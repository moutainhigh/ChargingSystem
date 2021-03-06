package com.egintra.feeService.sys.department.service;

import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface DepartmentService {

    /**
     * 查看列表
     *
     * @return 结果
     */
    DataReuslt getDepartmentList(DepartmentReqDTO department);

    /**
     * 查看列表
     *
     * @return 结果
     */
    DataReuslt getDepartmentLists(DepartmentReqDTO department);

    /**
     * 添加
     *
     * @param departmentReqDTO
     * @return
     */
    DataReuslt addDepartment(DepartmentReqDTO departmentReqDTO);

    /**
     * 编辑
     *
     * @param departmentReqDTO
     * @return
     */
    DataReuslt editDepartment(List<DepartmentReqDTO> departmentReqDTO);

    /**
     * 编辑
     *
     * @param id
     * @return
     */
    DataReuslt deleteById(String id);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    DataReuslt getOne(String id);

    /**
     * 获取pos银行集合
     *
     * @return 结果
     */
    public DataReuslt getPosModes();
}
