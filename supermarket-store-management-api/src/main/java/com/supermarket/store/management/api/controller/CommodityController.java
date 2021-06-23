package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.CommodityForSave;
import com.supermarket.store.management.api.model.bo.CommodityForUpdate;
import com.supermarket.store.management.api.model.entity.CommodityDO;
import com.supermarket.store.management.api.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品视图层接口
 */
@RestController
@RequestMapping(value = "/v1/commodities")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    /**
     * 搜索商品分页列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<CommodityDO> search(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return commodityService.search(offset, limit);
    }

    /**
     * 根据编号获取商品
     */
    @RequestMapping(value = "/{commodity_code}", method = RequestMethod.GET)
    public CommodityDO get(
            @PathVariable(value = "commodity_code") String commodityCode) {
        return commodityService.load(commodityCode);
    }

    /**
     * 新增商品
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody CommodityForSave commodityForSave) {
        commodityService.create(commodityForSave);
    }

    /**
     * 编辑商品
     */
    @RequestMapping(value = "/{commodity_code}", method = RequestMethod.PUT)
    public void update(
            @PathVariable(value = "commodity_code") String commodityCode,
            @RequestBody CommodityForUpdate commodityForUpdate) {
        commodityService.update(commodityCode, commodityForUpdate);
    }

    /**
     * 删除商品
     */
    @RequestMapping(value = "/{commodity_code}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "commodity_code") String commodityCode) {
        commodityService.delete(commodityCode);
    }

}
