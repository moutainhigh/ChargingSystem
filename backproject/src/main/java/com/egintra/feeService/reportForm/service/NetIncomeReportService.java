package com.egintra.feeService.reportForm.service;

import com.egintra.common.dto.reportForm.QueryReportReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 * 净收入报表Service
 *
 * @author liushihao
 * @date 2021/11/10
 */
public interface NetIncomeReportService {

    /**
     * 查询报表信息
     *
     * @param reqDTO 请求对象
     * @return 结果
     */
    public DataReuslt queryReportInfo(QueryReportReqDTO reqDTO);
}
