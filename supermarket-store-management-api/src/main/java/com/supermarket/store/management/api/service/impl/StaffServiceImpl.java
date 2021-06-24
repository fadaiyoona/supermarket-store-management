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
 * 使用@Service注解，注册成Spring IOC容器的组件
 */
@Service
public class StaffServiceImpl implements StaffService {
    /**
     * 员工持久层组件
     * 员工相关数据库的操作都通过 staffDAO 进行实现
     */
    @Autowired
    private StaffDAO staffDAO;

    @Override
    public StaffDO load(String code) {
        // 根据员工编码获取员工，返回员工信息 staff
        StaffDO staff = this.get(code);
        if (staff != null) {
            // 如果员工staff对象不为空，直接返回
            return staff;
        }
        // 若员工staff对象为空，抛出异常，提示前端员工不存在
        throw new NormalException(ExceptionCodeConstant.STAFF_NOT_FOUND, "员工不存在");
    }

    @Override
    public StaffDO get(String code) {
        // 使用持久层组件 staffDAO ，根据员工编码获取员工信息
        return staffDAO.findByCode(code);
    }

    @Override
    public Pager<StaffDO> search(Integer offset, Integer limit) {
        // 使用持久层组件 staffDAO ，根据分页位移量、分页大小，获取员工的分页信息
        return staffDAO.search(offset, limit);
    }

    @Override
    public void create(StaffForSave staffForSave) {
        // 先根据前端传过来的员工编码获取员工
        StaffDO staff = this.get(staffForSave.getCode());
        if (staff != null) {
            // 因为员工编码是员工的唯一标识，所以如果根据员工编码获取到了员工，那说明已经存在对应员工编码的员工
            // 直接抛出异常给前端，错误提示：员工编码已存在
            throw new NormalException(ExceptionCodeConstant.STAFF_CODE_EXIST, "员工编号已存在");
        }
        // 如果校验通过，进行创建员工信息
        // 设置员工的数据库对象的信息
        staff = new StaffDO();
        staff.setCode(staffForSave.getCode());
        staff.setName(staffForSave.getName());
        staff.setPassword(staffForSave.getPassword());
        staff.setLevel(staffForSave.getLevel());
        staff.setPhone(staffForSave.getPhone());
        staff.setRemark(staffForSave.getRemark());
        staff.setCreateTime(new Date());
        // 使用员工持久层组件，进行保存员工信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现新增的功能
        staffDAO.save(staff);
    }

    @Override
    public void update(String staffCode, StaffForUpdate staffForUpdate) {
        // 因为编辑是编辑已存在的员工，所以要先校验员工是否存在
        // 根据员工编码获取员工，如果员工不存在，直接抛出异常
        StaffDO staff = this.load(staffCode);

        // 若校验通过，进行修改员工信息
        // 设置员工的数据库对象的信息
        staff.setName(staffForUpdate.getName());
        staff.setPassword(staffForUpdate.getPassword());
        staff.setLevel(staffForUpdate.getLevel());
        staff.setPhone(staffForUpdate.getPhone());
        staff.setRemark(staffForUpdate.getRemark());
        // 使用员工持久层组件，进行保存员工信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现修改的功能
        staffDAO.save(staff);
    }

    @Override
    public void delete(String staffCode) {
        // 因为删除是删除已存在的员工，所以要先校验员工是否存在
        // 根据员工编码获取员工，如果员工不存在，直接抛出异常
        this.load(staffCode);

        // 使用员工持久层组件，进行删除员工信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接delete，即可实现根据主键ID删除员工的功能
        staffDAO.delete(staffCode);
    }
}
