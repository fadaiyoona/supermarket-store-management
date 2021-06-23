package com.supermarket.store.management.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalException {

    /**
     * 处理参数错误的异常
     *
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @ExceptionHandler(InvalidArgException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidArgResponse handleParametersException(
            HttpServletRequest request,
            HttpServletResponse response,
            InvalidArgException exception) {
        InvalidArgResponse invalidArgResponse = new InvalidArgResponse();
        invalidArgResponse.setErrorCode(exception.getCode());
        invalidArgResponse.setErrorMessage(exception.getMessage());
        invalidArgResponse.setErrors(exception.getErrorMessage());
        return invalidArgResponse;
    }

    /**
     * 处理常见错误自定义错误类 - 5XX错误等异常
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @ExceptionHandler(NormalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NormalResponse handlerNormalException(
            HttpServletRequest request,
            HttpServletResponse response,
            NormalException exception) {
        return new NormalResponse(exception.getCode(), exception.getMessage());
    }

}
