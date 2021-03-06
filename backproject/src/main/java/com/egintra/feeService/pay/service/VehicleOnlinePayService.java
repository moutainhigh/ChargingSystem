package com.egintra.feeService.pay.service;

import com.egintra.common.utils.DataReuslt;

public interface VehicleOnlinePayService {

    /**
     * 查询机动车需要缴费的数据
     *
     * @param lsh
     * @return
     */
    DataReuslt getVehicleOnlinePay(String lsh);

    /**
     * 机动车线上缴费
     *
     * @param lsh
     * @return
     */
    DataReuslt onlinePayment(String lsh);
}
