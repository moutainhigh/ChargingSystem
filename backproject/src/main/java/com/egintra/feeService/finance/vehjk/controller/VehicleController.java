package com.egintra.feeService.finance.vehjk.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.VehicleReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.finance.vehjk.service.VehicleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机动车业务收费信息接口
 *
 * @author zyt
 * @date 2021/07/28
 */
@RestController
@RequestMapping("/vehicle")
@ResponseResult
public class VehicleController {

    @Resource
    private VehicleService vehjkService;

    /**
     * 分页 查看列表
     *
     * @return 结果
     */
    @RequestMapping(value = "/getVehicleList")
    public DataReuslt getVehicleList(@RequestBody VehicleReqDTO vehjkReqDTO) {

        return vehjkService.getVehicleList(vehjkReqDTO);
    }

    /**
     * 不分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getVehicleLists")
    public DataReuslt getVehicleLists(@RequestBody VehicleReqDTO vehjkReqDTO) {

        return vehjkService.getVehicleLists(vehjkReqDTO);
    }

    /**
     * 新增材料
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addVehicle")
    public DataReuslt addVehicle(@RequestBody List<VehicleReqDTO> vehjkReqDTOS) {

        return vehjkService.addVehicle(vehjkReqDTOS);
    }

    /**
     * 编辑材料
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/editVehicle")
    public DataReuslt editVehicle(@RequestBody List<VehicleReqDTO> vehjkReqDTOS) {

        return vehjkService.editVehicle(vehjkReqDTOS);
    }

    /**
     * 删除材料
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteById")
    public DataReuslt deleteById(@RequestBody VehicleReqDTO vehjkReqDTO) {

        return vehjkService.deleteById(vehjkReqDTO);
    }

    /**
     * 查询单个数据
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getOne")
    public DataReuslt getOne(@RequestBody VehicleReqDTO vehjkReqDTO) {

        return vehjkService.getOne(vehjkReqDTO);
    }
}
