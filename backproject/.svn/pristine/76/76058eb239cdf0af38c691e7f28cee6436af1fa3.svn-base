package com.egintra.feeService.queryStatistics.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.queryStatistics.QueryStatisticsReqDTO;
import com.egintra.common.pointcuts.SysLog;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.queryStatistics.service.ChargeStatisticsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 查询统计-收费统计
 *
 * @author liushihao
 * @date 2021/9/16
 */
@RestController
@RequestMapping("/chargeStatistics")
@ResponseResult
public class ChargeStatisticsController {

    @Resource
    private ChargeStatisticsService chargeStatisticsService;

    /**
     * 收费统计-查询
     *
     * @param queryStatisticsReqDTO 查询条件
     * @return 结果
     */
    @SysLog
    @RequestMapping(value = "/queryChargeStatistics")
    public DataReuslt queryChargeStatistics(@RequestBody QueryStatisticsReqDTO queryStatisticsReqDTO) {

        return chargeStatisticsService.queryStatistics(queryStatisticsReqDTO);
    }

    /**
     * 收费统计-导出查询
     *
     * @param queryReqDTO 查询条件
     * @return 结果
     */
    @SysLog
    @RequestMapping(value = "/exportStatistics")
    public DataReuslt exportStatistics(QueryStatisticsReqDTO queryReqDTO) {

        return chargeStatisticsService.exportStatistics(queryReqDTO);
    }
}
