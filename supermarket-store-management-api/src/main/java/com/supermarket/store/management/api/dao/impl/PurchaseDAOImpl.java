package com.supermarket.store.management.api.dao.impl;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.PurchaseDAOCustom;
import com.supermarket.store.management.api.dao.SupplierDAOCustom;
import com.supermarket.store.management.api.model.entity.PurchaseDO;
import com.supermarket.store.management.api.model.entity.SupplierDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购主表持久层扩展实现
 */
public class PurchaseDAOImpl implements PurchaseDAOCustom {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Pager<PurchaseDO> search(Integer offset, Integer limit) {
        String sql = "FROM t_purchase WHERE 1 = 1 ";

        Map<String, Object> params = new HashMap<>();

        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) " + sql,
                params,
                Long.class);

        sql += "ORDER BY create_time DESC LIMIT :offset, :limit ";
        List<PurchaseDO> staffs = namedParameterJdbcTemplate.query(
                "SELECT * " + sql,
                params,
                BeanPropertyRowMapper.newInstance(PurchaseDO.class));

        Pager<PurchaseDO> purchaseDOPager = new Pager<>();
        purchaseDOPager.setCount(count);
        purchaseDOPager.setData(staffs);
        return purchaseDOPager;
    }
}
