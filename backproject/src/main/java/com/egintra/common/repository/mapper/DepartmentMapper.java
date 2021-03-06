package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.dto.MaterialReqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<MaterialReqDTO> {

    /**
     * 查看列表
     *
     * @return 查询结果
     */
    List<DepartmentReqDTO> getDepartmentList(DepartmentReqDTO department);

    /**
     * 添加
     *
     * @param departmentReqDTO
     */
    void addDepartment(DepartmentReqDTO departmentReqDTO);

    /**
     * 编辑
     *
     * @param list
     */
    void editDepart(@Param("list") List<DepartmentReqDTO> list);

    /**
     * 根据参数查看是否存在
     *
     * @param departmentReqDTO
     * @return
     */
    DepartmentReqDTO getDepartParam(DepartmentReqDTO departmentReqDTO);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    DepartmentReqDTO getOne(String id);

    /**
     * 查看名称是否存在
     *
     * @param id
     * @return
     */
    DepartmentReqDTO findByName(String id);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(String id);
}
