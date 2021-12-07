package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.pay.FeePayparaReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.FeePayparaService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 线上支付参数设置相关接口
 *
 * @author liushihao
 * @date 2021/08/06
 */
@RestController
@RequestMapping("/feePaypara")
@ResponseResult
public class FeePayparaController {

    @Resource
    private FeePayparaService feePayparaService;

    /**
     * 获取该管理部门下对应的线上缴费账套号、线上支付银行参数信息
     *
     * @param feePayparaReqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "/queryPayInfo")
    public DataReuslt queryPayInfo(@RequestBody FeePayparaReqDTO feePayparaReqDTO) {

        return feePayparaService.queryPayInfo(feePayparaReqDTO);
    }
}
