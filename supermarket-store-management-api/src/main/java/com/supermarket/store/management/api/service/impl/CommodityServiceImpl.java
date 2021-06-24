package com.supermarket.store.management.api.service.impl;

import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.NormalException;
import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.CommodityDAO;
import com.supermarket.store.management.api.model.bo.CommodityForSave;
import com.supermarket.store.management.api.model.bo.CommodityForUpdate;
import com.supermarket.store.management.api.model.entity.CommodityDO;
import com.supermarket.store.management.api.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 商品业务层实现
 * 使用@Service注解，注册成Spring IOC容器的组件
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    /**
     * 商品持久层组件
     * 商品相关数据库的操作都通过 commodityDAO 进行实现
     */
    @Autowired
    private CommodityDAO commodityDAO;

    @Override
    public CommodityDO load(String code) {
        // 根据商品编码获取商品，返回商品信息 commodity
        CommodityDO commodity = this.get(code);
        if (commodity != null) {
            // 如果商品commodity对象不为空，直接返回
            return commodity;
        }
        // 若商品commodity对象为空，抛出异常，提示前端商品不存在
        throw new NormalException(ExceptionCodeConstant.COMMODITY_NOT_FOUND, "商品不存在");
    }

    @Override
    public CommodityDO get(String code) {
        // 使用持久层组件 commodityDAO ，根据商品编码获取商品信息
        return commodityDAO.findByCode(code);
    }

    @Override
    public Pager<CommodityDO> search(Integer offset, Integer limit) {
        // 使用持久层组件 commodityDAO ，根据分页位移量、分页大小，获取商品的分页信息
        return commodityDAO.search(offset, limit);
    }

    @Override
    public void create(CommodityForSave commodityForSave) {
        // 先根据前端传过来的商品编码获取商品
        CommodityDO commodity = this.get(commodityForSave.getCode());
        if (commodity != null) {
            // 因为商品编码是商品的唯一标识，所以如果根据商品编码获取到了商品，那说明已经存在对应商品编码的商品
            // 直接抛出异常给前端，错误提示：商品编码已存在
            throw new NormalException(ExceptionCodeConstant.COMMODITY_CODE_EXIST, "商品编号已存在");
        }
        // 如果校验通过，进行创建商品信息
        // 设置商品的数据库对象的信息
        commodity = new CommodityDO();
        commodity.setCode(commodityForSave.getCode());
        commodity.setName(commodityForSave.getName());
        commodity.setPrice(commodityForSave.getPrice());
        commodity.setSupplierCode(commodityForSave.getSupplierCode());
        commodity.setIntroduction(commodityForSave.getIntroduction());
        commodity.setRemark(commodityForSave.getRemark());
        commodity.setCreateTime(new Date());
        // 使用商品持久层组件，进行保存商品信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现新增的功能
        commodityDAO.save(commodity);
    }

    @Override
    public void update(String commodityCode, CommodityForUpdate commodityForUpdate) {
        // 因为编辑是编辑已存在的商品，所以要先校验商品是否存在
        // 根据商品编码获取商品，如果商品不存在，直接抛出异常
        CommodityDO commodity = this.load(commodityCode);

        // 若校验通过，进行修改商品信息
        // 设置商品的数据库对象的信息
        commodity.setName(commodityForUpdate.getName());
        commodity.setPrice(commodityForUpdate.getPrice());
        commodity.setSupplierCode(commodityForUpdate.getSupplierCode());
        commodity.setIntroduction(commodityForUpdate.getIntroduction());
        commodity.setRemark(commodityForUpdate.getRemark());
        // 使用商品持久层组件，进行保存商品信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现修改的功能
        commodityDAO.save(commodity);
    }

    @Override
    public void delete(String commodityCode) {
        // 因为删除是删除已存在的商品，所以要先校验商品是否存在
        // 根据商品编码获取商品，如果商品不存在，直接抛出异常
        this.load(commodityCode);

        // 使用商品持久层组件，进行删除商品信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接delete，即可实现根据主键ID删除商品的功能
        commodityDAO.delete(commodityCode);
    }
}
