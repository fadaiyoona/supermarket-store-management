package com.supermarket.store.management.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * 处理定义好需要进行处理的异常
 */
@ControllerAdvice
public class GlobalException {

    /**
     * 处理参数错误的异常
     *
     * @param request   http请求信息
     * @param response  http返回信息
     * @param exception 异常信息
     * @return 返回给前端的结果
     */
    @ExceptionHandler(InvalidArgException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidArgResponse handleParametersException(
            HttpServletRequest request,
            HttpServletResponse response,
            InvalidArgException exception) {
        // 获取异常信息，将异常信息设置到 InvalidArgResponse 对象中返回给前端
        InvalidArgResponse invalidArgResponse = new InvalidArgResponse();
        // 异常码
        invalidArgResponse.setErrorCode(exception.getCode());
        // 异常信息提示
        invalidArgResponse.setErrorMessage(exception.getMessage());
        // 异常错误信息
        invalidArgResponse.setErrors(exception.getErrorMessage());
        return invalidArgResponse;
    }

    /**
     * 处理常见错误自定义错误类 - 5XX错误等异常
     *
     * @param request   http请求信息
     * @param response  http返回信息
     * @param exception 异常信息
     * @return 返回给前端的结果
     */
    @ExceptionHandler(NormalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NormalResponse handlerNormalException(
            HttpServletRequest request,
            HttpServletResponse response,
            NormalException exception) {
        // 获取异常信息，将异常信息设置到 NormalResponse 对象中返回给前端
        return new NormalResponse(exception.getCode(), exception.getMessage());
    }

}
