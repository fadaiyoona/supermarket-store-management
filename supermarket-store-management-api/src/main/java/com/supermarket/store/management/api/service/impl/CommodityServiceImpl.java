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
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityDAO commodityDAO;

    @Override
    public CommodityDO load(String code) {
        CommodityDO commodity = this.get(code);
        if (commodity != null) {
            return commodity;
        }
        throw new NormalException(ExceptionCodeConstant.COMMODITY_NOT_FOUND, "商品不存在");
    }

    @Override
    public CommodityDO get(String code) {
        return commodityDAO.findByCode(code);
    }

    @Override
    public Pager<CommodityDO> search(Integer offset, Integer limit) {
        return commodityDAO.search(offset, limit);
    }

    @Override
    public void create(CommodityForSave commodityForSave) {
        CommodityDO commodity = this.get(commodityForSave.getCode());
        if (commodity != null) {
            throw new NormalException(ExceptionCodeConstant.COMMODITY_CODE_EXIST, "商品编号已存在");
        }
        commodity = new CommodityDO();
        commodity.setCode(commodityForSave.getCode());
        commodity.setName(commodityForSave.getName());
        commodity.setPrice(commodityForSave.getPrice());
        commodity.setSupplierCode(commodityForSave.getSupplierCode());
        commodity.setIntroduction(commodityForSave.getIntroduction());
        commodity.setRemark(commodityForSave.getRemark());
        commodity.setCreateTime(new Date());
        commodityDAO.save(commodity);
    }

    @Override
    public void update(String commodityCode, CommodityForUpdate commodityForUpdate) {
        CommodityDO commodity = this.load(commodityCode);
        commodity.setName(commodityForUpdate.getName());
        commodity.setPrice(commodityForUpdate.getPrice());
        commodity.setSupplierCode(commodityForUpdate.getSupplierCode());
        commodity.setIntroduction(commodityForUpdate.getIntroduction());
        commodity.setRemark(commodityForUpdate.getRemark());
        commodityDAO.save(commodity);
    }

    @Override
    public void delete(String commodityCode) {
        this.load(commodityCode);

        commodityDAO.delete(commodityCode);
    }
}
