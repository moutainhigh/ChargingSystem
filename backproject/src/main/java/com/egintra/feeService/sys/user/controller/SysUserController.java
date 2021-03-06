package com.egintra.feeService.sys.user.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.RightUserSaveReqDTO;
import com.egintra.common.dto.SysUserDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.user.service.SysUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户信息相关接口
 *
 * @author liushihao
 * @date 2021/7/30
 */
@RestController
@RequestMapping("/sysUser")
@ResponseResult
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 根据用户ID查询已配置的角色
     *
     * @param rightUserDTO 参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getUserAllRoles")
    public DataReuslt getUserAllRoles(@RequestBody RightUserDTO rightUserDTO) {

        return sysUserService.getUserAllRoles(rightUserDTO);
    }

    /**
     * 更新用户分配角色
     *
     * @param rightRoleUserReqDTO 请求对象
     * @return 处理结果
     */
    @SysToken
    @RequestMapping(value = "/updateRoleUserInfo")
    public DataReuslt updateRoleUserInfo(@RequestBody RightRoleUserReqDTO rightRoleUserReqDTO) {

        return sysUserService.updateRoleUserInfo(rightRoleUserReqDTO);
    }

    /**
     * 初始化查询所有用户信息
     *
     * @param sysUserDTO 查询条件
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/queryUserInfo")
    public DataReuslt queryUserInfo(@Valid @RequestBody SysUserDTO sysUserDTO) {

        return sysUserService.queryUserInfo(sysUserDTO);
    }

    /**
     * 根据条件查询用户信息
     *
     * @param rightUserDTO 查询条件
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/querySomeUsers")
    public DataReuslt querySomeUsers(@RequestBody RightUserDTO rightUserDTO) {

        return sysUserService.querySomeUsers(rightUserDTO);
    }

    /**
     * 修改密码
     *
     * @param rightUserDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/changePwd")
    public DataReuslt changePwd(@RequestBody RightUserDTO rightUserDTO) {

        return sysUserService.changePwd(rightUserDTO);
    }

    /**
     * 查询当前用户信息
     *
     * @param sysUserDTO 入参对象
     * @return 查询结果
     */
    @SysToken
    @RequestMapping(value = "/getCurUserInfo")
    public DataReuslt getCurUserInfo(@RequestBody SysUserDTO sysUserDTO) {

        return sysUserService.getCurUserInfo(sysUserDTO);
    }

    /**
     * 更新用户信息
     *
     * @param sysUsers 参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/updateUser")
    public DataReuslt updateUser(@RequestBody List<RightUserSaveReqDTO> sysUsers) {

        return sysUserService.updateUser(sysUsers);
    }

    /**
     * 删除用户信息
     *
     * @param sysUserDTO 参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteUser")
    public DataReuslt deleteUser(@RequestBody SysUserDTO sysUserDTO) {

        return sysUserService.deleteUser(sysUserDTO);
    }

    /**
     * 新增用户信息
     *
     * @param saveReqDTO 参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addUser")
    public DataReuslt addUser(@Valid @RequestBody RightUserSaveReqDTO saveReqDTO) {

        return sysUserService.addUser(saveReqDTO);
    }
}
