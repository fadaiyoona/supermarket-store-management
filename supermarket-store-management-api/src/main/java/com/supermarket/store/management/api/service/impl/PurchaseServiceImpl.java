package com.supermarket.store.management.api.service.impl;

import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.NormalException;
import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.dao.PurchaseDAO;
import com.supermarket.store.management.api.dao.PurchaseDetailDAO;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForSave;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForUpdate;
import com.supermarket.store.management.api.model.bo.PurchaseForSave;
import com.supermarket.store.management.api.model.bo.PurchaseForUpdate;
import com.supermarket.store.management.api.model.entity.CommodityDO;
import com.supermarket.store.management.api.model.entity.PurchaseDO;
import com.supermarket.store.management.api.model.entity.PurchaseDetailDO;
import com.supermarket.store.management.api.service.CommodityService;
import com.supermarket.store.management.api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 采购业务层实现
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseDAO purchaseDAO;
    @Autowired
    private PurchaseDetailDAO purchaseDetailDAO;
    @Autowired
    private CommodityService commodityService;

    @Override
    public PurchaseDO load(String code) {
        PurchaseDO purchase = this.get(code);
        if (purchase != null) {
            return purchase;
        }
        throw new NormalException(ExceptionCodeConstant.PURCHASE_NOT_FOUND, "采购主表不存在");
    }

    @Override
    public PurchaseDO get(String code) {
        return purchaseDAO.findByCode(code);
    }

    @Override
    public Pager<PurchaseDO> search(Integer offset, Integer limit) {
        return purchaseDAO.search(offset, limit);
    }

    @Override
    public void create(PurchaseForSave purchaseForSave) {
        PurchaseDO purchase = this.get(purchaseForSave.getPurchaseCode());
        if (purchase != null) {
            throw new NormalException(ExceptionCodeConstant.PURCHASE_CODE_EXIST, "采购清单号已存在");
        }
        purchase = new PurchaseDO();
        purchase.setCode(purchaseForSave.getPurchaseCode());
        purchase.setStaffCode(purchaseForSave.getStaffCode());
        purchase.setRemark(purchaseForSave.getRemark());
        purchase.setCreateTime(new Date());
        purchaseDAO.save(purchase);
    }

    @Override
    public void update(String purchaseCode, PurchaseForUpdate purchaseForUpdate) {
        PurchaseDO purchase = this.load(purchaseCode);
        purchase.setStaffCode(purchaseForUpdate.getStaffCode());
        purchase.setRemark(purchaseForUpdate.getRemark());
        purchaseDAO.save(purchase);
    }

    @Override
    public void delete(String purchaseCode) {
        this.load(purchaseCode);

        purchaseDAO.delete(purchaseCode);

        purchaseDetailDAO.deleteByCode(purchaseCode);
    }

    @Override
    public void handlePurchaseDetailChange(String purchaseCode) {
        PurchaseDO purchase = this.load(purchaseCode);

        List<PurchaseDetailDO> purchaseDetails = this.listPurchaseDetail(purchaseCode);
        BigDecimal totalPrice = purchaseDetails.stream()
                .map(PurchaseDetailDO::getCommodityTotalPrice)
                .reduce(BigDecimal::add).get();
        int sum = purchaseDetails.stream()
                .mapToInt(PurchaseDetailDO::getNum)
                .sum();
        purchase.setTotalPrice(totalPrice);
        purchase.setNum(sum);
        purchaseDAO.save(purchase);
    }

    @Override
    public PurchaseDetailDO getPurchaseDetail(String purchaseDetailCode) {
        return purchaseDetailDAO.findOne(purchaseDetailCode);
    }

    @Override
    public PurchaseDetailDO loadPurchaseDetail(String purchaseDetailCode) {
        PurchaseDetailDO purchaseDetail = this.getPurchaseDetail(purchaseDetailCode);
        if (purchaseDetail != null) {
            return purchaseDetail;
        }
        throw new NormalException(ExceptionCodeConstant.PURCHASE_DETAIL_NOT_FOUND, "采购明细不存在");
    }

    @Override
    public List<PurchaseDetailDO> listPurchaseDetail(String purchaseCode) {
        this.load(purchaseCode);

        return purchaseDetailDAO.findByCode(purchaseCode);
    }

    @Override
    public void addDetail(PurchaseDetailForSave forSave) {
        this.load(forSave.getCode());

        PurchaseDetailDO purchaseDetail = this.getPurchaseDetail(forSave.getDetailCode());
        if (purchaseDetail != null) {
            throw new NormalException(ExceptionCodeConstant.PURCHASE_DETAIL_CODE_EXIST, "采购明细已存在");
        }

        CommodityDO commodity = commodityService.load(forSave.getCommodityCode());

        purchaseDetail = new PurchaseDetailDO();
        purchaseDetail.setDetailCode(forSave.getDetailCode());
        purchaseDetail.setCode(forSave.getCode());
        purchaseDetail.setCommodityCode(forSave.getCommodityCode());
        purchaseDetail.setNum(forSave.getNum());
        purchaseDetail.setCommodityPrice(commodity.getPrice());
        purchaseDetail.setCommodityTotalPrice(commodity.getPrice().multiply(new BigDecimal(forSave.getNum())));
        purchaseDetail.setRemark(forSave.getRemark());
        purchaseDetail.setCreateTime(new Date());
        purchaseDetailDAO.save(purchaseDetail);

        this.handlePurchaseDetailChange(forSave.getCode());
    }

    @Override
    public void updateDetail(String purchaseDetailCode, PurchaseDetailForUpdate forUpdate) {
        PurchaseDetailDO purchaseDetail = this.loadPurchaseDetail(purchaseDetailCode);

        CommodityDO commodity = commodityService.load(forUpdate.getCommodityCode());

        purchaseDetail.setCommodityCode(forUpdate.getCommodityCode());
        purchaseDetail.setNum(forUpdate.getNum());
        purchaseDetail.setCommodityPrice(commodity.getPrice());
        purchaseDetail.setCommodityTotalPrice(commodity.getPrice().multiply(new BigDecimal(forUpdate.getNum())));
        purchaseDetail.setRemark(forUpdate.getRemark());
        purchaseDetailDAO.save(purchaseDetail);

        this.handlePurchaseDetailChange(purchaseDetail.getCode());
    }

    @Override
    public void deleteDetail(String purchaseDetailCode) {
        PurchaseDetailDO purchaseDetail = this.loadPurchaseDetail(purchaseDetailCode);

        purchaseDetailDAO.delete(purchaseDetailCode);

        this.handlePurchaseDetailChange(purchaseDetail.getCode());
    }
}
