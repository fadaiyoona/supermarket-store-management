package com.supermarket.store.management.api.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 */
@Data
@Entity
@Table(name = "t_commodity")
public class CommodityDO {

    /**
     * 商品编号
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 商品单价
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 供应商编号
     */
    @Column(name = "supplier_code")
    private String supplierCode;

    /**
     * 简介
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
