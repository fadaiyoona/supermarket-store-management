package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.SupplierForUpdate;
import com.supermarket.store.management.api.model.bo.SupplierForSave;
import com.supermarket.store.management.api.model.entity.SupplierDO;
import com.supermarket.store.management.api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供应商视图层接口
 */
@RestController
@RequestMapping(value = "/v1/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 搜索供应商分页列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<SupplierDO> search(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return supplierService.search(offset, limit);
    }

    /**
     * 根据编号获取供应商
     */
    @RequestMapping(value = "/{supplier_code}", method = RequestMethod.GET)
    public SupplierDO get(
            @PathVariable(value = "supplier_code") String supplierCode) {
        return supplierService.load(supplierCode);
    }

    /**
     * 新增供应商
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody SupplierForSave supplierForSave) {
        supplierService.create(supplierForSave);
    }

    /**
     * 编辑供应商
     */
    @RequestMapping(value = "/{supplier_code}", method = RequestMethod.PUT)
    public void update(
            @PathVariable(value = "supplier_code") String supplierCode,
            @RequestBody SupplierForUpdate supplierForUpdate) {
        supplierService.update(supplierCode, supplierForUpdate);
    }

    /**
     * 删除供应商
     */
    @RequestMapping(value = "/{supplier_code}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "supplier_code") String supplierCode) {
        supplierService.delete(supplierCode);
    }

}
