package com.supermarket.store.management.api.common.exception;

import lombok.Data;

/**
 * 常见错误自定义返回类
 * 处理业务，程序运行中出现的异常，封装后给前端的数据结构
 */
@Data
public class NormalResponse {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息提示
     */
    private String message;

    public NormalResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NormalResponse() {

    }
}
