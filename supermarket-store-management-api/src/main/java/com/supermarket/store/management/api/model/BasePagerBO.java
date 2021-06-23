package com.supermarket.store.management.api.model;

import lombok.Data;

@Data
public class BasePagerBO {
    private Integer page = 1;

    private Integer limit = 10;

    public int getOffset() {
        return (page - 1) * limit;
    }
}
