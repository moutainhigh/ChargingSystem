package com.egintra.feeService.pay.service;

import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.utils.DataReuslt;

public interface DriversOnlinePayService {

    /**
     * 查询机动车需要缴费的数据
     *
     * @param invoiceReqDTO
     * @return
     */
    DataReuslt getDriversOnlinePay(InvoiceReqDTO invoiceReqDTO);

    /**
     * 机动车线上缴费
     *
     * @param invoiceReqDTO
     * @return
     */
    DataReuslt onlinePayment(InvoiceReqDTO invoiceReqDTO);
}
