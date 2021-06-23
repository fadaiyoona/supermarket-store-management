package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.entity.CommodityDO;

/**
 * 商品表持久层扩展
 */
public interface CommodityDAOCustom {

    /**
     * 搜索商品分页列表
     */
    Pager<CommodityDO> search(Integer offset, Integer limit);
}
