package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.StaffDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 员工表持久层
 * 该组件继承自JpaRepository接口，而JpaRepository继承自CrudRepository，
 * CrudRepository接口他实现了基本的增删改查的封装
 */
public interface StaffDAO extends JpaRepository<StaffDO, String>, StaffDAOCustom {

    /**
     * 根据员工编码，获取员工信息
     * 因为是jpa接口，因此通过jpa的表达式，可以自动生成对应的sql语句
     * 此处生成的sql语句是：SELECT * FROM t_staff WHERE code = :code
     * :code 就是我们 findByCode 函数的参数
     * @param code 员工编码
     * @return 员工信息
     */
    StaffDO findByCode(String code);
}
