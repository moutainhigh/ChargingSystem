package com.egintra.feeService.business.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.business.FeeInvoiceGiveUpQueryReqDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.business.service.InvoiceGiveUpService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 发票作废controller
 *
 * @author liushihao
 * @date 2021/9/8
 */
@RestController
@RequestMapping(value = "/invoiveGiveUp")
@ResponseResult
public class InvoiceGiveUpController {

    @Resource
    private InvoiceGiveUpService invoiceGiveUpService;

    /**
     * 发票查询
     *
     * @param feeInvoiceGiveUpQueryReqDTO 参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/queryInvoices")
    public DataReuslt queryInvoices(@RequestBody FeeInvoiceGiveUpQueryReqDTO feeInvoiceGiveUpQueryReqDTO) {

        return invoiceGiveUpService.queryInvoices(feeInvoiceGiveUpQueryReqDTO);
    }

    /**
     * 发票作废
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/voidInvoices")
    public DataReuslt voidInvoices(@Valid @RequestBody FeeInvoiceGiveUpReqDTO reqDTO) {

        return invoiceGiveUpService.voidInvoices(reqDTO);
    }
}
