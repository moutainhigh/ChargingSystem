package com.egintra.feeService.sys.dataFrom.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.sys.FeeGetDataSetReqDTO;
import com.egintra.common.dto.sys.FeeGetDataSetSaveReqDTO;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.dataFrom.service.FeeGetDataSetService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 数据访问参数设置模块
 *
 * @author liushihao
 * @date 2021/8/6
 */
@RestController
@RequestMapping(value = "/getDataSet")
@ResponseResult
public class FeeGetDataSetController {

    @Resource
    private FeeGetDataSetService feeGetDataSetService;

    /**
     * 查询设置数据
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 查询结果
     */
    @RequestMapping(value = "/queryDataSets")
    public DataReuslt queryDataSets(@RequestBody FeeGetDataSetReqDTO feeGetDataSetReqDTO) {

        return feeGetDataSetService.queryDataSets(feeGetDataSetReqDTO);
    }

    /**
     * 新增设置
     *
     * @param feeGetDataSetSaveReqDTO 请求参数
     * @return 结果
     */
    @RequestMapping(value = "/saveDataSet")
    public DataReuslt saveDataSet(@RequestBody FeeGetDataSetSaveReqDTO feeGetDataSetSaveReqDTO) {

        return feeGetDataSetService.saveDataSet(feeGetDataSetSaveReqDTO);
    }

    /**
     * 修改设置
     *
     * @param updates 请求参数
     * @return 结果
     */
    @RequestMapping(value = "/updateDataSet")
    public DataReuslt updateDataSet(@RequestBody List<FeeGetDataSetSaveReqDTO> updates) {

        return feeGetDataSetService.updateDataSet(updates);
    }

    /**
     * 删除设置
     *
     * @param feeGetDataSetReqDTO 请求参数
     * @return 结果
     */
    @RequestMapping(value = "/deleteDataSet")
    public DataReuslt deleteDataSet(@Valid @RequestBody FeeGetDataSetReqDTO feeGetDataSetReqDTO) {

        return feeGetDataSetService.deleteDataSet(feeGetDataSetReqDTO);
    }
}
