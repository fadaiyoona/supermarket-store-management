package com.supermarket.store.management.api.service;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.SupplierForUpdate;
import com.supermarket.store.management.api.model.bo.SupplierForSave;
import com.supermarket.store.management.api.model.entity.SupplierDO;

/**
 * 供应商业务层接口
 */
public interface SupplierService {

    /**
     * 根据 编码获取供应商，不存在抛出异常
     * @param code 供应商编码
     * @return 供应商信息
     */
    SupplierDO load(String code);

    /**
     * 根据供应商编码获取供应商，不存在返回空
     * @param code 供应商编码
     * @return 供应商信息
     */
    SupplierDO get(String code);

    /**
     * 搜索供应商分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 供应商分页列表
     */
    Pager<SupplierDO> search(Integer offset, Integer limit);

    /**
     * 创建供应商
     * @param staffForSave 供应商信息
     */
    void create(SupplierForSave staffForSave);

    /**
     * 创建供应商
     * @param staffCode 供应商编号
     * @param staffForUpdate 供应商信息
     */
    void update(String staffCode, SupplierForUpdate staffForUpdate);

    /**
     * 删除供应商
     * @param staffCode 供应商编号
     */
    void delete(String staffCode);

}
