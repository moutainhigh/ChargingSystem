package com.egintra.feeService.sys.menu.service;

import com.egintra.common.dto.FunctionReqDTO;
import com.egintra.common.dto.RightFunccateReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.utils.DataReuslt;

public interface MenuService {

    /**
     * 根据权限获取对应菜单树
     *
     * @param rightUserDTO 用户信息
     * @return
     */
    public DataReuslt getCurRoleMenus(RightUserDTO rightUserDTO);

    /**
     * 获取菜单
     *
     * @return 菜单信息
     */
    public DataReuslt getMenus();

    /**
     * 查询分类菜单列表
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt getFunccate(RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 查询分类菜单列表
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果/
     */
    public DataReuslt getFunccateList(RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 查询当前分类菜单下功能菜单列表
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt getFuncMenus(FunctionReqDTO functionReqDTO);

    /**
     * 根据分类菜单获取当前角色下对应的功能菜单列表
     *
     * @param functionReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt getFunctionMenus(FunctionReqDTO functionReqDTO);

    /**
     * 新增分类菜单
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt addFunccateMenu(RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 新增功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt addFunctionMenu(FunctionReqDTO functionReqDTO);

    /**
     * 修改分类菜单
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt updateFunccateMenu(RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 修改功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt updateFunctionMenu(FunctionReqDTO functionReqDTO);

    /**
     * 删除分类菜单（注意提示将会删除所有下级）
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt deleteFunccateMenu(RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 删除功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt deleteFunctionMenu(FunctionReqDTO functionReqDTO);

    /**
     * 根据功能菜单获取按钮列表
     *
     * @param functionReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt getFuncActions(FunctionReqDTO functionReqDTO);
}
