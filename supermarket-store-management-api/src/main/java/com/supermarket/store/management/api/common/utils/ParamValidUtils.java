package com.supermarket.store.management.api.common.utils;

import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.InvalidArgException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ParamValidUtils {

    public static void parameterValidation(BindingResult result) {

        List<FieldError> fieldErrors = result.getFieldErrors();

        if (!fieldErrors.isEmpty()) {
            InvalidArgException invalidParamsException = new InvalidArgException(ExceptionCodeConstant.INVALID_ARGS, "无效的提交参数");

            for (FieldError error : fieldErrors) {
                invalidParamsException.addErrorMessage(error.getField(), error.getDefaultMessage());
            }

            throw invalidParamsException;
        }

    }
}
