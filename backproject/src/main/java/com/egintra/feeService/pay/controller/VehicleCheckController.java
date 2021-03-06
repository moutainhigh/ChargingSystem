package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.VehiclePayReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.VehiclePayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 获取机动车业务缴费信息服务接口
 *
 * @author zyt
 * @date 2021/08/06
 */
@RestController
@RequestMapping("/vehiclePay")
@ResponseResult
public class VehicleCheckController {

    @Resource
    private VehiclePayService vehiclePayService;

    /**
     * 获取机动车业务缴费信息服务信息
     *
     * @return 结果
     */
    @RequestMapping(value = "/getVehiclePayList")
    public DataReuslt VehiclePayList(@RequestBody VehiclePayReqDTO vehiclePayReqDTO) {
        try {
            return vehiclePayService.getVehiclePayList(vehiclePayReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }
}
