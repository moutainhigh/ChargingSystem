package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.InvoiceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 车驾管业务收费核查接口
 *
 * @author zyt
 * @date 2021/08/06
 */
@RestController
@RequestMapping("/invoice")
@ResponseResult
public class InvoiceController {

    @Resource
    private InvoiceService invoiceService;

    /**
     * 车驾管业务收费核查接口
     *
     * @param invoiceReqDTO
     * @return
     */
    @RequestMapping(value = "/isPaymentNot")
    public DataReuslt isPaymentNot(@RequestBody InvoiceReqDTO invoiceReqDTO) {
        try {
            return invoiceService.isPaymentNot(invoiceReqDTO.getLsh());
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.success("查询失败");
        }
    }
}
