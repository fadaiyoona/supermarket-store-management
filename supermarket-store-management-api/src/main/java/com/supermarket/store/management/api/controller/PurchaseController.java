package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForSave;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForUpdate;
import com.supermarket.store.management.api.model.bo.PurchaseForSave;
import com.supermarket.store.management.api.model.bo.PurchaseForUpdate;
import com.supermarket.store.management.api.model.entity.PurchaseDO;
import com.supermarket.store.management.api.model.entity.PurchaseDetailDO;
import com.supermarket.store.management.api.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 采购管理接口
 * 用于处理前端发起的http请求，采购主表管理相关的请求统一在此类处理
 */
@Api("采购主表管理接口")
// 标识是spring IOC的controller类型的组件，并且接口返回结果都是数据结果，不是返回到对应的mvc页面
@RestController
// http路径的前缀
@RequestMapping(value = "/v1/purchases")
public class PurchaseController {
    /**
     * 采购业务层组件 (采购主表相关业务 + 采购明细相关业务 都在此业务层组件实现)
     * 注入这个组件，就可以使用采购业务层相关的功能
     */
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 搜索采购主表分页列表
     *
     * @param offset 分页位移量
     * @param limit  分页大小
     * @return 采购主表分页对象
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<PurchaseDO> search(
            @ApiParam("分页位移量") @RequestParam("offset") Integer offset,
            @ApiParam("分页大小") @RequestParam("limit") Integer limit) {
        // controller层收到请求后，使用业务层搜索采购主表分页列表的功能，实现采购主表分页列表的搜索
        // 后将搜索的结果返回给前端
        return purchaseService.search(offset, limit);
    }

    /**
     * 根据编号获取采购主表
     *
     * @param purchaseCode 采购主表编号
     * @return 采购主表信息
     */
    @RequestMapping(value = "/{purchase_code}", method = RequestMethod.GET)
    public PurchaseDO get(
            @PathVariable(value = "purchase_code") String purchaseCode) {
        // controller层收到请求后，使用业务层根据编号获取采购主表功能，实现采购主表详细信息的获取
        // 后将获取的结果返回给前端
        return purchaseService.load(purchaseCode);
    }

    /**
     * 新增采购主表
     *
     * @param purchaseForSave 保存采购主表的信息，接收前端新增采购主表时设置的参数
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody @Valid PurchaseForSave purchaseForSave) {
        // controller层收到请求后，前端传过来的参数信息都保存到了请求体 purchaseForSave 中
        // 将请求体对象，传给采购业务层进行采购主表的创建
        purchaseService.create(purchaseForSave);
    }

    /**
     * 编辑采购主表
     *
     * @param purchaseCode      采购主表编号
     * @param purchaseForUpdate 编辑采购主表的信息，接收前端编辑采购主表时设置的参数
     */
    @RequestMapping(value = "/{purchase_code}", method = RequestMethod.PUT)
    public void update(
            @PathVariable(value = "purchase_code") String purchaseCode,
            @RequestBody @Valid PurchaseForUpdate purchaseForUpdate) {
        // controller层收到请求后，前端传过来的编辑采购主表的信息保存到了 purchaseForUpdate 中
        // 将请求体对象，传给采购业务层进行采购主表的信息的更新
        purchaseService.update(purchaseCode, purchaseForUpdate);
    }

    /**
     * 删除采购主表
     *
     * @param purchaseCode 采购主表编号
     */
    @RequestMapping(value = "/{purchase_code}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "purchase_code") String purchaseCode) {
        // controller层接收到请求后，使用业务层组件，进行采购主表的根据采购主表编码删除采购主表
        purchaseService.delete(purchaseCode);
    }

    /**
     * 获取采购主表的采购明细记录列表
     *
     * @param purchaseCode 采购清单号
     * @return 采购主表的采购明细记录列表
     */
    @RequestMapping(value = "/{purchase_code}/details", method = RequestMethod.GET)
    public List<PurchaseDetailDO> purchaseDetail(
            @PathVariable(value = "purchase_code") String purchaseCode) {
        // controller层接收到请求后，使用业务层组件，进行获取采购主表的采购明细记录列表
        return purchaseService.listPurchaseDetail(purchaseCode);
    }

    /**
     * 新增采购主表的采购明细记录
     *
     * @param forSave 采购明细记录信息，接收前端添加采购明细数据时设置的参数
     */
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public void addDetail(
            @RequestBody @Valid PurchaseDetailForSave forSave) {
        // controller层收到请求后，前端传过来的新增采购主表的采购明细记录信息保存到了 forSave 中
        // 将请求体对象，传给采购业务层进行采购主表的采购明细记录的新增
        purchaseService.addDetail(forSave);
    }

    /**
     * 编辑采购主表的采购明细记录
     *
     * @param detailCode 采购明细号
     * @param forUpdate 采购明细记录信息，接收前端编辑采购主表的采购明细记录时设置的参数
     */
    @RequestMapping(value = "/details/{detail_code}", method = RequestMethod.PUT)
    public void updateDetail(
            @PathVariable(value = "detail_code") String detailCode,
            @RequestBody @Valid PurchaseDetailForUpdate forUpdate) {
        // controller层收到请求后，前端传过来的编辑采购主表的信息保存到了 forUpdate 中
        // 将请求体对象，传给采购业务层进行采购主表的采购明细记录的更新
        purchaseService.updateDetail(detailCode, forUpdate);
    }

    /**
     * 删除采购明细记录
     *
     * @param detailCode 采购明细号
     */
    @RequestMapping(value = "/details/{detail_code}", method = RequestMethod.DELETE)
    public void deleteDetail(
            @PathVariable(value = "detail_code") String detailCode) {
        // controller层接收到请求后，使用业务层组件，进行采购明细记录的根据采购清单号删除采购主表
        purchaseService.deleteDetail(detailCode);
    }

}
