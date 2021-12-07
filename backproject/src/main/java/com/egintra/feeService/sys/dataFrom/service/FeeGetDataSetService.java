package com.egintra.feeService.sys.dataFrom.service;

import com.egintra.common.dto.sys.FeeGetDataSetReqDTO;
import com.egintra.common.dto.sys.FeeGetDataSetSaveReqDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface FeeGetDataSetService {

    /**
     * 初始化查询设置数据
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 查询结果
     */
    public DataReuslt queryDataSets(FeeGetDataSetReqDTO feeGetDataSetReqDTO);

    /**
     * 新增设置
     *
     * @param feeGetDataSetSaveReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt saveDataSet(FeeGetDataSetSaveReqDTO feeGetDataSetSaveReqDTO);

    /**
     * 修改设置
     *
     * @param updates 请求参数
     * @return 结果
     */
    public DataReuslt updateDataSet(List<FeeGetDataSetSaveReqDTO> updates);

    /**
     * 删除设置
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt deleteDataSet(FeeGetDataSetReqDTO feeGetDataSetReqDTO);
}
