package com.supermarket.store.management.api.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 创建员工信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StaffForSave extends StaffForUpdate {

    /**
     * 编号
     */
    @NotBlank
    private String code;

}
