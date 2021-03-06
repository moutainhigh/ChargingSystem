package com.egintra.feeService.sys.user.service;

import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.RightUserSaveReqDTO;
import com.egintra.common.dto.SysUserDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface SysUserService {

    /**
     * 根据用户ID查询已配置的角色
     *
     * @param rightUserDTO 参数
     * @return 结果
     */
    public DataReuslt getUserAllRoles(RightUserDTO rightUserDTO);

    /**
     * 更新用户角色信息
     *
     * @param rightRoleUserReqDTO 请求对象
     * @return 处理结果
     */
    public DataReuslt updateRoleUserInfo(RightRoleUserReqDTO rightRoleUserReqDTO);

    /**
     * 查询所有用户信息
     *
     * @param sysUserDTO 查询条件
     * @return 结果
     */
    public DataReuslt queryUserInfo(SysUserDTO sysUserDTO);

    /**
     * 根据条件查询用户信息
     *
     * @param rightUserDTO 查询条件
     * @return 结果
     */
    public DataReuslt querySomeUsers(RightUserDTO rightUserDTO);

    /**
     * 修改密码
     *
     * @param rightUserDTO 请求对象
     * @return 结果
     */
    public DataReuslt changePwd(RightUserDTO rightUserDTO);

    /**
     * 查询当前用户信息
     *
     * @param sysUserDTO 入参对象
     * @return 查询结果
     */
    public DataReuslt getCurUserInfo(SysUserDTO sysUserDTO);

    /**
     * 更新用户信息
     *
     * @param sysUsers 参数
     * @return 结果
     */
    public DataReuslt updateUser(List<RightUserSaveReqDTO> sysUsers);

    /**
     * 删除用户信息
     *
     * @param sysUserDTO 参数
     * @return 结果
     */
    public DataReuslt deleteUser(SysUserDTO sysUserDTO);

    /**
     * 新增用户信息
     *
     * @param saveReqDTO 参数
     * @return 结果
     */
    public DataReuslt addUser(RightUserSaveReqDTO saveReqDTO);
}
