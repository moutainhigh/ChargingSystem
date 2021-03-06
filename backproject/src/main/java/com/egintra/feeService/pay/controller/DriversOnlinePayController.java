package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.DriversOnlinePayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 驾驶证线上缴费接口
 *
 * @author zyt
 * @date 2021/08/10
 */
@RestController
@RequestMapping("/driversOnlinePay")
@ResponseResult
public class DriversOnlinePayController {

    @Resource
    private DriversOnlinePayService driversOnlinePayService;

    /**
     * 查询驾驶证需要缴费的数据
     *
     * @return 结果
     */
    @RequestMapping(value = "/getDriversOnlinePay")
    public DataReuslt getDriversOnlinePay(@RequestBody InvoiceReqDTO invoiceReqDTO) {
        try {
            return driversOnlinePayService.getDriversOnlinePay(invoiceReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }

    /**
     * 驾驶证线上支付接口
     *
     * @return 结果
     */
    @RequestMapping(value = "/onlinePayment")
    public DataReuslt OnlinePayment(@RequestBody InvoiceReqDTO invoiceReqDTO) {
        try {
            return driversOnlinePayService.onlinePayment(invoiceReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("缴费失败");
        }
    }
}
