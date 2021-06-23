package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.entity.PurchaseDO;
import com.supermarket.store.management.api.model.entity.SupplierDO;

/**
 * 采购主表持久层扩展实现
 */
public interface PurchaseDAOCustom {

    /**
     * 搜索采购主表分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 采购主表分页列表
     */
    Pager<PurchaseDO> search(Integer offset, Integer limit);
}
