package com.egintra.feeService.business.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.business.service.OtherPayService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 其他缴费业务接口
 *
 * @author zyt
 * @date 2021/09/08
 */
@RestController
@RequestMapping("/otherPay")
@ResponseResult
public class OtherPayController {

    @Resource
    private OtherPayService otherPayService;

    /**
     *  缴费
     *
     * @return 结果
     */
    @RequestMapping(value = "/payBusiness")
    public DataReuslt payBusiness(@RequestBody Map<String, Object> body) {
        try {
            return otherPayService.otherPay(body);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("缴费失败");
        }
    }

}
