package com.egintra.feeService.sys.department.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.department.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单位（部门）管理
 *
 * @author zyt
 * @date 2021/07/22
 */
@RestController
@RequestMapping("/department")
@ResponseResult
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getDepartmentList")
    public DataReuslt getDepartmentList(@RequestBody DepartmentReqDTO department) {

        return departmentService.getDepartmentList(department);
    }

    /**
     * 不分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getDepartmentLists")
    public DataReuslt getDepartments(@RequestBody DepartmentReqDTO department) {

        return departmentService.getDepartmentLists(department);
    }

    /**
     * 新增单位
     *
     * @return 结果
     */
    @RequestMapping(value = "/addDepartment")
    public DataReuslt addDepartment(@RequestBody DepartmentReqDTO departmentReqDTO) {

        return departmentService.addDepartment(departmentReqDTO);
    }

    /**
     * 编辑单位
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/editDepartment")
    public DataReuslt editDepartment(@RequestBody List<DepartmentReqDTO> departmentReqDTO) {
        return departmentService.editDepartment(departmentReqDTO);
    }

    /**
     * 查看单位详情
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getOne")
    public DataReuslt getOne(@RequestBody DepartmentReqDTO departmentReqDTO) {

        return departmentService.getOne(departmentReqDTO.getUnitRegion());
    }

    /**
     * 删除单位
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteById")
    public DataReuslt deleteById(@RequestBody DepartmentReqDTO departmentReqDTO) {

        return departmentService.deleteById(departmentReqDTO.getUnitRegion());
    }

    /**
     * 获取pos银行集合
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getPosModes")
    public DataReuslt getPosModes() {

        return departmentService.getPosModes();
    }
}
