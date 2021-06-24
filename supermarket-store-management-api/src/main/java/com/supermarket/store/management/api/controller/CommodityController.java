package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.CommodityForSave;
import com.supermarket.store.management.api.model.bo.CommodityForUpdate;
import com.supermarket.store.management.api.model.entity.CommodityDO;
import com.supermarket.store.management.api.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商品视图层接口
 * 用于处理前端发起的http请求，商品管理相关的请求统一在此类处理
 */
@Api("商品管理接口")
// 标识是spring IOC的controller类型的组件，并且接口返回结果都是数据结果，不是返回到对应的mvc页面
@RestController
// http路径的前缀
@RequestMapping(value = "/v1/commodities")
public class CommodityController {
    /**
     * 商品业务层组件
     * 注入这个组件，就可以使用商品业务层相关的功能
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 搜索商品分页列表
     *
     * @param offset 分页位移量
     * @param limit  分页大小
     * @return 商品分页对象
     */
    @ApiOperation("搜索商品分页列表")
    // http路径
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<CommodityDO> search(
            @ApiParam("分页位移量") @RequestParam("offset") Integer offset,
            @ApiParam("分页大小") @RequestParam("limit") Integer limit) {
        // controller层收到请求后，使用业务层搜索商品分页列表的功能，实现商品分页列表的搜索
        // 后将搜索的结果返回给前端
        return commodityService.search(offset, limit);
    }

    /**
     * 根据编号获取商品
     *
     * @param commodityCode 商品编号
     * @return 商品信息
     */
    @ApiOperation("根据编号获取商品")
    @RequestMapping(value = "/{commodity_code}", method = RequestMethod.GET)
    public CommodityDO get(
            @ApiParam("商品编号") @PathVariable(value = "commodity_code") String commodityCode) {
        // controller层收到请求后，使用业务层根据编号获取商品功能，实现商品详细信息的获取
        // 后将获取的结果返回给前端
        return commodityService.load(commodityCode);
    }

    /**
     * 新增商品
     *
     * @param commodityForSave 保存商品的信息，接收前端新增商品时设置的参数
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody @Valid CommodityForSave commodityForSave) {
        // controller层收到请求后，前端传过来的参数信息都保存到了请求体 commodityForSave 中
        // 将请求体对象，传给商品业务层进行商品的创建
        commodityService.create(commodityForSave);
    }

    /**
     * 编辑商品
     *
     * @param commodityCode 商品编号
     * @param commodityForUpdate 编辑商品的信息，接收前端编辑商品时设置的参数
     */
    @RequestMapping(value = "/{commodity_code}", method = RequestMethod.PUT)
    public void update(
            @ApiParam("商品编号") @PathVariable(value = "commodity_code") String commodityCode,
            @RequestBody @Valid CommodityForUpdate commodityForUpdate) {
        // controller层收到请求后，前端传过来的编辑商品的信息保存到了 commodityForUpdate 中
        // 将请求体对象，传给商品业务层进行商品的信息的更新
        commodityService.update(commodityCode, commodityForUpdate);
    }

    /**
     * 删除商品
     * @param commodityCode 商品编号
     */
    @RequestMapping(value = "/{commodity_code}", method = RequestMethod.DELETE)
    public void delete(
            @ApiParam("商品编号") @PathVariable(value = "commodity_code") String commodityCode) {
        // controller层接收到请求后，使用业务层组件，进行商品的根据商品编码删除商品
        commodityService.delete(commodityCode);
    }

}
