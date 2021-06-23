package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增采购主表信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseForSave extends PurchaseForUpdate {

    /**
     * 采购主表编号
     */
    @NotBlank
    private String purchaseCode;
}
