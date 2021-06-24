package com.supermarket.store.management.api.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 参数异常类
 */
// TODO 全局参数异常应使用封装好的@Valid注解来校验，此处不做处理，使用自己封装的全局参数异常类
@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidArgException extends RuntimeException{
    /**
     * 异常码
     */
    private String code;

    /**
     * 异常信息提示
     */
    private String message;

    /**
     * 异常错误信息
     */
    private List<Map<String,String>> errorMessage= new ArrayList<>();

    public InvalidArgException(String code,String message){
        this.code = code;
        this.message = message;
    }

    public InvalidArgException(){
        super();
    }

    public void addErrorMessage(String field,String message){
        Map<String, String> error = new Hashtable<>();
        error.put(field, message);
        errorMessage.add(error);
    }
}
