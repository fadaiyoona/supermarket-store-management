package com.supermarket.store.management.api.controller;

import com.supermarket.store.management.api.common.Constants;
import com.supermarket.store.management.api.common.exception.ExceptionCodeConstant;
import com.supermarket.store.management.api.common.exception.NormalException;
import com.supermarket.store.management.api.common.model.Pager;
import com.supermarket.store.management.api.common.utils.ParamValidUtils;
import com.supermarket.store.management.api.model.bo.LoginBO;
import com.supermarket.store.management.api.model.bo.StaffForSave;
import com.supermarket.store.management.api.model.bo.StaffForUpdate;
import com.supermarket.store.management.api.model.entity.StaffDO;
import com.supermarket.store.management.api.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 员工视图层接口
 */
@RestController
@RequestMapping(value = "/v1/staffs")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     * 搜索员工分页列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<StaffDO> search(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return staffService.search(offset, limit);
    }

    /**
     * 根据编号获取员工
     */
    @RequestMapping(value = "/{staff_code}", method = RequestMethod.GET)
    public StaffDO get(
            @PathVariable(value = "staff_code") String staffCode) {
        return staffService.load(staffCode);
    }

    /**
     * 新增员工
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody StaffForSave staffForSave) {
        staffService.create(staffForSave);
    }

    /**
     * 编辑员工
     */
    @RequestMapping(value = "/{staff_code}", method = RequestMethod.PUT)
    public void update(
            @PathVariable(value = "staff_code") String staffCode,
            @RequestBody StaffForUpdate staffForUpdate) {
        staffService.update(staffCode, staffForUpdate);
    }

    /**
     * 删除员工
     */
    @RequestMapping(value = "/{staff_code}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "staff_code") String staffCode) {
        staffService.delete(staffCode);
    }

    /**
     * 用户登录接口
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public StaffDO login(
            @RequestBody @Valid LoginBO bo,
            HttpServletRequest request, BindingResult result) {
        ParamValidUtils.parameterValidation(result);
        StaffDO userDO = staffService.load(bo.getLoginName());
        if (!userDO.getPassword().equals(bo.getLoginPass())) {
            throw new NormalException(ExceptionCodeConstant.STAFF_CODE_EXIST, "用户名或者密码错误");
        }

        HttpSession session = request.getSession();
        session.setAttribute(Constants.User.CURRENT_USER, userDO);
        session.setMaxInactiveInterval(60 * 60);

        return userDO;
    }

    /**
     * 获取当前登录用户
     */
    @RequestMapping(value = "/current_user", method = RequestMethod.GET)
    public StaffDO getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (StaffDO) session.getAttribute(Constants.User.CURRENT_USER);
    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.User.CURRENT_USER);
    }

}
