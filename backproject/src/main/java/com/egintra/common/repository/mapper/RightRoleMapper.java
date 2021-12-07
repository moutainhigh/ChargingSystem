package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.RightRoleReqDTO;
import com.egintra.common.dto.RightRoleRespDTO;
import com.egintra.common.repository.entity.RightRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightRoleMapper extends BaseMapper<RightRole> {

    /**
     * 查询所有角色信息
     *
     * @return 查询结果
     */
    List<RightRoleRespDTO> queryAllRoles();

    /**
     * 查询角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 查询结果
     */
    List<RightRoleRespDTO> queryRole(@Param("req") RightRoleReqDTO rightRoleReqDTO);

    /**
     * 根据ID和name批量查询角色信息
     *
     * @param rightRoles 请求对象
     * @return 查询结果
     */
    List<RightRoleRespDTO> batchQueryRoles(@Param("list") List<RightRoleReqDTO> rightRoles);

    /**
     * 根据ID和name批量查询角色信息
     *
     * @param names 请求对象
     * @return 查询结果
     */
    List<RightRoleRespDTO> batchQueryRolesByNames(@Param("list") List<String> names);

    /**
     * 删除角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    int deleteRole(@Param("req") RightRoleReqDTO rightRoleReqDTO);

    /**
     * 批量更新角色信息
     *
     * @param rightRoles 请求对象
     * @return 结果
     */
    int batchUpdateRole(@Param("list") List<RightRoleReqDTO> rightRoles);

    /**
     * 新增角色
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    int insertRole(@Param("req") RightRoleReqDTO rightRoleReqDTO);
}
