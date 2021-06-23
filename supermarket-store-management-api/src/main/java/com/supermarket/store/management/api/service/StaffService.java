package com.supermarket.store.management.api.service;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.StaffForSave;
import com.supermarket.store.management.api.model.bo.StaffForUpdate;
import com.supermarket.store.management.api.model.entity.StaffDO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 员工业务层接口
 */
public interface StaffService {

    /**
     * 根据员工编码获取员工，不存在抛出异常
     * @param code 员工编码
     * @return 员工信息
     */
    StaffDO load(String code);

    /**
     * 根据员工编码获取员工，不存在返回空
     * @param code 员工编码
     * @return 员工信息
     */
    StaffDO get(String code);

    /**
     * 搜索员工分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 员工分页列表
     */
    Pager<StaffDO> search(Integer offset, Integer limit);

    /**
     * 创建员工
     * @param staffForSave 员工信息
     */
    void create(StaffForSave staffForSave);

    /**
     * 创建员工
     * @param staffCode 员工编号
     * @param staffForUpdate 员工信息
     */
    void update(String staffCode, StaffForUpdate staffForUpdate);

    /**
     * 删除员工
     * @param staffCode 员工编号
     */
    void delete(String staffCode);

}
