package com.egintra.feeService.pay.service;

import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.utils.DataReuslt;

public interface FeeInterfaceTwoService {

    /**
     * 获取驾驶证业务缴费信息服务接口
     *
     * @param feeDrvjkReqDTO 请求参数
     * @return 结果
     */
    public DataReuslt driverLicensePaymentInfo(FeeDrvjkReqDTO feeDrvjkReqDTO);
}
