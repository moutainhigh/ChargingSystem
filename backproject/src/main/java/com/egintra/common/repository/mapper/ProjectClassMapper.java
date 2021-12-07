package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.ProjectClassReqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectClassMapper extends BaseMapper<ProjectClassReqDTO> {

    /**
     * 查看列表
     *
     * @return 查询结果
     */
    List<ProjectClassReqDTO> getProjectClassList(ProjectClassReqDTO projectClassReqDTO);


    /**
     * 添加
     *
     * @param projectClassReqDTO
     */
    void addProjectClass(ProjectClassReqDTO projectClassReqDTO);


    /**
     * 编辑
     *
     * @param projectClassReqDTOS
     */
    void editProjectClass(@Param("list") List<ProjectClassReqDTO> projectClassReqDTOS);


    /**
     * 删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    ProjectClassReqDTO getOne(String id);


    /**
     * 查看名称是否重复
     *
     * @param name
     * @return
     */
    ProjectClassReqDTO findByName(String name);

}
