package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.FunctionRespDTO;
import com.egintra.common.dto.RightFunccateReqDTO;
import com.egintra.common.repository.entity.RightFunccate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightFunccateMapper extends BaseMapper<RightFunccate> {

    /**
     * 查询菜单信息
     *
     * @return 查询结果
     */
    public List<FunctionRespDTO> queryFunccateInfo(@Param("req") RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 根据分类菜单ID删除
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    int deleteFunccate(@Param("req") RightFunccateReqDTO rightFunccateReqDTO);

    /**
     * 根据分类菜单ID更新
     *
     * @param rightFunccateReqDTO 请求对象
     * @return 结果
     */
    int updateFunccate(@Param("req") RightFunccateReqDTO rightFunccateReqDTO);
}
