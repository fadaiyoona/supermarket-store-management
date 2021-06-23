package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增采购明细信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDetailForSave extends PurchaseDetailForUpdate {

    /**
     * 采购明细号
     */
    @NotBlank
    private String detailCode;

    /**
     * 采购清单号
     */
    @NotBlank
    private String code;
}
