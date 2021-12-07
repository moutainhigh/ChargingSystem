package com.egintra.feeService.reportForm.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.reportForm.QueryReportReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.reportForm.service.NetIncomeReportService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 净收入报表
 *
 * @author liushihao
 * @date 2021/11/10
 */
@RestController
@RequestMapping("/netIncome")
@ResponseResult
public class NetIncomeReportController {

    @Resource
    private NetIncomeReportService netIncomeReportService;

    /**
     * 查询报表信息
     *
     * @param reqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/queryReportInfo")
    public DataReuslt queryReport(@RequestBody QueryReportReqDTO reqDTO) {

        return netIncomeReportService.queryReportInfo(reqDTO);
    }
}
