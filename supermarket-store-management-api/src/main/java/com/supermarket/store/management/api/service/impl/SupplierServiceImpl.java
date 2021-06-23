package com.supermarket.store.management.api.service.impl;

import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.NormalException;
import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.SupplierDAO;
import com.supermarket.store.management.api.model.bo.SupplierForUpdate;
import com.supermarket.store.management.api.model.bo.SupplierForSave;
import com.supermarket.store.management.api.model.entity.SupplierDO;
import com.supermarket.store.management.api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 供应商业务层实现
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public SupplierDO load(String code) {
        SupplierDO supplier = this.get(code);
        if (supplier != null) {
            return supplier;
        }
        throw new NormalException(ExceptionCodeConstant.SUPPLIER_NOT_FOUND, "供应商不存在");
    }

    @Override
    public SupplierDO get(String code) {
        return supplierDAO.findByCode(code);
    }

    @Override
    public Pager<SupplierDO> search(Integer offset, Integer limit) {
        return supplierDAO.search(offset, limit);
    }

    @Override
    public void create(SupplierForSave supplierForSave) {
        SupplierDO supplier = this.get(supplierForSave.getCode());
        if (supplier != null) {
            throw new NormalException(ExceptionCodeConstant.SUPPLIER_CODE_EXIST, "供应商编号已存在");
        }
        supplier = new SupplierDO();
        supplier.setCode(supplierForSave.getCode());
        supplier.setName(supplierForSave.getName());
        supplier.setShortName(supplierForSave.getShortName());
        supplier.setAddress(supplierForSave.getAddress());
        supplier.setTellphone(supplierForSave.getTellphone());
        supplier.setEmail(supplierForSave.getEmail());
        supplier.setContacts(supplierForSave.getContacts());
        supplier.setContactPhone(supplierForSave.getContactPhone());
        supplier.setRemark(supplierForSave.getRemark());
        supplier.setCreateTime(new Date());
        supplierDAO.save(supplier);
    }

    @Override
    public void update(String supplierCode, SupplierForUpdate supplierForUpdate) {
        SupplierDO supplier = this.load(supplierCode);
        supplier.setName(supplierForUpdate.getName());
        supplier.setShortName(supplierForUpdate.getShortName());
        supplier.setAddress(supplierForUpdate.getAddress());
        supplier.setTellphone(supplierForUpdate.getTellphone());
        supplier.setEmail(supplierForUpdate.getEmail());
        supplier.setContacts(supplierForUpdate.getContacts());
        supplier.setContactPhone(supplierForUpdate.getContactPhone());
        supplier.setRemark(supplierForUpdate.getRemark());
        supplierDAO.save(supplier);
    }

    @Override
    public void delete(String supplierCode) {
        this.load(supplierCode);

        supplierDAO.delete(supplierCode);
    }
}
