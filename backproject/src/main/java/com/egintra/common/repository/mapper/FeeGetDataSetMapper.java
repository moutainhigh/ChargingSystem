package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.sys.FeeGetDataSetReqDTO;
import com.egintra.common.dto.sys.FeeGetDataSetRespDTO;
import com.egintra.common.dto.sys.FeeGetDataSetSaveReqDTO;
import com.egintra.common.repository.entity.FeeGetDataSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeeGetDataSetMapper extends BaseMapper<FeeGetDataSet> {

    /**
     * 查询数据设置结果
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 返回结果
     */
    List<FeeGetDataSetRespDTO> queryDataSet(@Param("req") FeeGetDataSetReqDTO feeGetDataSetReqDTO);

    /**
     * 查询数据设置结果
     *
     * @param modelid 请求参数
     * @return 返回结果
     */
    FeeGetDataSetRespDTO getOneByModelId(@Param("modelid") String modelid);

    /**
     * 新增数据参数设置
     *
     * @param feeGetDataSetSaveReqDTO 请求参数
     */
    void insertDataSet(@Param("req") FeeGetDataSetSaveReqDTO feeGetDataSetSaveReqDTO);

    /**
     * 批量更新数据参数设置
     *
     * @param updates 更新数据集合
     * @return 更新结果
     */
    int batchUpdateDataSets(@Param("list") List<FeeGetDataSetSaveReqDTO> updates);

    /**
     * 新增数据参数设置
     *
     * @param feeGetDataSetReqDTO 请求参数
     */
    void deleteDataSet(@Param("req") FeeGetDataSetReqDTO feeGetDataSetReqDTO);
}
