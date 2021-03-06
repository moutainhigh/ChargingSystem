package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.MaterialReqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialMapper extends BaseMapper<MaterialReqDTO> {

    /**
     * 查看列表
     *
     * @return 查询结果
     */
    List<MaterialReqDTO> getMaterialList(MaterialReqDTO materialReqDTO);

    /**
     * 添加
     *
     * @param materialReqDTO
     */
    void addMaterial(MaterialReqDTO materialReqDTO);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    MaterialReqDTO getOne(String id);

    /**
     * 名称是否重复
     *
     * @param name
     * @return
     */
    MaterialReqDTO findByName(String name);

    /**
     * 名称是否重复
     *
     * @param materialReqDTO
     * @return
     */
    MaterialReqDTO findByNameParam(MaterialReqDTO materialReqDTO);

    /**
     * 根据明细id查看材料列表
     *
     * @param id
     * @return
     */
    List<MaterialReqDTO> getMaterialListByParam(String id);

    /**
     * 编辑
     *
     * @param materialReqDTO
     */
    void editMaterial(@Param("list") List<MaterialReqDTO> materialReqDTO);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 查看当前名洗下消耗的总材料费用
     * @param projectId
     * @return
     */
    String getSumMaterial(String projectId);
}
