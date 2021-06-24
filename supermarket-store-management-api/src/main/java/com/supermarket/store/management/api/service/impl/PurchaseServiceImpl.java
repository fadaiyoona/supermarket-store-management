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
 * 使用@Service注解，注册成Spring IOC容器的组件
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    /**
     * 采购主表持久层组件
     * 采购主表相关数据库的操作都通过 purchaseDAO 进行实现
     */
    @Autowired
    private PurchaseDAO purchaseDAO;
    /**
     * 采购明细持久层组件
     * 采购明细记录相关数据库的操作都通过 purchaseDetailDAO 进行实现
     */
    @Autowired
    private PurchaseDetailDAO purchaseDetailDAO;
    /**
     * 商品业务层组件
     * 商品相关业务操作都通过 commodityService 进行实现
     */
    @Autowired
    private CommodityService commodityService;

    @Override
    public PurchaseDO load(String code) {
        // 根据采购主表编码获取采购主表，返回采购主表信息 purchase
        PurchaseDO purchase = this.get(code);
        if (purchase != null) {
            // 如果采购主表purchase对象不为空，直接返回
            return purchase;
        }
        // 若采购主表purchase对象为空，抛出异常，提示前端采购主表不存在
        throw new NormalException(ExceptionCodeConstant.PURCHASE_NOT_FOUND, "采购主表不存在");
    }

    @Override
    public PurchaseDO get(String code) {
        // 使用持久层组件 purchaseDAO ，根据采购主表编码获取采购主表信息
        return purchaseDAO.findByCode(code);
    }

    @Override
    public Pager<PurchaseDO> search(Integer offset, Integer limit) {
        // 使用持久层组件 purchaseDAO ，根据分页位移量、分页大小，获取采购主表的分页信息
        return purchaseDAO.search(offset, limit);
    }

    @Override
    public void create(PurchaseForSave purchaseForSave) {
        // 先根据前端传过来的采购主表编码获取采购主表
        PurchaseDO purchase = this.get(purchaseForSave.getPurchaseCode());
        if (purchase != null) {
            // 因为采购主表编码是采购主表的唯一标识，所以如果根据采购主表编码获取到了采购主表，那说明已经存在对应采购主表编码的采购主表
            // 直接抛出异常给前端，错误提示：采购主表编码已存在
            throw new NormalException(ExceptionCodeConstant.PURCHASE_CODE_EXIST, "采购清单号已存在");
        }
        // 如果校验通过，进行创建采购主表信息
        // 设置采购主表的数据库对象的信息
        purchase = new PurchaseDO();
        purchase.setCode(purchaseForSave.getPurchaseCode());
        purchase.setStaffCode(purchaseForSave.getStaffCode());
        purchase.setPurchaseDate(purchaseForSave.getPurchaseDate());
        purchase.setRemark(purchaseForSave.getRemark());
        purchase.setCreateTime(new Date());
        // 使用采购主表持久层组件，进行保存采购主表信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现新增的功能
        purchaseDAO.save(purchase);
    }

    @Override
    public void update(String purchaseCode, PurchaseForUpdate purchaseForUpdate) {
        // 因为编辑是编辑已存在的采购主表，所以要先校验采购主表是否存在
        // 根据采购主表编码获取采购主表，如果采购主表不存在，直接抛出异常
        PurchaseDO purchase = this.load(purchaseCode);

        // 若校验通过，进行修改采购主表信息
        // 设置采购主表的数据库对象的信息
        purchase.setStaffCode(purchaseForUpdate.getStaffCode());
        purchase.setPurchaseDate(purchaseForUpdate.getPurchaseDate());
        purchase.setRemark(purchaseForUpdate.getRemark());
        // 使用采购主表持久层组件，进行保存采购主表信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现修改的功能
        purchaseDAO.save(purchase);
    }

    @Override
    public void delete(String purchaseCode) {
        // 因为删除是删除已存在的采购主表，所以要先校验采购主表是否存在
        // 根据采购主表编码获取采购主表，如果采购主表不存在，直接抛出异常
        this.load(purchaseCode);

        // 使用采购主表持久层组件，进行删除采购主表信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接delete，即可实现根据主键ID删除采购主表的功能
        purchaseDAO.delete(purchaseCode);

        // 使用采购明细持久层组件，进行删除采购明细信息
        // 因为采购明细只是采购主表的附加数据，因此采购主表删除时，需要级联删除采购明细表的数据
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接delete，即可实现根据主键ID删除采购主表的功能
        purchaseDetailDAO.deleteByCode(purchaseCode);
    }

    @Override
    public void handlePurchaseDetailChange(String purchaseCode) {
        // 采购主表更新 - 处理采购明细记录变更
        // 因为是处理已存在的采购主表，所以要先校验采购主表是否存在
        // 根据采购主表编码获取采购主表，如果采购主表不存在，直接抛出异常
        PurchaseDO purchase = this.load(purchaseCode);

        // 根据采购主表，获取采购主表相关的所有采购明细记录数据
        List<PurchaseDetailDO> purchaseDetails = this.listPurchaseDetail(purchaseCode);

        // 计算采购主表中的总价
        // 总价 = 所有采购明细中的商品总价和
        BigDecimal totalPrice = purchaseDetails.stream()
                // 获取采购明细中的商品总价
                .map(PurchaseDetailDO::getCommodityTotalPrice)
                // 求和
                .reduce(BigDecimal::add).get();

        // 计算采购主表中的总采购数量
        int sum = purchaseDetails.stream()
                // 获取采购明细中的商品数量
                .mapToInt(PurchaseDetailDO::getNum)
                // 求和
                .sum();

        // 将计算的总价和采购数量设置到采购主表数据中
        purchase.setTotalPrice(totalPrice);
        purchase.setNum(sum);

        // 使用采购主表持久层组件，进行保存采购主表信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接save，即可实现修改的功能
        purchaseDAO.save(purchase);
    }

    @Override
    public PurchaseDetailDO getPurchaseDetail(String purchaseDetailCode) {
        // 使用持久层组件 purchaseDetailDAO ，根据采购明细号获取采购明细信息
        return purchaseDetailDAO.findOne(purchaseDetailCode);
    }

    @Override
    public PurchaseDetailDO loadPurchaseDetail(String purchaseDetailCode) {
        // 根据采购明细号获取采购明细数据，返回采购明细数据 purchaseDetail
        PurchaseDetailDO purchaseDetail = this.getPurchaseDetail(purchaseDetailCode);
        if (purchaseDetail != null) {
            // 如果采购明细purchaseDetail对象不为空，直接返回
            return purchaseDetail;
        }
        // 若采购明细purchaseDetail对象为空，抛出异常，提示前端采购主表不存在
        throw new NormalException(ExceptionCodeConstant.PURCHASE_DETAIL_NOT_FOUND, "采购明细不存在");
    }

    @Override
    public List<PurchaseDetailDO> listPurchaseDetail(String purchaseCode) {
        // 因为是获取已存在的采购主表的采购明细记录，所以要先校验采购主表是否存在
        // 根据采购主表编码获取采购主表，如果采购主表不存在，直接抛出异常
        this.load(purchaseCode);

        // 使用持久层组件 purchaseDetailDAO ，根据采购主表编码，获取采购主表相关的所有采购明细信息
        return purchaseDetailDAO.findByCode(purchaseCode);
    }

    @Override
    public void addDetail(PurchaseDetailForSave forSave) {
        // 因为是添加已存在的采购主表的采购明细记录，所以要先校验采购主表是否存在
        // 根据采购主表编码获取采购主表，如果采购主表不存在，直接抛出异常
        this.load(forSave.getCode());

        // 先根据前端传过来的采购明细号获取采购明细
        PurchaseDetailDO purchaseDetail = this.getPurchaseDetail(forSave.getDetailCode());
        if (purchaseDetail != null) {
            // 因为采购明细号是采购明细的唯一标识，所以如果根据采购明细号获取到了采购明细，那说明已经存在对应采购明细号的采购明细
            // 直接抛出异常给前端，错误提示：采购明细号已存在
            throw new NormalException(ExceptionCodeConstant.PURCHASE_DETAIL_CODE_EXIST, "采购明细已存在");
        }

        // 因为采购明细中含有商品的信息，需保证添加的采购明细中的商品是存在的商品，
        // 如果不存在，抛出异常给前端
        CommodityDO commodity = commodityService.load(forSave.getCommodityCode());

        // 如果校验通过，进行添加采购明细信息
        // 设置采购明细的数据库对象的信息
        purchaseDetail = new PurchaseDetailDO();
        purchaseDetail.setDetailCode(forSave.getDetailCode());
        purchaseDetail.setCode(forSave.getCode());
        purchaseDetail.setCommodityCode(forSave.getCommodityCode());
        purchaseDetail.setNum(forSave.getNum());
        // 设置采购明细中商品的单价信息
        purchaseDetail.setCommodityPrice(commodity.getPrice());
        // 设置采购明细中商品的总价信息
        // 通过计算得出采购明细的商品总价
        purchaseDetail.setCommodityTotalPrice(commodity.getPrice().multiply(new BigDecimal(forSave.getNum())));
        purchaseDetail.setRemark(forSave.getRemark());
        purchaseDetail.setCreateTime(new Date());
        // 使用持久层组件 purchaseDetailDAO ，保存采购明细记录信息
        purchaseDetailDAO.save(purchaseDetail);

        // 通过通用的代码，更加采购主表编码，更新采购主表的信息
        this.handlePurchaseDetailChange(forSave.getCode());
    }

    @Override
    public void updateDetail(String purchaseDetailCode, PurchaseDetailForUpdate forUpdate) {
        // 先根据前端传过来的采购明细号获取采购明细
        // 因为是编辑已存在的采购明细记录，所以要先校验采购明细是否存在
        // 根据采购明细号获取采购明细，如果采购明细不存在，直接抛出异常
        PurchaseDetailDO purchaseDetail = this.loadPurchaseDetail(purchaseDetailCode);

        // 因为采购明细中含有商品的信息，需保证添加的采购明细中的商品是存在的商品，
        // 如果不存在，抛出异常给前端
        CommodityDO commodity = commodityService.load(forUpdate.getCommodityCode());

        // 如果校验通过，进行添加采购明细信息
        // 设置采购明细的数据库对象的信息
        purchaseDetail.setCommodityCode(forUpdate.getCommodityCode());
        purchaseDetail.setNum(forUpdate.getNum());
        // 设置采购明细中商品的单价信息
        purchaseDetail.setCommodityPrice(commodity.getPrice());
        // 设置采购明细中商品的总价信息
        // 通过计算得出采购明细的商品总价
        purchaseDetail.setCommodityTotalPrice(commodity.getPrice().multiply(new BigDecimal(forUpdate.getNum())));
        purchaseDetail.setRemark(forUpdate.getRemark());
        // 使用持久层组件 purchaseDetailDAO ，保存采购明细记录信息
        purchaseDetailDAO.save(purchaseDetail);

        // 通过通用的代码，更加采购主表编码，更新采购主表的信息
        this.handlePurchaseDetailChange(purchaseDetail.getCode());
    }

    @Override
    public void deleteDetail(String purchaseDetailCode) {
        // 先根据前端传过来的采购明细号获取采购明细
        // 因为是编辑已存在的采购明细记录，所以要先校验采购明细是否存在
        // 根据采购明细号获取采购明细，如果采购明细不存在，直接抛出异常
        PurchaseDetailDO purchaseDetail = this.loadPurchaseDetail(purchaseDetailCode);

        // 使用采购明细持久层组件，进行删除采购明细信息
        // 该组件继承自CrudRepository接口，他实现了基本的增删改查的封装，因此直接delete，即可实现根据主键ID删除采购明细的功能
        purchaseDetailDAO.delete(purchaseDetailCode);

        // 通过通用的代码，更加采购主表编码，更新采购主表的信息
        this.handlePurchaseDetailChange(purchaseDetail.getCode());
    }
}
