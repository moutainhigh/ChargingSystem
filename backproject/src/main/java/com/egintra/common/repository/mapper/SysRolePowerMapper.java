package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.SysRolePowerReqDTO;
import com.egintra.common.dto.SysRolePowerRespDTO;
import com.egintra.common.repository.entity.SysRolePower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePowerMapper extends BaseMapper<SysRolePower> {

    /**
     * 查询角色功能关系数据
     *
     * @param sysRolePowerReqDTO 请求对象
     * @return 返回结果
     */
    List<SysRolePowerRespDTO> queryRolePower(@Param("req") SysRolePowerReqDTO sysRolePowerReqDTO);

    /**
     * 批量插入角色功能关系数据
     *
     * @param list 数据集合
     * @return 结果
     */
    int batchInsertRolePowers(@Param("list") List<SysRolePowerReqDTO> list);

    /**
     * 批量删除角色功能关系数据
     *
     * @param ids 数据集合
     * @return 结果
     */
    int batchDeleteRolePowers(@Param("list") List<String> ids, @Param("status") String status);
}
