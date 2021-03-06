package com.egintra.feeService.finance.material.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.MaterialReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.finance.material.service.MaterialService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 材料接口
 *
 * @author zyt
 * @date 2021/07/22
 */
@RestController
@RequestMapping("/material")
@ResponseResult
public class MaterialController {

    @Resource
    private MaterialService materialService;

    /**
     * 分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getMaterialList")
    public DataReuslt getMaterialList(@RequestBody MaterialReqDTO materialReqDTO) {

        return materialService.getMaterialList(materialReqDTO);
    }

    /**
     * 不分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getMaterialLists")
    public DataReuslt getMaterialLists(@RequestBody MaterialReqDTO materialReqDTO) {

        return materialService.getMaterialLists(materialReqDTO);
    }

    /**
     * 新增材料
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addMaterial")
    public DataReuslt addMaterial(@RequestBody MaterialReqDTO materialReqDTO) {

        return materialService.addMaterial(materialReqDTO);
    }

    /**
     * 编辑材料
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/editMaterial")
    public DataReuslt editMaterial(@RequestBody List<MaterialReqDTO> materialReqDTO) {

        return materialService.editMaterial(materialReqDTO);
    }

    /**
     * 删除材料
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteById")
    public DataReuslt deleteById(@RequestBody MaterialReqDTO materialReqDTO) {

        return materialService.deleteById(materialReqDTO.getMaterialId());
    }
}
