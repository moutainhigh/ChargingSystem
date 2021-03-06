package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.PubSimplenumberReqDTO;
import com.egintra.common.dto.PubSimplenumberRespDTO;
import com.egintra.common.repository.entity.PubSimplecate;
import com.egintra.common.repository.entity.PubSimplenumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubSimplenumberMapper extends BaseMapper<PubSimplenumber> {

    /**
     * 新增编码
     *
     * @param pubSimplenumber 保存参数
     */
    void insertPubSimplenumber(@Param("req") PubSimplenumber pubSimplenumber);

    /**
     * 初始化编码分类查询
     *
     * @param pubSimplenumberReqDTO 请求参数
     * @return 查询结果
     */
    List<PubSimplenumberRespDTO> init(@Param("req") PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 根据ID更新数据状态为删除状态
     *
     * @param pubSimplenumberReqDTO 请求参数
     * @return 更新结果
     */
    int updateCodeById(@Param("req") PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 批量根据ID更新数据状态为删除状态
     *
     * @param codeIds 请求参数
     * @return 更新结果
     */
    int batchUpdateCodeById(@Param("list") List<String> codeIds);

    /**
     * 根据编码名称和编码分类查询编码
     *
     * @param pubSimplenumberReqDTO 参数
     * @return 查询结果
     */
    List<PubSimplenumberRespDTO> queryCodes(@Param("req") PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 根据编码Id和code查询编码
     *
     * @param pubSimplenumberReqDTO 参数
     * @return 查询结果
     */
    PubSimplenumberRespDTO queryCodesByIdAndCd(@Param("req") PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 根据编码code查询编码
     *
     * @param pubSimplenumberReqDTO 参数
     * @return 查询结果
     */
    List<PubSimplenumberRespDTO> queryCodesByCd(@Param("req") PubSimplenumberReqDTO pubSimplenumberReqDTO);

    /**
     * 根据编码分类code查询编码
     *
     * @param cateCd 编码分类code
     * @return 查询结果
     */
    List<PubSimplenumberRespDTO> queryNumbersByCateCd(@Param("cateCd") String cateCd);

    /**
     * 根据ID更新数据
     *
     * @param pubSimplenumber 请求参数
     * @return 更新结果
     */
    int updateCodeInfoById(@Param("req")  PubSimplenumber pubSimplenumber);

    /**
     * 获取code值信息
     * @param pubSimplenumberRespDTO
     * @return
     */
    PubSimplenumberRespDTO getPubSimplenumberRespDTOByParam(PubSimplenumberRespDTO pubSimplenumberRespDTO);
}
