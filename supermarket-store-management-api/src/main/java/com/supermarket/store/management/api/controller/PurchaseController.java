package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForSave;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForUpdate;
import com.supermarket.store.management.api.model.bo.PurchaseForSave;
import com.supermarket.store.management.api.model.bo.PurchaseForUpdate;
import com.supermarket.store.management.api.model.entity.PurchaseDO;
import com.supermarket.store.management.api.model.entity.PurchaseDetailDO;
import com.supermarket.store.management.api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 采购管理接口
 */
@RestController
@RequestMapping(value = "/v1/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 搜索采购主表分页列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<PurchaseDO> search(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return purchaseService.search(offset, limit);
    }

    /**
     * 根据编号获取采购主表
     */
    @RequestMapping(value = "/{purchase_code}", method = RequestMethod.GET)
    public PurchaseDO get(
            @PathVariable(value = "purchase_code") String purchaseCode) {
        return purchaseService.load(purchaseCode);
    }

    /**
     * 新增采购主表
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody PurchaseForSave purchaseForSave) {
        purchaseService.create(purchaseForSave);
    }

    /**
     * 编辑采购主表
     */
    @RequestMapping(value = "/{purchase_code}", method = RequestMethod.PUT)
    public void update(
            @PathVariable(value = "purchase_code") String purchaseCode,
            @RequestBody PurchaseForUpdate purchaseForUpdate) {
        purchaseService.update(purchaseCode, purchaseForUpdate);
    }

    /**
     * 删除采购主表
     */
    @RequestMapping(value = "/{purchase_code}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "purchase_code") String purchaseCode) {
        purchaseService.delete(purchaseCode);
    }

    /**
     * 获取采购明细记录列表
     */
    @RequestMapping(value = "/{purchase_code}/details", method = RequestMethod.GET)
    public List<PurchaseDetailDO> purchaseDetail(
            @PathVariable(value = "purchase_code") String purchaseCode) {
        return purchaseService.listPurchaseDetail(purchaseCode);
    }

    /**
     * 新增采购明细记录
     */
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public void addDetail(
            @RequestBody PurchaseDetailForSave forSave) {
        purchaseService.addDetail(forSave);
    }

    /**
     * 编辑采购明细记录
     */
    @RequestMapping(value = "/details/{detail_code}", method = RequestMethod.POST)
    public void updateDetail(
            @PathVariable(value = "detail_code") String detailCode,
            @RequestBody PurchaseDetailForUpdate forUpdate) {
        purchaseService.updateDetail(detailCode, forUpdate);
    }

    /**
     * 删除采购明细记录
     */
    @RequestMapping(value = "/details/{detail_code}", method = RequestMethod.DELETE)
    public void deleteDetail(
            @PathVariable(value = "detail_code") String detailCode) {
        purchaseService.deleteDetail(detailCode);
    }

}
