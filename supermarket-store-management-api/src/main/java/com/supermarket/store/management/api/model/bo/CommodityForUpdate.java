package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * 编辑供应商信息
 */
@Data
public class CommodityForUpdate {

    /**
     * 商品名称
     */
    @NotBlank
    private String name;

    /**
     * 商品单价
     */
    @NotBlank
    private BigDecimal price;

    /**
     * 供应商编号
     */
    @NotBlank
    private String supplierCode;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 备注
     */
    private String remark;
}
