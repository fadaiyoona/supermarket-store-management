package com.supermarket.store.management.api.dao.impl;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.StaffDAOCustom;
import com.supermarket.store.management.api.model.entity.StaffDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工持久层扩展实现
 */
public class StaffDAOImpl implements StaffDAOCustom {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Pager<StaffDO> search(Integer offset, Integer limit) {
        String sql = "FROM t_staff WHERE 1 = 1 ";

        Map<String, Object> params = new HashMap<>();

        Long count = namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(*) " + sql,
                params,
                Long.class);

        sql += "ORDER BY create_time DESC LIMIT :offset, :limit ";
        List<StaffDO> staffs = namedParameterJdbcTemplate.query(
                "SELECT * " + sql,
                params,
                BeanPropertyRowMapper.newInstance(StaffDO.class));

        Pager<StaffDO> staffDOPager = new Pager<>();
        staffDOPager.setCount(count);
        staffDOPager.setData(staffs);
        return staffDOPager;
    }
}
