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
 * 使用@Service注解，注册成Spring IOC容器的组件
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    /**
     * 供应商持久层组件
     * 供应商相关数据库的操作都通过 supplierDAO 进行实现
     */
    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public SupplierDO load(String code) {
        // 根据供应商编码获取供应商，返回供应商信息 supplier
        SupplierDO supplier = this.get(code);
        if (supplier != null) {
            // 如果供应商supplier对象不为空，直接返回
            return supplier;
        }
        // 若供应商supplier对象为空，抛出异常，提示前端供应商不存在
        throw new NormalException(ExceptionCodeConstant.SUPPLIER_NOT_FOUND, "供应商不存在");
    }

    @Override
    public SupplierDO get(String code) {
        // 使用持久层组件 supplierDAO ，根据供应商编码获取供应商信息
        return supplierDAO.findByCode(code);
    }

    @Override
    public Pager<SupplierDO> search(Integer offset, Integer limit) {
        // 使用持久层组件 supplierDAO ，根据分页位移量、分页大小，获取供应商的分页信息
        return supplierDAO.search(offset, limit);
    }

    @Override
    public void create(SupplierForSave supplierForSave) {
        // 先根据前端传过来的供应商编码获取供应商
        SupplierDO supplier = this.get(supplierForSave.getCode());
        if (supplier != null) {
            // 因为供应商编码是供应商的唯一标识，所以如果根据供应商编码获取到了供应商，那说明已经存在对应供应商编码的供应商
            // 直接抛出异常给前端，错误提示：供应商编码已存在
            throw new NormalException(ExceptionCodeConstant.SUPPLIER_CODE_EXIST, "供应商编号已存在");
        }
        // 如果校验通过，进行创建供应商信息
        // 设置供应商的数据库对象的信息
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
        // 使用供应商持久层组件，进行保存供应商信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现新增的功能
        supplierDAO.save(supplier);
    }

    @Override
    public void update(String supplierCode, SupplierForUpdate supplierForUpdate) {
        // 因为编辑是编辑已存在的供应商，所以要先校验供应商是否存在
        // 根据供应商编码获取供应商，如果供应商不存在，直接抛出异常
        SupplierDO supplier = this.load(supplierCode);

        // 若校验通过，进行修改供应商信息
        // 设置供应商的数据库对象的信息
        supplier.setName(supplierForUpdate.getName());
        supplier.setShortName(supplierForUpdate.getShortName());
        supplier.setAddress(supplierForUpdate.getAddress());
        supplier.setTellphone(supplierForUpdate.getTellphone());
        supplier.setEmail(supplierForUpdate.getEmail());
        supplier.setContacts(supplierForUpdate.getContacts());
        supplier.setContactPhone(supplierForUpdate.getContactPhone());
        supplier.setRemark(supplierForUpdate.getRemark());
        // 使用供应商持久层组件，进行保存供应商信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现修改的功能
        supplierDAO.save(supplier);
    }

    @Override
    public void delete(String supplierCode) {
        // 因为删除是删除已存在的供应商，所以要先校验供应商是否存在
        // 根据供应商编码获取供应商，如果供应商不存在，直接抛出异常
        this.load(supplierCode);

        // 使用供应商持久层组件，进行删除供应商信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接delete，即可实现根据主键ID删除供应商的功能
        supplierDAO.delete(supplierCode);
    }
}
