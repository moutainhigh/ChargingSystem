package com.egintra.feeService.queryStatistics.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.InvoiceDetailReqDTO;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.queryStatistics.service.QueryInvoiceService;
import com.egintra.feeService.queryStatistics.service.SpecialBusinessService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 专项业务查询
 *
 * @author zyt
 * @date 2021/11/4
 */
@RestController
@RequestMapping("/specialBusiness")
@ResponseResult
public class SpecialBusinessController {

    @Resource
    private SpecialBusinessService specialBusinessService;

    /**
     * 查询发票相关的接口
     *
     * @param invoiceDetailReqDTO
     * @return
     */
    @RequestMapping(value = "/queryInvoce")
    public DataReuslt queryInvoce(@RequestBody InvoiceDetailReqDTO invoiceDetailReqDTO) {
        try {
            return specialBusinessService.queryInvoceDetailList(invoiceDetailReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.success("查询失败");
        }
    }

    /**
     * 获取当前的用户等级
     *
     * @return
     */
    @RequestMapping(value = "/getUserLevel")
    public DataReuslt getUserLevel() {
        try {
            return specialBusinessService.getUserLevel();
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.success("查询失败");
        }
    }


}
