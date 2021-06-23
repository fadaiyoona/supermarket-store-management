package com.supermarket.store.management.api.service;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.CommodityForSave;
import com.supermarket.store.management.api.model.bo.CommodityForUpdate;
import com.supermarket.store.management.api.model.entity.CommodityDO;

/**
 * 商品业务层接口
 */
public interface CommodityService {

    /**
     * 根据 编码获取商品，不存在抛出异常
     * @param code 商品编码
     * @return 商品信息
     */
    CommodityDO load(String code);

    /**
     * 根据商品编码获取商品，不存在返回空
     * @param code 商品编码
     * @return 商品信息
     */
    CommodityDO get(String code);

    /**
     * 搜索商品分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 商品分页列表
     */
    Pager<CommodityDO> search(Integer offset, Integer limit);

    /**
     * 创建商品
     * @param staffForSave 商品信息
     */
    void create(CommodityForSave staffForSave);

    /**
     * 创建商品
     * @param staffCode 商品编号
     * @param staffForUpdate 商品信息
     */
    void update(String staffCode, CommodityForUpdate staffForUpdate);

    /**
     * 删除商品
     * @param staffCode 商品编号
     */
    void delete(String staffCode);

}
