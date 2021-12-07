package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.FunctionReqDTO;
import com.egintra.common.dto.FunctionRespDTO;
import com.egintra.common.repository.entity.RightFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightFunctionMapper extends BaseMapper<RightFunction> {

    /**
     * 查询菜单信息
     *
     * @return 查询结果
     */
    public List<FunctionRespDTO> querycurUserFunctions(@Param("userId") String userId);

    /**
     * 查询菜单信息
     *
     * @return 查询结果
     */
    public List<FunctionRespDTO> queryFunctionInfo(@Param("req") FunctionReqDTO functionReqDTO);

    /**
     * 批量更新状态
     *
     * @param functionIds  功能菜单ID集合
     * @param recordStatus 数据状态
     * @return 结果
     */
    int batchDeleteFunctions(@Param("list") List<String> functionIds, @Param("recordStatus") String recordStatus);

    /**
     * 根据功能菜单ID删除当前功能
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    int deleteFunction(@Param("req") FunctionReqDTO functionReqDTO);


    /**
     * 根据功能菜单ID更新
     *
     * @param functionReqDTO 请求对象
     * @return 结果
     */
    int updateFunction(@Param("req")  FunctionReqDTO functionReqDTO);

}
