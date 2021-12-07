package com.egintra.feeService.reportForm.service;

import com.egintra.common.dto.reportForm.ReportReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 * @author zhangyunting
 * @date 2021/11/10
 */
public interface PCStatisticalReportService {


    /**
     * 查询发票详情相关的信息
     *
     * @param reportReqDTO
     * @return
     */
    DataReuslt queryPCReport(ReportReqDTO reportReqDTO);


    /**
     * 获取当前的用户等级
     *
     * @return
     */
    DataReuslt getUserLevel();
}
