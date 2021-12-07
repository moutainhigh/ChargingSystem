package com.egintra.feeService.queryStatistics.service;

import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.utils.DataReuslt;

/**
 *
 */
public interface QueryInvoiceService {


    /**
     * 查询发票相关的信息
     *
     * @param invoiceReqDTO
     * @return
     */
    DataReuslt queryInvoce(InvoiceReqDTO invoiceReqDTO);

    /**
     * 获取当前的用户等级
     *
     * @return
     */
    DataReuslt getUserLevel();

    /**
     *  查看发票详情
     * @return
     */
    DataReuslt getInvoiceDetailList(FeeInvoiceDetail feeInvoiceDetail);
}
