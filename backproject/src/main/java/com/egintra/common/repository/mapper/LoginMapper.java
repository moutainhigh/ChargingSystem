package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.RightUserSaveReqDTO;
import com.egintra.common.dto.SysUserDTO;
import com.egintra.common.repository.entity.RightUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper extends BaseMapper<RightUser> {

    /**
     * 登录查询验证用户信息
     *
     * @param rightUser 查询条件
     * @return 结果
     */
    RightUser queryUserInfo(@Param("req") RightUser rightUser);

    /**
     * 根据用户ID查询验证用户信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    RightUser queryUserInfoById(@Param("userId") String userId);

    /**
     * 查询验证用户信息
     *
     * @return 结果
     */
    List<RightUser> queryUserInfos();

    /**
     * 根据条件查询用户信息
     *
     * @param rightUserDTO 请求对象
     * @return 结果
     */
    List<RightUser> querySomeUsers(@Param("req") RightUserDTO rightUserDTO);

    /**
     * 更新密码
     *
     * @param rightUser 原数据信息
     * @param newPwd    新密码
     * @return 更新结果
     */
    int updatePwd(@Param("req") RightUser rightUser, @Param("newPwd") String newPwd);

    /**
     * 更新用户信息
     *
     * @param sysUsers 参数
     * @return 结果
     */
    int batchUpdateUsers(@Param("list") List<RightUserSaveReqDTO> sysUsers);

    /**
     * 删除用户信息
     *
     * @param sysUserDTO 参数
     * @return 结果
     */
    int deleteUser(@Param("req") SysUserDTO sysUserDTO);

    /**
     * 新增用户信息
     *
     * @param rightUser 参数
     * @return 结果
     */
    int insertUser(@Param("req") RightUser rightUser);


    /**
     * 获取当前用户信息
     * @param rightUserDTO
     * @return
     */
    RightUser getUserById(RightUserDTO rightUserDTO);
}
