package com.egintra.feeService.finance.projectclass.service.impl;

import com.egintra.common.dto.ProjectClassReqDTO;
import com.egintra.common.dto.ProjectDetailReqDTO;
import com.egintra.common.repository.mapper.ProjectClassMapper;
import com.egintra.common.repository.mapper.ProjectDetailMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.finance.projectclass.service.ProjectClassService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectClassServiceImpl implements ProjectClassService {

    @Resource
    private ProjectClassMapper projectClassMapper;
    @Resource
    private ProjectDetailMapper projectDetailMapper;

    /**
     * 列表(分页)
     *
     * @param projectClassReqDTO
     * @return
     */
    @Override
    public DataReuslt getProjectClassList(ProjectClassReqDTO projectClassReqDTO) {
        Page ps = PageHelper.startPage(projectClassReqDTO.getPage(), projectClassReqDTO.getSize());
        List<ProjectClassReqDTO> projectClassList = projectClassMapper.getProjectClassList(projectClassReqDTO);
        PageResult<ProjectClassReqDTO> result = PageHelperUtils.getPagingData(projectClassList, ps);

        return DataReuslt.success(result);
    }

    /**
     * 列表(不分页)
     *
     * @param projectClassReqDTO
     * @return
     */
    @Override
    public DataReuslt getProjectClassLists(ProjectClassReqDTO projectClassReqDTO) {
        List<ProjectClassReqDTO> departments = projectClassMapper.getProjectClassList(projectClassReqDTO);

        return DataReuslt.success(departments);
    }

    /**
     * 新增大类
     *
     * @param projectClassReqDTO
     * @return
     */
    @Override
    @Transactional
    public DataReuslt addProjectClass(ProjectClassReqDTO projectClassReqDTO) {
        ProjectClassReqDTO classReqDTO = projectClassMapper.getOne(projectClassReqDTO.getClassId());
        if (classReqDTO != null) {
            return DataReuslt.fail("大类代码在系统中已经存在");
        }
        ProjectClassReqDTO name = projectClassMapper.findByName(projectClassReqDTO.getClassName());
        if (name != null) {
            return DataReuslt.fail("大类名称在系统中已经存在");
        }
        projectClassMapper.addProjectClass(projectClassReqDTO);

        return DataReuslt.success(projectClassReqDTO);
    }

    /**
     * 编辑大类
     *
     * @param projectClassReqDTOS
     * @return
     */
    @Override
    @Transactional
    public DataReuslt editProjectClass(List<ProjectClassReqDTO> projectClassReqDTOS) {
        projectClassMapper.editProjectClass(projectClassReqDTOS);

        return DataReuslt.success(projectClassReqDTOS);
    }

    /**
     * 删除大类
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt deleteById(String id) {
        List<ProjectDetailReqDTO> list = projectDetailMapper.getDetailList(id);
        if (list.size() != 0) {
            return DataReuslt.fail("0", "大类下存在明细,不允许删除！");
        }
        projectClassMapper.deleteById(id);

        return DataReuslt.success("删除成功");
    }

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt getOne(String id) {
        ProjectClassReqDTO projectClassReqDTO = projectClassMapper.getOne(id);

        return DataReuslt.success(projectClassReqDTO);
    }
}
