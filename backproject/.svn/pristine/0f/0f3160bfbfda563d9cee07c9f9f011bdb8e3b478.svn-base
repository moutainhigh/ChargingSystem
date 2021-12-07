package com.egintra.feeService.reportForm.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.reportForm.ReportReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.reportForm.service.StatisticalReportService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 统计报表
 *
 * @author zhangyunting
 * @date 2021/11/10
 */
@RestController
@RequestMapping("/statisticalReport")
@ResponseResult
public class StatisticalReportController {


    @Resource
    private StatisticalReportService statisticalReportService;

    /**
     * 查询报表信息
     *
     * @param reportReqDTO
     * @return
     */
    @RequestMapping(value = "/queryReport")
    public DataReuslt queryInvoce(@RequestBody ReportReqDTO reportReqDTO) {
        try {
            return statisticalReportService.queryReport(reportReqDTO);
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
            return statisticalReportService.getUserLevel();
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.success("查询失败");
        }
    }

}
