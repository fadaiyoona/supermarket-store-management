package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.StaffDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 员工表持久层
 */
public interface StaffDAO extends JpaRepository<StaffDO, String>, StaffDAOCustom {

    StaffDO findByCode(String code);
}
