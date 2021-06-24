package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.SupplierDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 供应商表持久层
 * 该组件继承自JpaRepository接口，而JpaRepository继承自CrudRepository，
 * CrudRepository接口他实现了基本的增删改查的封装
 */
public interface SupplierDAO extends JpaRepository<SupplierDO, String>, SupplierDAOCustom {

    /**
     * 根据供应商编码，获取供应商信息
     * 因为是jpa接口，因此通过jpa的表达式，可以自动生成对应的sql语句
     * 此处生成的sql语句是：SELECT * FROM t_supplier WHERE code = :code
     * :code 就是我们函数的参数
     * @param code 供应商编码
     * @return 供应商信息
     */
    SupplierDO findByCode(String code);
}
