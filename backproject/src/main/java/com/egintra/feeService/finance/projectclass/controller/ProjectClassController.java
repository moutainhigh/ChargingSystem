package com.egintra.feeService.finance.projectclass.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.ProjectClassReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.finance.projectclass.service.ProjectClassService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目大类接口
 *
 * @author zyt
 * @date 2021/07/22
 */
@RestController
@RequestMapping("/projectclass")
@ResponseResult
public class ProjectClassController {

    @Resource
    private ProjectClassService projectClassService;

    /**
     * 分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getProjectClassList")
    public DataReuslt getProjectClassList(@RequestBody ProjectClassReqDTO projectClassReqDTO) {

        return projectClassService.getProjectClassList(projectClassReqDTO);
    }

    /**
     * 不分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getProjectClassLists")
    public DataReuslt getDepartments(@RequestBody ProjectClassReqDTO projectClassReqDTO) {

        return projectClassService.getProjectClassLists(projectClassReqDTO);
    }

    /**
     * 新增大类
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addProjectClass")
    public DataReuslt addProjectClass(@RequestBody ProjectClassReqDTO projectClassReqDTO) {

        return projectClassService.addProjectClass(projectClassReqDTO);
    }

    /**
     * 编辑大类
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/editProjectClass")
    public DataReuslt editProjectClass(@RequestBody List<ProjectClassReqDTO> projectClassReqDTO) {

        return projectClassService.editProjectClass(projectClassReqDTO);
    }

    /**
     * 查看详情
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getOne")
    public DataReuslt getOne(@RequestBody ProjectClassReqDTO projectClassReqDTO) {

        return projectClassService.getOne(projectClassReqDTO.getClassId());
    }

    /**
     * 删除大类
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteById")
    public DataReuslt deleteById(@RequestBody ProjectClassReqDTO projectClassReqDTO) {

        return projectClassService.deleteById(projectClassReqDTO.getClassId());
    }
}
