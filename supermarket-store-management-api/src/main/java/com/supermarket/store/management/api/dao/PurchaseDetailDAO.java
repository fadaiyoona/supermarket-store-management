package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.PurchaseDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购明细表持久层
 * 该组件继承自JpaRepository接口，而JpaRepository继承自CrudRepository，
 * CrudRepository接口他实现了基本的增删改查的封装
 */
public interface PurchaseDetailDAO extends JpaRepository<PurchaseDetailDO, String> {

    /**
     * 根据采购主表编码，删除采购主表相关的所有采购明细信息
     * 因为是jpa接口，因此通过jpa的表达式，可以自动生成对应的sql语句
     * 此处生成的sql语句是：DELETE * FROM t_purchase_detail WHERE code = :code
     * :code 就是我们函数的参数
     * @param purchaseCode 采购主表编码
     */
    @Transactional
    @Modifying
    void deleteByCode(String purchaseCode);

    /**
     * 根据采购主表编码，获取采购主表相关的所有采购明细信息
     * 因为是jpa接口，因此通过jpa的表达式，可以自动生成对应的sql语句
     * 此处生成的sql语句是：SELECT * FROM t_purchase_detail WHERE code = :code
     * :code 就是我们函数的参数
     * @param purchaseCode 采购主表编码
     * @return 采购主表相关的所有采购明细信息
     */
    List<PurchaseDetailDO> findByCode(String purchaseCode);
}
