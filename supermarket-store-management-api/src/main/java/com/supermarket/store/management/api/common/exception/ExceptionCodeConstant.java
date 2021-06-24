package com.supermarket.store.management.api.common.exception;

/**
 * 统一异常码类
 */
public class ExceptionCodeConstant {
    /** 无效参数 */
    public static final String INVALID_ARGS = "invalid.args";

    /** 员工不存在 */
    public static final String STAFF_NOT_FOUND = "staff.not.found";

    /** 员工编号已存在 */
    public static final String STAFF_CODE_EXIST = "staff.code.exist";

    /** 供应商不存在 */
    public static final String SUPPLIER_NOT_FOUND = "supplier.not.found";

    /** 供应商编号已存在 */
    public static final String SUPPLIER_CODE_EXIST = "supplier.code.exist";

    /** 商品不存在 */
    public static final String COMMODITY_NOT_FOUND = "commodity.not.found";

    /** 商品编号已存在 */
    public static final String COMMODITY_CODE_EXIST = "commodity.code.exist";

    /** 采购主表不存在 */
    public static final String PURCHASE_NOT_FOUND = "purchase.not.found";

    /** 采购清单号已存在 */
    public static final String PURCHASE_CODE_EXIST = "purchase.code.exist";

    /** 采购明细不存在 */
    public static final String PURCHASE_DETAIL_NOT_FOUND = "purchase.detail.not.found";

    /** 采购明细号已存在 */
    public static final String PURCHASE_DETAIL_CODE_EXIST = "purchase.detail.code.exist";
}
