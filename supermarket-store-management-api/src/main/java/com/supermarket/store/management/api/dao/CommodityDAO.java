package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.model.entity.CommodityDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品表持久层
 */
public interface CommodityDAO extends JpaRepository<CommodityDO, String>, CommodityDAOCustom {

    CommodityDO findByCode(String code);
}
