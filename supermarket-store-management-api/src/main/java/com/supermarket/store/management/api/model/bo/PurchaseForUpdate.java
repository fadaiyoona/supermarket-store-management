package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * 编辑采购主表信息
 */
@Data
public class PurchaseForUpdate {

    /**
     * 员工编号
     */
    @NotBlank
    private String staffCode;

    /**
     * 采购时间
     */
    @NotBlank
    private String purchaseDate;

    /**
     * 备注
     */
    private String remark;
}
