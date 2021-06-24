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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
 * 用于处理前端发起的http请求，员工管理相关的请求统一在此类处理
 */
@Api("员工管理接口")
// 标识是spring IOC的controller类型的组件，并且接口返回结果都是数据结果，不是返回到对应的mvc页面
@RestController
// http路径的前缀
@RequestMapping(value = "/v1/staffs")
public class StaffController {
    /**
     * 员工业务层组件
     * 注入这个组件，就可以使用员工业务层相关的功能
     */
    @Autowired
    private StaffService staffService;

    /**
     * 搜索员工分页列表
     *
     * @param offset 分页位移量
     * @param limit  分页大小
     * @return 员工分页对象
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Pager<StaffDO> search(
            @ApiParam("分页位移量") @RequestParam("offset") Integer offset,
            @ApiParam("分页大小") @RequestParam("limit") Integer limit) {
        // controller层收到请求后，使用业务层搜索员工分页列表的功能，实现员工分页列表的搜索
        // 后将搜索的结果返回给前端
        return staffService.search(offset, limit);
    }

    /**
     * 根据编号获取员工
     *
     * @param staffCode 员工编号
     * @return 员工信息
     */
    @RequestMapping(value = "/{staff_code}", method = RequestMethod.GET)
    public StaffDO get(
            @ApiParam("员工编号") @PathVariable(value = "staff_code") String staffCode) {
        // controller层收到请求后，使用业务层根据编号获取员工功能，实现员工详细信息的获取
        // 后将获取的结果返回给前端
        return staffService.load(staffCode);
    }

    /**
     * 新增员工
     *
     * @param staffForSave 保存员工的信息，接收前端新增员工时设置的参数
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody @Valid StaffForSave staffForSave) {
        // controller层收到请求后，前端传过来的参数信息都保存到了请求体 staffForSave 中
        // 将请求体对象，传给员工业务层进行员工的创建
        staffService.create(staffForSave);
    }

    /**
     * 编辑员工
     *
     * @param staffCode 员工编号
     * @param staffForUpdate 编辑员工的信息，接收前端编辑员工时设置的参数
     */
    @RequestMapping(value = "/{staff_code}", method = RequestMethod.PUT)
    public void update(
            @ApiParam("员工编号") @PathVariable(value = "staff_code") String staffCode,
            @RequestBody @Valid StaffForUpdate staffForUpdate) {
        // controller层收到请求后，前端传过来的编辑员工的信息保存到了 staffForUpdate 中
        // 将请求体对象，传给员工业务层进行员工的信息的更新
        staffService.update(staffCode, staffForUpdate);
    }

    /**
     * 删除员工
     * @param staffCode 员工编号
     */
    @RequestMapping(value = "/{staff_code}", method = RequestMethod.DELETE)
    public void delete(
            @ApiParam("员工编号") @PathVariable(value = "staff_code") String staffCode) {
        // controller层接收到请求后，使用业务层组件，进行员工的根据员工编码删除员工
        staffService.delete(staffCode);
    }

    /**
     * 用户登录接口
     * @param bo      前端传过来的登录信息
     * @param request http请求
     * @param result  数据返回结果绑定对象
     * @return 用户信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public StaffDO login(
            @RequestBody @Valid LoginBO bo,
            HttpServletRequest request,
            BindingResult result) {
        // 处理异常
        ParamValidUtils.parameterValidation(result);
        // 通过员工业务层组件，根据员工编码，获取员工信息，如果不存在直接抛出异常提示员工不存在
        StaffDO userDO = staffService.load(bo.getLoginName());
        // 校验员工的密码是否跟前端传过来的用户输入的密码一致，如果不一致的话，抛出异常，提示用户名或者密码错误
        if (!userDO.getPassword().equals(bo.getLoginPass())) {
            throw new NormalException(ExceptionCodeConstant.STAFF_CODE_EXIST, "用户名或者密码错误");
        }

        // 登录成功，将用户信息保存到session域对象中，并且属性值的名称是常量 "user"
        HttpSession session = request.getSession();
        session.setAttribute(Constants.User.CURRENT_USER, userDO);
        // 设置过期时间是60分钟
        session.setMaxInactiveInterval(60 * 60);
        // 返回用户信息给前端
        return userDO;
    }

    /**
     * 获取当前登录用户
     * @param request http请求
     * @return 用户信息
     */
    @RequestMapping(value = "/current_user", method = RequestMethod.GET)
    public StaffDO getCurrentUser(HttpServletRequest request) {
        // 获取session域对象
        HttpSession session = request.getSession();
        // 获取session域对象中属性是"user"的值，即为当前登录的用户
        // 如果当前的用户已经登录，直接返回用户的信息
        // 如果当前的用户还未登录，则返回空对象，前端根据返回的用户信息，如果是空，提示用户进行登录
        return (StaffDO) session.getAttribute(Constants.User.CURRENT_USER);
    }

    /**
     * 用户登出
     * @param request http请求
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void loginOut(HttpServletRequest request) {
        // 获取session域对象
        HttpSession session = request.getSession();
        // 将session域对象中属性是"user"的值清除
        // 则下次前端通过获取当前登录用户接口获取时，获取到的就是空对象，那么会提示用户进行重新登录
        session.removeAttribute(Constants.User.CURRENT_USER);
    }

}
