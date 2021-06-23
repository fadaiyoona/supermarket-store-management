package com.supermarket.store.management.api.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购明细表
 */
@Data
@Entity
@Table(name = "t_purchase_detail")
public class PurchaseDetailDO {

    /**
     * 采购明细号
     */
    @Id
    @Column(name = "detail_code")
    private String detailCode;

    /**
     * 采购清单号
     */
    @Column(name = "code")
    private String code;

    /**
     * 商品编号
     */
    @Column(name = "commodity_code")
    private String commodityCode;

    /**
     * 采购数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 商品单价
     */
    @Column(name = "commodity_price")
    private BigDecimal commodityPrice;

    /**
     * 商品总价
     */
    @Column(name = "commodity_total_price")
    private BigDecimal commodityTotalPrice;

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
