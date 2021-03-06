package com.egintra.feeService.finance.material.service.impl;

import com.egintra.common.dto.MaterialReqDTO;
import com.egintra.common.repository.mapper.MaterialMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.finance.material.service.MaterialService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;

    /**
     * 查看列表(分页)
     *
     * @param materialReqDTO
     * @return
     */
    @Override
    public DataReuslt getMaterialList(MaterialReqDTO materialReqDTO) {

        Page ps = PageHelper.startPage(materialReqDTO.getPage(), materialReqDTO.getSize());
        List<MaterialReqDTO> materialReqDTOS = materialMapper.getMaterialList(materialReqDTO);
        PageResult<MaterialReqDTO> result = PageHelperUtils.getPagingData(materialReqDTOS, ps);

        return DataReuslt.success(result);
    }

    /**
     * 查看列表(不分页)
     *
     * @param materialReqDTO
     * @return
     */
    @Override
    public DataReuslt getMaterialLists(MaterialReqDTO materialReqDTO) {

        List<MaterialReqDTO> departments = materialMapper.getMaterialList(materialReqDTO);

        return DataReuslt.success(departments);
    }

    /**
     * 新增材料
     *
     * @param materialReqDTO
     * @return
     */
    @Override
    @Transactional
    public DataReuslt addMaterial(MaterialReqDTO materialReqDTO) {

        MaterialReqDTO material = materialMapper.getOne(materialReqDTO.getMaterialId());
        if (material != null) {
            return DataReuslt.fail("0", "材料编号已经存在");
        }
        MaterialReqDTO name = materialMapper.findByName(materialReqDTO.getMaterialName());
        if (name != null) {
            return DataReuslt.fail("0", "材料名称已经存在");
        }
        materialMapper.addMaterial(materialReqDTO);

        return DataReuslt.success(materialReqDTO);
    }

    /**
     * 编辑材料
     *
     * @param materialReqDTOs
     * @return
     */
    @Override
    @Transactional
    public DataReuslt editMaterial(List<MaterialReqDTO> materialReqDTOs) {
        boolean result = false;
        StringBuffer stringBuffer = new StringBuffer();
        for (MaterialReqDTO materialReqDTO : materialReqDTOs) {
            MaterialReqDTO materialReqDTO1 = materialMapper.findByNameParam(materialReqDTO);
            if (materialReqDTO1 == null) {
                MaterialReqDTO materialReqDTO2 = materialMapper.findByName(materialReqDTO.getMaterialName());
                if (materialReqDTO2 != null) {
                    stringBuffer.append(materialReqDTO2.getMaterialName());
                    result = true;
                }
            }
        }
        if (result == true) {
            return DataReuslt.fail("0", "材料名称已经存在:" + stringBuffer.toString());
        } else {
            materialMapper.editMaterial(materialReqDTOs);

            return DataReuslt.success(materialReqDTOs);
        }
    }

    /**
     * 删除材料
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt deleteById(String id) {
        materialMapper.deleteById(id);

        return DataReuslt.success("删除成功");
    }
}
