package com.egintra.feeService.finance.projectdetail.service.impl;

import com.egintra.common.dto.MaterialReqDTO;
import com.egintra.common.dto.ProjectDetailReqDTO;
import com.egintra.common.repository.mapper.MaterialMapper;
import com.egintra.common.repository.mapper.ProjectDetailMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.finance.projectdetail.service.ProjectDetailService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {

    @Resource
    private ProjectDetailMapper projectDetailMapper;
    @Resource
    private MaterialMapper materialMapper;

    /**
     * 查看列表(分页)
     *
     * @param projectDetailReqDTO
     * @return
     */
    @Override
    public DataReuslt getProjectDetailList(ProjectDetailReqDTO projectDetailReqDTO) {
        Page ps = PageHelper.startPage(projectDetailReqDTO.getPage(), projectDetailReqDTO.getSize());
        List<ProjectDetailReqDTO> detailReqDTOS = projectDetailMapper.getProjectDetailList(projectDetailReqDTO);
        PageResult<ProjectDetailReqDTO> result = PageHelperUtils.getPagingData(detailReqDTOS, ps);

        return DataReuslt.success(result);
    }

    /**
     * 查看列表(不分页)
     *
     * @param projectDetailReqDTO
     * @return
     */
    @Override
    public DataReuslt getProjectDetailLists(ProjectDetailReqDTO projectDetailReqDTO) {
        List<ProjectDetailReqDTO> departments = projectDetailMapper.getProjectDetailList(projectDetailReqDTO);

        return DataReuslt.success(departments);
    }

    /**
     * 新增项目明细
     *
     * @param projectDetailReqDTO
     * @return
     */
    @Override
    @Transactional
    public DataReuslt addProjectDetail(ProjectDetailReqDTO projectDetailReqDTO) {
        ProjectDetailReqDTO project = projectDetailMapper.getOne(projectDetailReqDTO.getProjectId());
        if (project != null) {
            return DataReuslt.fail("0", "项目明细编号已经存在");
        }
        ProjectDetailReqDTO name = projectDetailMapper.findByName(projectDetailReqDTO.getProjectName());
        if (name != null) {
            return DataReuslt.fail("0", "项目明细名称已经存在");
        }
        projectDetailMapper.addProjectDetail(projectDetailReqDTO);

        return DataReuslt.success(projectDetailReqDTO);
    }

    /**
     * 编辑明细
     *
     * @param projectDetailReqDTOs
     * @return
     */
    @Override
    @Transactional
    public DataReuslt editProjectDetail(List<ProjectDetailReqDTO> projectDetailReqDTOs) {
        boolean result = false;
        StringBuffer stringBuffer = new StringBuffer();
        for (ProjectDetailReqDTO projectDetailReqDTO : projectDetailReqDTOs) {
            ProjectDetailReqDTO projectDetailReqDTO1 = projectDetailMapper.findByNameParam(projectDetailReqDTO);
            if (projectDetailReqDTO1 == null) {
                ProjectDetailReqDTO projectDetailReqDTO2 = projectDetailMapper.findByName(projectDetailReqDTO.getProjectName());
                if (projectDetailReqDTO2 != null) {
                    stringBuffer.append(projectDetailReqDTO2.getProjectName());
                    result = true;
                }
            }
        }
        if (result == true) {
            return DataReuslt.fail("0", "明细名称已经存在: " + stringBuffer.toString());
        } else {
            projectDetailMapper.editProjectDetail(projectDetailReqDTOs);
            return DataReuslt.success(projectDetailReqDTOs);
        }
    }

    /**
     * 删除明细
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt deleteById(String id) {
        List<MaterialReqDTO> materialReqDTOS = materialMapper.getMaterialListByParam(id);
        if (materialReqDTOS.size() != 0) {
            return DataReuslt.fail("0", "该明细下有材料，不允许删除!");
        }
        projectDetailMapper.deleteById(id);

        return DataReuslt.success("删除成功");
    }
}
