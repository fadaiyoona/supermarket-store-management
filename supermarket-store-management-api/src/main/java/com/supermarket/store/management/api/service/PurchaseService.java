package com.supermarket.store.management.api.service;

import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForSave;
import com.supermarket.store.management.api.model.bo.PurchaseDetailForUpdate;
import com.supermarket.store.management.api.model.bo.PurchaseForSave;
import com.supermarket.store.management.api.model.bo.PurchaseForUpdate;
import com.supermarket.store.management.api.model.entity.PurchaseDO;
import com.supermarket.store.management.api.model.entity.PurchaseDetailDO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 采购业务层接口
 */
public interface PurchaseService {

    /**
     * 根据 编码获取采购，不存在抛出异常
     * @param code 采购编码
     * @return 采购信息
     */
    PurchaseDO load(String code);

    /**
     * 根据采购编码获取采购，不存在返回空
     * @param code 采购编码
     * @return 采购信息
     */
    PurchaseDO get(String code);

    /**
     * 搜索采购分页列表
     * @param offset 偏移量
     * @param limit  分页大小
     * @return 采购分页列表
     */
    Pager<PurchaseDO> search(Integer offset, Integer limit);

    /**
     * 创建采购
     * @param purchaseForSave 采购信息
     */
    void create(PurchaseForSave purchaseForSave);

    /**
     * 创建采购
     * @param purchaseCode 采购编号
     * @param purchaseForUpdate 采购信息
     */
    void update(String purchaseCode, PurchaseForUpdate purchaseForUpdate);

    /**
     * 删除采购
     * @param purchaseCode 采购编号
     */
    void delete(String purchaseCode);

    /**
     * 采购主表更新 - 处理采购明细记录变更
     * @param purchaseCode 采购编号
     */
    void handlePurchaseDetailChange(String purchaseCode);

    /**
     * 根据采购明细编号获取采购明细记录
     * @param purchaseDetailCode 采购明细编号
     * @return 采购明细记录
     */
    PurchaseDetailDO getPurchaseDetail(String purchaseDetailCode);

    /**
     * 根据采购明细编号获取采购明细记录
     * @param purchaseDetailCode 采购明细编号
     * @return 采购明细记录
     */
    PurchaseDetailDO loadPurchaseDetail(String purchaseDetailCode);

    /**
     * 获取采购明细记录列表
     * @param purchaseCode 采购清单号
     * @return 采购明细记录列表
     */
    List<PurchaseDetailDO> listPurchaseDetail(String purchaseCode);

    /**
     * 添加采购明细记录
     * @param forSave 采购明细信息
     */
    void addDetail(PurchaseDetailForSave forSave);

    /**
     * 编辑采购明细记录
     * @param purchaseDetailCode 采购明细编号
     * @param forUpdate 采购明细信息
     */
    void updateDetail(String purchaseDetailCode, PurchaseDetailForUpdate forUpdate);

    /**
     * 删除采购明细记录
     * @param purchaseDetailCode 采购明细编号
     */
    void deleteDetail(String purchaseDetailCode);

}
