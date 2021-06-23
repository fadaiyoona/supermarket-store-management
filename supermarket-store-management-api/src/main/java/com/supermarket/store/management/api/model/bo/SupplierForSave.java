package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增供应商信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierForSave extends SupplierForUpdate {

    /**
     * 供应商编号
     */
    @NotBlank
    private String code;
}
