package com.egintra.feeService.finance.driverLicense.service;

import com.egintra.common.dto.finance.FeeDrvjkChangeReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.utils.DataReuslt;

public interface FeeDriverLicenseService {

    /**
     * 初始化
     *
     * @param feeDrvjkReqDTO 参数
     * @return 初始化结果
     */
    public DataReuslt driverLicenseInit(FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 新增
     *
     * @param reqDTO 请求对象
     * @return 返回结果
     */
    public DataReuslt addDriverLicense(FeeDrvjkChangeReqDTO reqDTO);

    /**
     * 修改
     *
     * @param reqDTO 请求对象
     * @return 返回结果
     */
    public DataReuslt updateDriverLicense(FeeDrvjkChangeReqDTO reqDTO);

    /**
     * 删除
     *
     * @param feeDrvjkReqDTO 请求对象
     * @return 返回结果
     */
    public DataReuslt deleteDriverLicense(FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 查询
     *
     * @param feeDrvjkReqDTO 请求对象
     * @return 返回结果
     */
    public DataReuslt queryDriverLicense(FeeDrvjkReqDTO feeDrvjkReqDTO);

    /**
     * 驾驶证业务收费新增页面初始化获取下拉框数据
     *
     * @return 返回结果
     */
    public DataReuslt getSelectedData();

    /**
     * 驾驶证业务收费编辑页面初始化已设置收费项目明细
     *
     * @param reqDTO 请求参数
     * @return 返回结果
     */
    public DataReuslt chargeableProjects(FeeDrvjkChangeReqDTO reqDTO);
}
