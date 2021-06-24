package com.supermarket.store.management.api.common.exception;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 参数错误异常的返回信息
 */
@Data
public class InvalidArgResponse {
    /**
     * 错误码
     */
	private String errorCode;

    /**
     * 错误信息提示
     */
	private String errorMessage;

    /**
     * 出错的参数的错误信息
     */
	private List<Map<String,String>> errors;


	public InvalidArgResponse(){
		super();
	}

	public InvalidArgResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public InvalidArgResponse(String errorCode){
		this.errorCode = errorCode;
	}

	
	
}
