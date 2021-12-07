package com.egintra.feeService.business.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.business.BusinessPaymentReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.business.service.BusinessService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 业务缴费接口
 *
 * @author zyt
 * @date 2021/09/03
 */
@RestController
@RequestMapping("/businessPayment")
@ResponseResult
public class BusinessController {


    @Resource
    private BusinessService businessService;

    /**
     * 根据流水号或身份证号 获取要缴费的数据
     *
     * @return 结果
     */
    @RequestMapping(value = "/getBusinessList")
    public DataReuslt getBusinessList(@RequestBody BusinessPaymentReqDTO businessPayment) {
        try {
            return businessService.getBusinessList(businessPayment);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }


    /**
     *  缴费
     *
     * @return 结果
     */
    @RequestMapping(value = "/payBusiness")
    public DataReuslt payBusiness(@RequestBody Map<String, Object> body) {
        try {
            return businessService.payBusiness(body);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }



    /**
     * 获取当前收费消息基本信息
     *
     * @return 结果
     */
    @RequestMapping(value = "/getPayBasicsMessage")
    public DataReuslt getPayBasicsMessage(@RequestBody BusinessPaymentReqDTO businessPayment) {
        try {
            return businessService.getPayBasicsMessage(businessPayment);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }

}
