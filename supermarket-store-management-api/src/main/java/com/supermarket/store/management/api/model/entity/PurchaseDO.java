package com.supermarket.store.management.api.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购表
 */
@Data
@Entity
@Table(name = "t_purchase")
public class PurchaseDO {

    /**
     * 采购清单号
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * 员工编号
     */
    @Column(name = "staff_code")
    private String staffCode;

    /**
     * 采购日期
     */
    @Column(name = "purchase_date")
    private String purchaseDate;

    /**
     * 采购数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 采购总价
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;

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
