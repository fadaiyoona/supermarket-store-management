package com.supermarket.store.management.api.dao.impl;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.CommodityDAOCustom;
import com.supermarket.store.management.api.model.entity.CommodityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品表持久层扩展实现
 */
public class CommodityDAOImpl implements CommodityDAOCustom {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Pager<CommodityDO> search(Integer offset, Integer limit) {
        String sql = "FROM t_commodity WHERE 1 = 1 ";

        Map<String, Object> params = new HashMap<>();

        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) " + sql,
                params,
                Long.class);

        sql += "ORDER BY create_time DESC LIMIT :offset, :limit ";
        List<CommodityDO> staffs = namedParameterJdbcTemplate.query(
                "SELECT * " + sql,
                params,
                BeanPropertyRowMapper.newInstance(CommodityDO.class));

        Pager<CommodityDO> commodityDOPager = new Pager<>();
        commodityDOPager.setCount(count);
        commodityDOPager.setData(staffs);
        return commodityDOPager;
    }
}
