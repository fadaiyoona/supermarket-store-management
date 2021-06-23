package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 新增商品信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommodityForSave extends CommodityForUpdate {

    /**
     * 商品编号
     */
    @NotBlank
    private String code;
}
