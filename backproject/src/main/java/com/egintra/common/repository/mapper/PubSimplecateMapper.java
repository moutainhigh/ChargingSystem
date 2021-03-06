package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.PubSimplecateReqDTO;
import com.egintra.common.dto.PubSimplecateRespDTO;
import com.egintra.common.repository.entity.PubSimplecate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubSimplecateMapper extends BaseMapper<PubSimplecate> {

    /**
     * 新增编码分类
     *
     * @param pubSimplecate 保存参数
     */
    void insertPubSimplecate(@Param("req") PubSimplecate pubSimplecate);

    /**
     * 初始化编码分类查询
     *
     * @return 查询结果
     */
    List<PubSimplecateRespDTO> init();

    /**
     * 根据编码分类名称查询
     *
     * @param pubSimplecateRespDTO 参数
     * @return 查询结果
     */
    List<PubSimplecateRespDTO> queryCodeSortByNm(@Param("req") PubSimplecateReqDTO pubSimplecateRespDTO);

    /**
     * 根据编码分类信息查询数据
     *
     * @param pubSimplecateRespDTO 参数
     * @return 查询结果
     */
    PubSimplecateRespDTO queryCodeSorts(@Param("req") PubSimplecateReqDTO pubSimplecateRespDTO);

    /**
     * 根据编码分类code查询
     *
     * @param pubSimplecateRespDTO 参数
     * @return 查询结果
     */
    List<PubSimplecateRespDTO> queryCodeSortByCd(@Param("req") PubSimplecateReqDTO pubSimplecateRespDTO);

    /**
     * 初始化编码分类查询
     *
     * @param pubSimplecateRespDTO 参数
     * @return 查询结果
     */
    List<PubSimplecateRespDTO> initParams(PubSimplecateReqDTO pubSimplecateRespDTO);

    /**
     * 删除
     *
     * @param pubSimplecateReqDTO 请求参数
     * @return 更新结果
     */
    int deleteCodeSortById(@Param("req") PubSimplecateReqDTO pubSimplecateReqDTO);

    /**
     * 根据ID更新数据
     *
     * @param pubSimplecate 请求参数
     * @return 更新结果
     */
    int updateCodeSortById(@Param("req") PubSimplecate pubSimplecate);
}
