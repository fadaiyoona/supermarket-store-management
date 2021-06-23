package com.supermarket.store.management.api.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 供应商表
 */
@Data
@Entity
@Table(name = "t_supplier")
public class SupplierDO {

    /**
     * 供应商编号
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * 供应商名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 供应商简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 公司电话
     */
    @Column(name = "tellphone")
    private String tellphone;

    /**
     * 邮件
     */
    @Column(name = "email")
    private String email;

    /**
     * 联系人
     */
    @Column(name = "contacts")
    private String contacts;

    /**
     * 联系人电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

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
