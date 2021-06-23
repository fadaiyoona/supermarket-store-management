package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 编辑采购明细信息
 */
@Data
public class PurchaseDetailForUpdate {

    /**
     * 商品编号
     */
    @NotBlank
    private String commodityCode;

    /**
     * 采购数量
     */
    @NotNull
    private Integer num;

    /**
     * 备注
     */
    private String remark;
}
