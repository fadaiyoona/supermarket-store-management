package com.supermarket.store.management.api.dao.impl;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.SupplierDAO;
import com.supermarket.store.management.api.dao.SupplierDAOCustom;
import com.supermarket.store.management.api.model.entity.StaffDO;
import com.supermarket.store.management.api.model.entity.SupplierDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 供应商持久层扩展实现
 */
public class SupplierDAOImpl implements SupplierDAOCustom {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Pager<SupplierDO> search(Integer offset, Integer limit) {
        String sql = "FROM t_supplier WHERE 1 = 1 ";

        Map<String, Object> params = new HashMap<>();

        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) " + sql,
                params,
                Long.class);

        sql += "ORDER BY create_time DESC LIMIT :offset, :limit ";
        List<SupplierDO> staffs = namedParameterJdbcTemplate.query(
                "SELECT * " + sql,
                params,
                BeanPropertyRowMapper.newInstance(SupplierDO.class));

        Pager<SupplierDO> supplierDOPager = new Pager<>();
        supplierDOPager.setCount(count);
        supplierDOPager.setData(staffs);
        return supplierDOPager;
    }
}
