package com.supermarket.store.management.api.common.exception;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class InvalidArgResponse {
	
	private String errorCode;
	
	private String errorMessage;

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
