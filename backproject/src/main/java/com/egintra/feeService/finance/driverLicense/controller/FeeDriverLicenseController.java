package com.egintra.feeService.finance.driverLicense.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.finance.FeeDrvjkChangeReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.pointcuts.SysLog;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.finance.driverLicense.service.FeeDriverLicenseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 驾驶证业务收费信息设置：初始化、新增、修改、删除、查询
 *
 * @author liushihao
 * @date 2021/7/28
 */
@RestController
@RequestMapping("/driverLicense")
@ResponseResult
public class FeeDriverLicenseController {

    @Resource
    private FeeDriverLicenseService feeDriverLicenseService;

    /**
     * 初始化
     *
     * @param feeDrvjkReqDTO 参数
     * @return 初始化结果
     */
    @SysToken
    @SysLog
    @RequestMapping(value = "/driverLicensesInit")
    public DataReuslt driverLicenseInit(@RequestBody FeeDrvjkReqDTO feeDrvjkReqDTO) {

        return feeDriverLicenseService.driverLicenseInit(feeDrvjkReqDTO);
    }

    /**
     * 新增
     *
     * @param reqDTO 请求对象
     * @return 返回结果
     */
    @SysToken
    @SysLog
    @RequestMapping(value = "/addDriverLicense")
    public DataReuslt addDriverLicense(@RequestBody FeeDrvjkChangeReqDTO reqDTO) {

        return feeDriverLicenseService.addDriverLicense(reqDTO);
    }

    /**
     * 修改
     *
     * @param reqDTO 请求对象
     * @return 返回结果
     */
    @SysToken
    @SysLog
    @RequestMapping(value = "/updateDriverLicenses")
    public DataReuslt updateDriverLicense(@RequestBody FeeDrvjkChangeReqDTO reqDTO) {

        return feeDriverLicenseService.updateDriverLicense(reqDTO);
    }

    /**
     * 删除
     *
     * @param feeDrvjkReqDTO 请求对象
     * @return 返回结果
     */
    @SysToken
    @SysLog
    @RequestMapping(value = "/deleteDriverLicense")
    public DataReuslt deleteDriverLicense(@RequestBody FeeDrvjkReqDTO feeDrvjkReqDTO) {

        return feeDriverLicenseService.deleteDriverLicense(feeDrvjkReqDTO);
    }

    /**
     * 驾驶证业务收费新增页面初始化获取下拉框数据
     *
     * @return 返回结果
     */
    @SysToken
    @SysLog
    @RequestMapping(value = "/getSelectedData")
    public DataReuslt getSelectedData() {

        return feeDriverLicenseService.getSelectedData();
    }

    /**
     * 驾驶证业务收费编辑页面初始化已设置收费项目明细
     *
     * @param reqDTO 请求参数
     * @return 返回结果
     */
    @SysToken
    @RequestMapping(value = "/chargeableProjects")
    public DataReuslt chargeableProjects(@RequestBody FeeDrvjkChangeReqDTO reqDTO) {

        return feeDriverLicenseService.chargeableProjects(reqDTO);
    }
}
