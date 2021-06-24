package com.supermarket.store.management.api.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常见错误自定义错误类
 * 处理业务，程序运行中出现的异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NormalException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息提示
     */
    private String message;

    public NormalException(String code,String message){
        this.code = code;
        this.message = message;
    }

    public NormalException(){
        super();
    }
}
