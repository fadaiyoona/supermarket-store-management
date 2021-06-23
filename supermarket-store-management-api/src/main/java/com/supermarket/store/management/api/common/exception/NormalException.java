package com.supermarket.store.management.api.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常见错误自定义错误类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NormalException extends RuntimeException {

    private String code;

    private String message;

    public NormalException(String code,String message){
        this.code = code;
        this.message = message;
    }

    public NormalException(){
        super();
    }
}
