package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 编辑供应商信息
 */
@Data
public class SupplierForUpdate {

    /**
     * 供应商名称
     */
    @NotBlank
    private String name;

    /**
     * 供应商简称
     */
    @NotBlank
    private String shortName;

    /**
     * 地址
     */
    private String address;

    /**
     * 公司电话
     */
    private String tellphone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 备注
     */
    private String remark;
}
