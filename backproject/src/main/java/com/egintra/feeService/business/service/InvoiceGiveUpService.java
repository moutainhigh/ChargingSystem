package com.egintra.feeService.business.service;

import com.egintra.common.dto.business.FeeInvoiceGiveUpQueryReqDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpReqDTO;
import com.egintra.common.utils.DataReuslt;

/**
 * 发票作废service
 *
 * @author liushihao
 * @date 2021/9/8
 */
public interface InvoiceGiveUpService {
    /**
     * 发票查询
     *
     * @param feeInvoiceGiveUpQueryReqDTO 参数
     * @return 结果
     */
    public DataReuslt queryInvoices(FeeInvoiceGiveUpQueryReqDTO feeInvoiceGiveUpQueryReqDTO);

    /**
     * 发票作废
     *
     * @param reqDTO 参数
     * @return 结果
     */
    public DataReuslt voidInvoices(FeeInvoiceGiveUpReqDTO reqDTO);
}
