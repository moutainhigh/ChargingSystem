package com.egintra.feeService.finance.projectdetail.service;

import com.egintra.common.dto.ProjectDetailReqDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface ProjectDetailService {

    /**
     * 查看列表
     *
     * @return 结果
     */
    DataReuslt getProjectDetailList(ProjectDetailReqDTO projectDetailReqDTO);

    /**
     * 查看列表
     *
     * @return 结果
     */
    DataReuslt getProjectDetailLists(ProjectDetailReqDTO projectDetailReqDTO);

    /**
     * 添加
     *
     * @param projectDetailReqDTO
     * @return
     */
    DataReuslt addProjectDetail(ProjectDetailReqDTO projectDetailReqDTO);

    /**
     * 编辑
     *
     * @param projectDetailReqDTO
     * @return
     */
    DataReuslt editProjectDetail(List<ProjectDetailReqDTO> projectDetailReqDTO);

    /**
     * 编辑
     *
     * @param id
     * @return
     */
    DataReuslt deleteById(String id);
}
