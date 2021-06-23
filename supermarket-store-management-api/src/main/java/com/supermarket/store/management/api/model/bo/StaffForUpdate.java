package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;

/**
 * 更新员工信息
 */
@Data
public class StaffForUpdate {

    /**
     * 姓名
     */
    @NotBlank
    private String name;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 级别
     */
    @NotBlank
    private String level;

    /**
     * 电话
     */
    private String phone;

    /**
     * 工资
     */
    private Double salary;

    /**
     * 备注
     */
    private String remark;

}
