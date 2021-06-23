package com.supermarket.store.management.api.dao;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.entity.StaffDO;

/**
 * 员工持久层扩展实现
 */
public interface StaffDAOCustom {

    /**
     * 搜索员工分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 员工分页列表
     */
    Pager<StaffDO> search(Integer offset, Integer limit);
}
