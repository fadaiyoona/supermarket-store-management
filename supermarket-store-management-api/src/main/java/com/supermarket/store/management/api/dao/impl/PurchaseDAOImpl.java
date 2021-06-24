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
 * 一些数据库层操作比较复杂的SQL，在此处进行扩展实现
 */
public class PurchaseDAOImpl implements PurchaseDAOCustom {
    /**
     * 使用 @Autowired 注解控制反转，自动获取sql操作的类
     */
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Pager<PurchaseDO> search(Integer offset, Integer limit) {
        // 手动写sql语句，进行数据库的查询
        String sql = "FROM t_purchase WHERE 1 = 1 ";

        Map<String, Object> params = new HashMap<>();

        // 获取分页数据的总数
        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) " + sql,
                params,
                Long.class);

        sql += "ORDER BY create_time DESC LIMIT :offset, :limit ";
        params.put("offset", offset);
        params.put("limit", limit);
        // 获取采购主表分页数据
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
