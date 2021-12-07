package com.egintra.feeService.business.service;

import com.egintra.common.dto.business.BusinessPaymentReqDTO;
import com.egintra.common.dto.business.DUnlockReqDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.Map;

public interface BusinessService {

    /**
     * 根据流水号或身份证号 获取要缴费的数据
     *
     * @param businessPayment
     * @return
     */
    DataReuslt getBusinessList(BusinessPaymentReqDTO businessPayment);

    /**
     * 机动车，驾驶证缴费
     *
     * @param body
     * @return
     */
    DataReuslt payBusiness(Map<String, Object> body);

    /**
     * 收费项目基础信息
     *
     * @param businessPayment
     * @return
     */
    DataReuslt getPayBasicsMessage(BusinessPaymentReqDTO businessPayment);

    /**
     * 考试费项目转换
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public String getFeeCodeCk(String projectId);

    /**
     * 补考费项目转换
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public String getFeeCodeBk(String projectId);

    /**
     * 解锁 写入驾驶证信息
     *
     * @param dUnlock 数据对象
     * @return 结果
     */
    public Map<String, String> driverLicenseUnlock(DUnlockReqDTO dUnlock);
}
