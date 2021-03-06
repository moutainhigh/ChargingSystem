package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.VehiclePayReqDTO;
import com.egintra.common.repository.mapper.VehiclePayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.VehiclePayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class VehiclePayServiceImpl implements VehiclePayService {

    @Resource
    private VehiclePayMapper vehiclePayMapper;

    /**
     * 获取机动车业务缴费信息服务信息
     *
     * @param vehiclePayReqDTO
     * @return
     */
    @Override
    public DataReuslt getVehiclePayList(VehiclePayReqDTO vehiclePayReqDTO) {
        List<VehiclePayReqDTO> vehiclePayList = vehiclePayMapper.getVehiclePayList(vehiclePayReqDTO);
        return DataReuslt.success(vehiclePayList);
    }
}
