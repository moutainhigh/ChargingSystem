package com.egintra.feeService.reportForm.service;

import com.egintra.common.dto.reportForm.QueryReportReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 * 材料消耗明细报表Service
 *
 * @author liushihao
 * @date 2021/11/10
 */
public interface ConsumeMaterialDetailService {

    /**
     * 查询报表信息
     *
     * @param reqDTO 请求对象
     * @return 结果
     */
    public DataReuslt queryConsumeMaterial(QueryReportReqDTO reqDTO);
}
