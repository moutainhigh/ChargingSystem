package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.InvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Resource
    private InvoiceMapper invoiceMapper;

    /**
     * 车驾管业务收费核查接口
     *
     * @param lsh
     * @return
     */
    @Override
    public DataReuslt isPaymentNot(String lsh) {
        InvoiceReqDTO invoiceReqDTO = invoiceMapper.isPaymentNot(lsh);
        if (invoiceReqDTO == null) {
            return DataReuslt.fail("02", "未缴费");
        } else {
            String status = invoiceReqDTO.getStatus();
            if ("1".equals(status)) {
                return DataReuslt.success("已缴费");
            } else {
                return DataReuslt.fail("02", "未缴费");
            }
        }
    }
}
