package com.egintra.feeService.finance.material.service;

import com.egintra.common.dto.MaterialReqDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface MaterialService {

    /**
     * 查看列表
     *
     * @return 结果
     */
    DataReuslt getMaterialList(MaterialReqDTO materialReqDTO);

    /**
     * 查看列表
     *
     * @return 结果
     */
    DataReuslt getMaterialLists(MaterialReqDTO materialReqDTO);

    /**
     * 添加
     *
     * @param materialReqDTO
     * @return
     */
    DataReuslt addMaterial(MaterialReqDTO materialReqDTO);

    /**
     * 编辑
     *
     * @param materialReqDTO
     * @return
     */
    DataReuslt editMaterial(List<MaterialReqDTO> materialReqDTO);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    DataReuslt deleteById(String id);
}
