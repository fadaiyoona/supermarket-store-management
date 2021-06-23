package com.supermarket.store.management.api.service.impl;

import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.NormalException;
import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.StaffDAO;
import com.supermarket.store.management.api.model.bo.StaffForSave;
import com.supermarket.store.management.api.model.bo.StaffForUpdate;
import com.supermarket.store.management.api.model.entity.StaffDO;
import com.supermarket.store.management.api.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 员工业务层实现
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;

    @Override
    public StaffDO load(String code) {
        StaffDO staff = this.get(code);
        if (staff != null) {
            return staff;
        }
        throw new NormalException(ExceptionCodeConstant.STAFF_NOT_FOUND, "员工不存在");
    }

    @Override
    public StaffDO get(String code) {
        return staffDAO.findByCode(code);
    }

    @Override
    public Pager<StaffDO> search(Integer offset, Integer limit) {
        return staffDAO.search(offset, limit);
    }

    @Override
    public void create(StaffForSave staffForSave) {
        StaffDO staff = this.get(staffForSave.getCode());
        if (staff != null) {
            throw new NormalException(ExceptionCodeConstant.STAFF_CODE_EXIST, "员工编号已存在");
        }
        staff = new StaffDO();
        staff.setCode(staffForSave.getCode());
        staff.setName(staffForSave.getName());
        staff.setPassword(staffForSave.getPassword());
        staff.setLevel(staffForSave.getLevel());
        staff.setPhone(staffForSave.getPhone());
        staff.setRemark(staffForSave.getRemark());
        staff.setCreateTime(new Date());
        staffDAO.save(staff);
    }

    @Override
    public void update(String staffCode, StaffForUpdate staffForUpdate) {
        StaffDO staff = this.load(staffCode);
        staff.setName(staffForUpdate.getName());
        staff.setPassword(staffForUpdate.getPassword());
        staff.setLevel(staffForUpdate.getLevel());
        staff.setPhone(staffForUpdate.getPhone());
        staff.setRemark(staffForUpdate.getRemark());
        staffDAO.save(staff);
    }

    @Override
    public void delete(String staffCode) {
        this.load(staffCode);

        staffDAO.delete(staffCode);
    }
}
