package com.supermarket.store.management.api.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工表
 */
@Data
@Entity
@Table(name = "t_staff")
public class StaffDO {

    /**
     * 编号
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 级别
     */
    @Column(name = "level")
    private String level;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 工资
     */
    @Column(name = "salary")
    private Double salary;

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
