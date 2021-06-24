package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.SupplierForUpdate;
import com.supermarket.store.management.api.model.bo.SupplierForSave;
import com.supermarket.store.management.api.model.entity.SupplierDO;
import com.supermarket.store.management.api.service.SupplierService;
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

/**
 * 供应商视图层接口
 * 用于处理前端发起的http请求，供应商管理相关的请求统一在此类处理
 */
@Api("供应商管理接口")
// 标识是spring IOC的controller类型的组件，并且接口返回结果都是数据结果，不是返回到对应的mvc页面
@RestController
// http路径的前缀
@RequestMapping(value = "/v1/suppliers")
public class SupplierController {
    /**
     * 供应商业务层组件
     * 注入这个组件，就可以使用供应商业务层相关的功能
     */
    @Autowired
    private SupplierService supplierService;

    /**
     * 搜索供应商分页列表
     *
     * @param offset 分页位移量
     * @param limit  分页大小
     * @return 供应商分页对象
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<SupplierDO> search(
            @ApiParam("分页位移量") @RequestParam("offset") Integer offset,
            @ApiParam("分页大小") @RequestParam("limit") Integer limit) {
        // controller层收到请求后，使用业务层搜索供应商分页列表的功能，实现供应商分页列表的搜索
        // 后将搜索的结果返回给前端
        return supplierService.search(offset, limit);
    }

    /**
     * 根据编号获取供应商
     *
     * @param supplierCode 供应商编号
     * @return 供应商信息
     */
    @RequestMapping(value = "/{supplier_code}", method = RequestMethod.GET)
    public SupplierDO get(
            @PathVariable(value = "supplier_code") String supplierCode) {
        // controller层收到请求后，使用业务层根据编号获取供应商功能，实现供应商详细信息的获取
        // 后将获取的结果返回给前端
        return supplierService.load(supplierCode);
    }

    /**
     * 新增供应商
     *
     * @param supplierForSave 保存供应商的信息，接收前端新增供应商时设置的参数
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody @Valid SupplierForSave supplierForSave) {
        // controller层收到请求后，前端传过来的参数信息都保存到了请求体 supplierForSave 中
        // 将请求体对象，传给供应商业务层进行供应商的创建
        supplierService.create(supplierForSave);
    }

    /**
     * 编辑供应商
     *
     * @param supplierCode 供应商编号
     * @param supplierForUpdate 编辑供应商的信息，接收前端编辑供应商时设置的参数
     */
    @RequestMapping(value = "/{supplier_code}", method = RequestMethod.PUT)
    public void update(
            @PathVariable(value = "supplier_code") String supplierCode,
            @RequestBody @Valid SupplierForUpdate supplierForUpdate) {
        // controller层收到请求后，前端传过来的编辑供应商的信息保存到了 supplierForUpdate 中
        // 将请求体对象，传给供应商业务层进行供应商的信息的更新
        supplierService.update(supplierCode, supplierForUpdate);
    }

    /**
     * 删除供应商
     * @param supplierCode 供应商编号
     */
    @RequestMapping(value = "/{supplier_code}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "supplier_code") String supplierCode) {
        // controller层接收到请求后，使用业务层组件，进行供应商的根据供应商编码删除供应商
        supplierService.delete(supplierCode);
    }

}
