package com.egintra.feeService.business.controller;

import com.egintra.common.dto.pay.PayExaminationFeeReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.business.service.ExamPayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 考试缴费controller
 *
 * @author liushihao
 * @date 2021/9/10
 */
@RestController
@RequestMapping(value = "/examPay")
public class ExamPayController {

    @Resource
    private ExamPayService examPayService;

    /**
     * 查询考试缴费信息
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    @SysToken
    @RequestMapping(value = "/queryExamPayInfo")
    public DataReuslt queryExamPayInfo(@RequestBody PayExaminationFeeReqDTO reqDTO) {

        return examPayService.queryExamPayInfo(reqDTO);
    }

    /**
     * 确认支付
     *
     * @param reqDTO 参数
     * @return 查询结果
     */
    @SysToken
    @RequestMapping(value = "/pay")
    public DataReuslt pay(@RequestBody PayExaminationFeeReqDTO reqDTO) {

        return examPayService.examPay(reqDTO);
    }
}
