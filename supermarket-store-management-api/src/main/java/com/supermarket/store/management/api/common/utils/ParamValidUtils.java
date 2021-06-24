package com.supermarket.store.management.api.common.utils;

import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.InvalidArgException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 处理异常的工具类
 */
public class ParamValidUtils {

    /**
     * 处理异常
     * @param result
     */
    public static void parameterValidation(BindingResult result) {
        // 获取有错误的前端参数
        List<FieldError> fieldErrors = result.getFieldErrors();

        // 若不为空，进行相应处理
        if (!fieldErrors.isEmpty()) {
            // 封装成参数异常
            InvalidArgException invalidParamsException = new InvalidArgException(ExceptionCodeConstant.INVALID_ARGS, "无效的提交参数");

            for (FieldError error : fieldErrors) {
                // 添加具体错误信息
                invalidParamsException.addErrorMessage(error.getField(), error.getDefaultMessage());
            }

            // 抛出参数异常
            throw invalidParamsException;
        }

    }
}
