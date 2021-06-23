package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.entity.SupplierDO;

/**
 * 供应商持久层扩展实现
 */
public interface SupplierDAOCustom {

    /**
     * 搜索供应商分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 供应商分页列表
     */
    Pager<SupplierDO> search(Integer offset, Integer limit);
}
