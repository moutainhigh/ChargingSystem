package com.egintra.feeService.finance.projectdetail.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.ProjectDetailReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.finance.projectdetail.service.ProjectDetailService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目明细接口
 *
 * @author zyt
 * @date 2021/07/22
 */
@RestController
@RequestMapping("/projectDetail")
@ResponseResult
public class ProjectDetailController {

    @Resource
    private ProjectDetailService projectDetailService;

    /**
     * 分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getProjectDetailList")
    public DataReuslt getProjectDetailList(@RequestBody ProjectDetailReqDTO projectDetailReqDTO) {

        return projectDetailService.getProjectDetailList(projectDetailReqDTO);
    }

    /**
     * 不分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getProjectDetailLists")
    public DataReuslt getProjectDetailLists(@RequestBody ProjectDetailReqDTO projectDetailReqDTO) {

        return projectDetailService.getProjectDetailLists(projectDetailReqDTO);
    }

    /**
     * 新增项目明细
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addProjectDetail")
    public DataReuslt addProjectDetail(@RequestBody ProjectDetailReqDTO projectDetailReqDTO) {

        return projectDetailService.addProjectDetail(projectDetailReqDTO);
    }

    /**
     * 编辑项目明细
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/editProjectDetail")
    public DataReuslt editProjectDetail(@RequestBody List<ProjectDetailReqDTO> projectDetailReqDTO) {

        return projectDetailService.editProjectDetail(projectDetailReqDTO);
    }

    /**
     * 删除明细
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteById")
    public DataReuslt deleteById(@RequestBody ProjectDetailReqDTO ProjectDetailReqDTO) {

        return projectDetailService.deleteById(ProjectDetailReqDTO.getProjectId());
    }
}
