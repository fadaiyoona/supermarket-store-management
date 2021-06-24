package com.supermarket.store.management.api.common.model;

import lombok.Data;

import java.util.List;

/**
 * 通用的分页返回的类
 * @param <T>
 */
@Data
public class Pager<T> {

    /**
     * 总数
     */
	private Long count;

    /**
     * 单页数据
     */
	private List<T> data;
	
}
