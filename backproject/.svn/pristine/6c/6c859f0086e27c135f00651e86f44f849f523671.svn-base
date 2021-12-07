package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.RightFuncrightReqDTO;
import com.egintra.common.dto.RightFuncrightRespDTO;
import com.egintra.common.repository.entity.RightFuncright;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightFuncrightMapper extends BaseMapper<RightFuncright> {

    /**
     * 批量插入功能权限数据
     *
     * @param inserts 数据集合
     * @return 结果
     */
    int batchInsertFuncrights(@Param("list") List<RightFuncright> inserts);

    /**
     * 批量删除功能权限数据
     *
     * @param deletes 数据集合
     * @return 结果
     */
    int batchDeleteFuncrights(@Param("list") List<RightFuncright> deletes);

    /**
     * 根据角色ID查询功能权限数据
     *
     * @param rightFuncrightReqDTO 参数
     * @return 结果
     */
    List<RightFuncrightRespDTO> queryFuncrights(@Param("req") RightFuncrightReqDTO rightFuncrightReqDTO);
}
