package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.FeeInterfaceTwoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 获取驾驶证业务缴费信息服务接口
 *
 * @author liushihao
 * @date 2021/8/6
 */
@RestController
@RequestMapping(value = "/feeInterfaceTwo")
@ResponseResult
public class FeeInterfaceTwoController {

    @Resource
    private FeeInterfaceTwoService feeInterfaceTwoService;

    /**
     * 获取驾驶证业务缴费信息服务接口
     *
     * @param feeDrvjkReqDTO 请求参数
     * @return 结果
     */
    @RequestMapping(value = "/driverLicensePayment")
    public DataReuslt driverLicensePaymentInfo(@RequestBody FeeDrvjkReqDTO feeDrvjkReqDTO) {

        return feeInterfaceTwoService.driverLicensePaymentInfo(feeDrvjkReqDTO);
    }
}
