package com.egintra.feeService.queryStatistics.service;

import com.egintra.common.dto.queryStatistics.QueryStatisticsReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 * 查询统计-收费统计Service
 *
 * @author liushihao
 * @date 2021/9/16
 */
public interface ChargeStatisticsService {

    /**
     * 收费统计-查询
     *
     * @param queryStatisticsReqDTO 查询条件
     * @return 结果
     */
    public DataReuslt queryStatistics(QueryStatisticsReqDTO queryStatisticsReqDTO);

    /**
     * 收费统计-导出查询
     *
     * @param queryReqDTO 查询条件
     * @return 结果
     */
    public DataReuslt exportStatistics(QueryStatisticsReqDTO queryReqDTO);
}
