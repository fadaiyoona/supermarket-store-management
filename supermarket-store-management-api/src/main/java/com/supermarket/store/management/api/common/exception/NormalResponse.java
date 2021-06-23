package com.supermarket.store.management.api.common.exception;

import lombok.Data;

/**
 * 常见错误自定义返回类
 */
@Data
public class NormalResponse {

    private String code;

    private String message;

    public NormalResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NormalResponse() {

    }
}
