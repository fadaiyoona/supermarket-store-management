package com.supermarket.store.management.api.common.model;

import lombok.Data;

import java.util.List;

@Data
public class Pager<T> {

	private Integer code;
	
	private String msg;
	
	private Long count;
	
	private List<T> data;
	
}
