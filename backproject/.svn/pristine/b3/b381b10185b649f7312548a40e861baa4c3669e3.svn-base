package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.BankCallbackService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 银行回调接口
 *
 * @author zyt
 * @date 2021/08/12
 */
@RestController
@RequestMapping("/callBack")
@ResponseResult
public class BankCallbackController {

    @Resource
    private BankCallbackService bankCallbackService;

    /**
     * 银行回调接口
     *
     * @return 结果
     */
    @RequestMapping(value = "/callBack")
    public DataReuslt getDriversOnlinePay(String data) {
        try {
            return bankCallbackService.addBankData(data);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }
}
