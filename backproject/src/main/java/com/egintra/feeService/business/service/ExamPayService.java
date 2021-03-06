package com.egintra.feeService.business.service;

import com.egintra.common.dto.pay.PayExaminationFeeReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 * 考试缴费service
 *
 * @author liushihao
 * @date 2021/9/10
 */
public interface ExamPayService {

    /**
     * 查询考试缴费信息
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    public DataReuslt queryExamPayInfo(PayExaminationFeeReqDTO reqDTO);


    /**
     * 确认支付
     *
     * @param reqDTO 参数
     * @return 查询结果
     */
    public DataReuslt examPay(PayExaminationFeeReqDTO reqDTO);
}
