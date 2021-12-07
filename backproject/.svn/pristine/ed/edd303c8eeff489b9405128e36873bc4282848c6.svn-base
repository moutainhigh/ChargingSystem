package com.egintra.feeService.business.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.business.WorkDateReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.business.service.WorkDateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 工作日期设置
 *
 * @author zyt
 * @date 2021/09/07
 */
@RestController
@RequestMapping("/workDate")
@ResponseResult
public class WorkDateController {

    @Resource
    private WorkDateService workDateService;

    /**
     * 获取当前工作日期的时间
     *
     * @return 结果
     */
    @RequestMapping(value = "/getWorkDate")
    public DataReuslt getWorkDate(@RequestBody WorkDateReqDTO workDateReqDTO) {
        try {
            return workDateService.getWorkDate(workDateReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("查询失败");
        }
    }



    /**
     * 修改当前工作日期的时间
     *
     * @return 结果
     */
    @RequestMapping(value = "/updateWorkDate")
    public DataReuslt udpateWordDate(@RequestBody WorkDateReqDTO workDateReqDTO) {
        try {
            return workDateService.updateWorkDate(workDateReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return DataReuslt.fail("修改失败");
        }
    }

}
