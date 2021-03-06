package com.egintra.feeService.pay.service;

import com.egintra.common.dto.pay.PayExaminationFeeReqDTO;
import com.egintra.common.utils.DataReuslt;

public interface ExaminationFeeOnLineService {

    /**
     * 考试费线上缴费接口
     *
     * @param payExaminationFeeReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt queryExaminationFee(PayExaminationFeeReqDTO payExaminationFeeReqDTO);
}
