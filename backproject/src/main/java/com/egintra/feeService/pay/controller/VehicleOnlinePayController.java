package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.VehicleOnlinePayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 机动车线上缴费接口
 *
 * @author zyt
 * @date 2021/08/09
 */
@RestController
@RequestMapping("/vehicleOnlinePay")
@ResponseResult
public class VehicleOnlinePayController {

    @Resource
    private VehicleOnlinePayService vehicleOnlinePayService;

    /**
     * 查询机动车需要缴费的数据
     *
     * @return 结果
     */
    @RequestMapping(value = "/getVehicleOnlinePay")
    public DataReuslt getVehicleOnlinePay(@RequestBody InvoiceReqDTO invoiceReqDTO) {
        try {
            return vehicleOnlinePayService.getVehicleOnlinePay(invoiceReqDTO.getLsh());
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }

    /**
     * 机动车线上支付接口
     *
     * @return 结果
     */
    @RequestMapping(value = "/onlinePayment")
    public DataReuslt OnlinePayment(@RequestBody InvoiceReqDTO invoiceReqDTO) {
        try {
            return vehicleOnlinePayService.onlinePayment(invoiceReqDTO.getLsh());
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("缴费失败");
        }
    }
}
