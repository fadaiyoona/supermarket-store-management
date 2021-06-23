package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.SupplierDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 供应商表持久层
 */
public interface SupplierDAO extends JpaRepository<SupplierDO, String>, SupplierDAOCustom {

    SupplierDO findByCode(String code);
}
