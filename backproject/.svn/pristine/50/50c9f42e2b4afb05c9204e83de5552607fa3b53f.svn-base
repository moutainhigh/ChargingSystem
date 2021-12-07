package com.egintra.feeService.queryStatistics.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.queryStatistics.service.QueryInvoiceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 查询发票接口
 *
 * @author zyt
 * @date 2021/9/16
 */
@RestController
@RequestMapping("/queryInvoice")
@ResponseResult
public class QueryInvoiceController {

    @Resource
    private QueryInvoiceService queryInvoiceService;

    /**
     * 查询发票相关的接口
     *
     * @param invoiceReqDTO
     * @return
     */
    @RequestMapping(value = "/queryInvoce")
    public DataReuslt queryInvoce(@RequestBody InvoiceReqDTO invoiceReqDTO) {
        try {
            return queryInvoiceService.queryInvoce(invoiceReqDTO);
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
            return queryInvoiceService.getUserLevel();
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.success("查询失败");
        }
    }

    /**
     * 查看发票详情
     *
     * @return
     */
    @RequestMapping(value = "/getInvoiceDetailList")
    public DataReuslt getInvoiceDetailList(@RequestBody FeeInvoiceDetail feeInvoiceDetail) {
        try {
            return queryInvoiceService.getInvoiceDetailList(feeInvoiceDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.success("查询失败");
        }
    }
}
