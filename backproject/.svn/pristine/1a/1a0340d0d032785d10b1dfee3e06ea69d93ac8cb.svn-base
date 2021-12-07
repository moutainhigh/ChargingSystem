package com.egintra.feeService.sys.menu.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.FunctionReqDTO;
import com.egintra.common.dto.RightFunccateReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.menu.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 系统菜单相关接口
 *
 * @author liushihao
 * @date 2021/06/22
 */
@RestController
@RequestMapping(value = "/menu")
@ResponseResult
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 根据权限获取对应菜单树
     *
     * @param rightUserDTO 用户信息
     * @return
     */
    @SysToken
    @RequestMapping(value = "/getCurRoleMenus")
    public DataReuslt getCurRoleMenus(@RequestBody RightUserDTO rightUserDTO) {

        return menuService.getCurRoleMenus(rightUserDTO);
    }

    /**
     * 获取首页菜单树
     *
     * @return 菜单信息
     */
    @SysToken
    @RequestMapping(value = "/getMenus")
    public DataReuslt getMenus() {

        return menuService.getMenus();
    }

    /**
     * 查询分类菜单列表
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getFunccateMenus")
    public DataReuslt getFunccateMenus(@RequestBody RightFunccateReqDTO rightFunccateReqDTO) {

        return menuService.getFunccate(rightFunccateReqDTO);
    }

    /**
     * 查询分类菜单列表
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果/
     */
    @RequestMapping(value = "/getFunccateMenusList")
    public DataReuslt getFunccateMenusList(@RequestBody RightFunccateReqDTO rightFunccateReqDTO) {

        return menuService.getFunccateList(rightFunccateReqDTO);
    }

    /**
     * 查询当前分类菜单下功能菜单列表
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getFuncMenus")
    public DataReuslt getFuncMenus(@RequestBody FunctionReqDTO functionReqDTO) {

        return menuService.getFuncMenus(functionReqDTO);
    }

    /**
     * 根据分类菜单获取当前角色下对应的功能菜单列表
     *
     * @param functionReqDTO 请求参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getFunctionMenus")
    public DataReuslt getFunctionMenus(@RequestBody FunctionReqDTO functionReqDTO) {

        return menuService.getFunctionMenus(functionReqDTO);
    }

    /**
     * 新增分类菜单
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addFunccateMenu")
    public DataReuslt addFunccateMenu(@Valid @RequestBody RightFunccateReqDTO rightFunccateReqDTO) {

        return menuService.addFunccateMenu(rightFunccateReqDTO);
    }

    /**
     * 新增功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addFunctionMenu")
    public DataReuslt addFunctionMenu(@Valid @RequestBody FunctionReqDTO functionReqDTO) {

        return menuService.addFunctionMenu(functionReqDTO);
    }

    /**
     * 修改分类菜单
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/updateFunccateMenu")
    public DataReuslt updateFunccateMenu(@Valid @RequestBody RightFunccateReqDTO rightFunccateReqDTO) {

        return menuService.updateFunccateMenu(rightFunccateReqDTO);
    }

    /**
     * 修改功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/updateFunctionMenu")
    public DataReuslt updateFunctionMenu(@Valid @RequestBody FunctionReqDTO functionReqDTO) {

        return menuService.updateFunctionMenu(functionReqDTO);
    }

    /**
     * 删除分类菜单（注意提示将会删除所有下级）
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteFunccateMenu")
    public DataReuslt deleteFunccateMenu(@Valid @RequestBody RightFunccateReqDTO rightFunccateReqDTO) {

        return menuService.deleteFunccateMenu(rightFunccateReqDTO);
    }

    /**
     * 删除功能菜单
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteFunctionMenu")
    public DataReuslt deleteFunctionMenu(@Valid @RequestBody FunctionReqDTO functionReqDTO) {

        return menuService.deleteFunctionMenu(functionReqDTO);
    }

    /**
     * 根据功能菜单获取按钮列表
     *
     * @param functionReqDTO 请求参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getFuncActions")
    public DataReuslt getFuncActions(@RequestBody FunctionReqDTO functionReqDTO) {

        return menuService.getFuncActions(functionReqDTO);
    }
}