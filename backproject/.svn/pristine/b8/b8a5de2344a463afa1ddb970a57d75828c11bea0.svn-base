package com.egintra.feeService.sys.role.service;

import com.egintra.common.dto.BaseFuncDTO;
import com.egintra.common.dto.RightRoleReqDTO;
import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface RightRoleService {

    /**
     * 查询所有角色信息
     *
     * @return 结果
     */
    public DataReuslt queryAllRoles(RightRoleReqDTO rightRoleReqDTO);

    /**
     * 查询所有角色信息
     *
     * @param baseFuncDTO 参数
     * @return 结果
     */
    public DataReuslt getRoleList(BaseFuncDTO baseFuncDTO);

    /**
     * 查询角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt queryRole(RightRoleReqDTO rightRoleReqDTO);

    /**
     * 新增角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt addRole(RightRoleReqDTO rightRoleReqDTO);

    /**
     * 删除角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    public DataReuslt deleteRole(RightRoleReqDTO rightRoleReqDTO);

    /**
     * 修改角色信息
     *
     * @param rightRoles 请求对象
     * @return 结果
     */
    public DataReuslt updateRole(List<RightRoleReqDTO> rightRoles);

    /**
     * 更新用户角色信息
     *
     * @param rightRoleUserReqDTO 请求对象
     * @return 处理结果
     */
    public DataReuslt updateRoleUserInfo(RightRoleUserReqDTO rightRoleUserReqDTO);
}
