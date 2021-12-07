package com.egintra.feeService.queryStatistics.service;

import com.egintra.common.dto.InvoiceDetailReqDTO;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.utils.DataReuslt;

/**
 *
 */
public interface SpecialBusinessService {


    /**
     * 查询发票详情相关的信息
     *
     * @param invoiceDetailReqDTO
     * @return
     */
    DataReuslt queryInvoceDetailList(InvoiceDetailReqDTO invoiceDetailReqDTO);

    /**
     * 获取当前的用户等级
     *
     * @return
     */
    DataReuslt getUserLevel();


}
