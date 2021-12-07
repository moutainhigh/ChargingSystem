package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.pay.PayExaminationFeeReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.ExaminationFeeOnLineService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 考试费线上缴费模块
 *
 * @author liushihao
 * @date 2021/8/9
 */
@RestController
@RequestMapping(value = "/examinationFeeOnLine")
@ResponseResult
public class ExaminationFeeOnLineController {

    @Resource
    private ExaminationFeeOnLineService examinationFeeOnLineService;

    /**
     * 考试费线上缴费查询
     *
     * @param payExaminationFeeReqDTO 请求参数
     * @return 结果
     */
    @RequestMapping(value = "queryExaminationFee")
    public DataReuslt queryExaminationFee(@RequestBody PayExaminationFeeReqDTO payExaminationFeeReqDTO) {

        return examinationFeeOnLineService.queryExaminationFee(payExaminationFeeReqDTO);
    }
}
