package com.egintra.feeService.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 银行对接接口类
 *
 * @author liushihao
 * @date 2021/8/12
 */
@Controller
@RequestMapping(value = "/dockingBank")
public class DockingBankController {

    /**
     * 线上考试费缴费打开H5页面
     *
     * @return 结果
     */
    @RequestMapping(value = "/openExamination")
    public String openExamPay() {

        return "examPaymentAction";
    }

    /**
     * 线上业务缴费打开H5页面
     *
     * @return
     */
    @RequestMapping(value = "/openBusPayment")
    public String openBusPay() {

        return "busPaymentAction";
    }

    /**
     * 缴费
     *
     * @return
     */
    @RequestMapping(value = "/businessPay")
    public String BusinessPay() {

        return "businessPay";
    }

    /**
     * 线上其他缴费打开H5页面
     *
     * @return
     */
    @RequestMapping(value = "/openOtherPayment")
    public String openOtherPay() {

        return "otherPaymentAction";
    }

    /**
     * 查询费用
     *
     * @return
     */
    @RequestMapping(value = "/queryPayInfo")
    public String queryPayInfo() {

        return "index";
    }
}
