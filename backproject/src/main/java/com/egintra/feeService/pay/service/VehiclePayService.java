package com.egintra.feeService.pay.service;

import com.egintra.common.dto.VehiclePayReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 *
 */
public interface VehiclePayService {

    /**
     * 获取机动车业务缴费信息服务信息
     * @param vehiclePayReqDTO
     * @return
     */
    DataReuslt getVehiclePayList(VehiclePayReqDTO vehiclePayReqDTO);
}
