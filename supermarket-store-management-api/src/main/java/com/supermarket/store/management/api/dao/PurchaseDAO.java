package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.PurchaseDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 采购表持久层
 */
public interface PurchaseDAO extends JpaRepository<PurchaseDO, String>, PurchaseDAOCustom {

    PurchaseDO findByCode(String code);
}
