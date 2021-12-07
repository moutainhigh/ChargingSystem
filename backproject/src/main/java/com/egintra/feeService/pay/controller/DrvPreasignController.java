package com.egintra.feeService.pay.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.DrvPreasignReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.pay.service.DrvPreasignService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 考试费收费核查接口
 *
 * @author zyt
 * @date 2021/08/06
 */
@RestController
@RequestMapping("/drvPreasign")
@ResponseResult
public class DrvPreasignController {

    @Resource
    private DrvPreasignService drvPreasignService;

    /**
     * 考试费收费核查接口
     *
     * @param drvPreasign
     * @return
     */
    @RequestMapping(value = "/checkExamPay")
    public DataReuslt checkExamPay(@RequestBody DrvPreasignReqDTO drvPreasign) {

        return drvPreasignService.checkExamPay(drvPreasign);
    }

    /**
     * 查询试预约数据(根据流水号或身份证号)
     *
     * @param drvPreasign 请求参数
     * @return 结果
     */
    public DataReuslt queryPreasigns(@RequestBody DrvPreasignReqDTO drvPreasign) {

        return drvPreasignService.queryPreasigns(drvPreasign);
    }
}
