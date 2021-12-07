package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.dto.RightRoleUserRespDTO;
import com.egintra.common.dto.sys.RightRoleUserSaveDTO;
import com.egintra.common.repository.entity.RightRoleuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightRoleuserMapper extends BaseMapper<RightRoleuser> {

    /**
     * 查询角色用户信息
     *
     * @param rightRoleUserReqDTO 参数
     * @return 结果
     */
    List<RightRoleUserRespDTO> queryRoleUsers(@Param("req") RightRoleUserReqDTO rightRoleUserReqDTO);

    /**
     * 批量删除用户角色关系数据
     *
     * @param ids 数据集合
     * @return 结果
     */
    int batchDeleteUserRoles(@Param("list") List<String> ids, @Param("status") String status);

    /**
     * 批量插入用户角色关系数据
     *
     * @param list 数据集合
     * @return 结果
     */
    int batchInsertUserRoles(@Param("list") List<RightRoleUserSaveDTO> list);
}
