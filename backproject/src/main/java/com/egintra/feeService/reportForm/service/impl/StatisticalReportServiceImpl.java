package com.egintra.feeService.reportForm.service.impl;

import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.reportForm.PayTypeReqDTO;
import com.egintra.common.dto.reportForm.ReportReqDTO;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.DepartmentMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.repository.mapper.ReportMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.reportForm.service.StatisticalReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyunting
 * @date 2021/11/10
 */
@Service
public class StatisticalReportServiceImpl implements StatisticalReportService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public DataReuslt queryReport(ReportReqDTO reportReqDTO) {
        Map map=new HashMap();
        List<ReportReqDTO> reportReqDTOList = reportMapper.queryReport(reportReqDTO);
        List<PayTypeReqDTO> payTypeList = reportMapper.getPayTypeList(reportReqDTO);
        map.put("report",reportReqDTOList);
        map.put("type",payTypeList);
        return DataReuslt.success(map);
    }

    @Override
    public DataReuslt getUserLevel() {
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        DepartmentReqDTO department = departmentMapper.getOne(rightUser.getDepartId());
        rightUser.setName(department.getUnitName());
        return DataReuslt.success(rightUser);
    }
}
