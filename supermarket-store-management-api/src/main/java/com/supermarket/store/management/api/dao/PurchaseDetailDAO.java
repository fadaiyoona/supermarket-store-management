package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.PurchaseDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购明细表持久层
 */
public interface PurchaseDetailDAO extends JpaRepository<PurchaseDetailDO, String> {

    @Transactional
    @Modifying
    void deleteByCode(String purchaseCode);

    List<PurchaseDetailDO> findByCode(String purchaseCode);
}
