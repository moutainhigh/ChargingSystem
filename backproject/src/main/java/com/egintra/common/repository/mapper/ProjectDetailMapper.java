package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.ProjectClassReqDTO;
import com.egintra.common.dto.ProjectDetailReqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDetailMapper extends BaseMapper<ProjectClassReqDTO> {

    /**
     * 查看列表
     *
     * @return 查询结果
     */
    List<ProjectDetailReqDTO> getProjectDetailList(ProjectDetailReqDTO projectDetailReqDTO);

    /**
     * 添加
     *
     * @param projectDetailReqDTO
     */
    void addProjectDetail(ProjectDetailReqDTO projectDetailReqDTO);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    ProjectDetailReqDTO getOne(String id);

    /**
     * 名称是否重复
     *
     * @param name
     * @return
     */
    ProjectDetailReqDTO findByName(String name);

    /**
     * 名称是否重复
     *
     * @param projectDetailReqDTO
     * @return
     */
    ProjectDetailReqDTO findByNameParam(ProjectDetailReqDTO projectDetailReqDTO);

    /**
     * 编辑
     *
     * @param projectDetailReqDTO
     */
    void editProjectDetail(@Param("list") List<ProjectDetailReqDTO> projectDetailReqDTO);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据项目Id查看明细列表
	 
     * @param classId
     * @return
     */
    List<ProjectDetailReqDTO> getDetailList(String classId);
}
