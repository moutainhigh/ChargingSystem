package com.egintra.feeService.sys.department.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.dto.PubSimplenumberRespDTO;
import com.egintra.common.enums.PubsimplecateEnum;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.repository.mapper.DepartmentMapper;
import com.egintra.common.repository.mapper.PubSimplenumberMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.common.utils.StringUtil;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.department.service.DepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private PubSimplenumberMapper pubSimplenumberMapper;

    /**
     * 查看 列表 （分页）
     *
     * @param department
     * @return
     */
    @Override
    public DataReuslt getDepartmentList(DepartmentReqDTO department) {
        // 分页
        Page ps = PageHelper.startPage(department.getPage(), department.getSize());
        // 查询
        List<DepartmentReqDTO> departments = departmentMapper.getDepartmentList(department);
        // 数字转中文
        String posMode = PubsimplecateEnum.POSMODE.getCode();
        List<PubSimplenumberRespDTO> posModes = pubSimplenumberMapper.queryNumbersByCateCd(posMode);
        Map<String, String> posModeMap = posModes.stream().collect(
                Collectors.toMap(PubSimplenumberRespDTO::getCode, PubSimplenumberRespDTO::getName));
        departments.forEach(dto -> {
            // 转换“大队”、“支队”
            if ("1".equals(dto.getUnitLevel())) {
                dto.setLevelName("支队");
            } else {
                dto.setLevelName("大队");
            }
            // 转换pos银行
            if (!StringUtil.isEmpty(dto.getPosMode())
                    && posModeMap.containsKey(dto.getPosMode())) {
                dto.setPosMode(posModeMap.get(dto.getPosMode()));
            }
        });
        // 构造分页数据
        PageResult<DepartmentReqDTO> result = PageHelperUtils.getPagingData(departments, ps);

        // 返回
        return DataReuslt.success(result);
    }

    /**
     * 查看列表(不分页)
     *
     * @param department
     * @return
     */
    @Override
    public DataReuslt getDepartmentLists(DepartmentReqDTO department) {
        List<DepartmentReqDTO> departments = departmentMapper.getDepartmentList(department);

        return DataReuslt.success(departments);
    }

    /**
     * 添加单位
     *
     * @param departmentReqDTO
     * @return
     */
    @Override
    @Transactional
    public DataReuslt addDepartment(DepartmentReqDTO departmentReqDTO) {
        DepartmentReqDTO depart = departmentMapper.getOne(departmentReqDTO.getUnitRegion());
        if (depart != null) {
            return DataReuslt.fail("1", "单位编码已经存在");
        }
        DepartmentReqDTO name = departmentMapper.findByName(departmentReqDTO.getUnitName());
        if (name != null) {
            return DataReuslt.fail("1", "单位名称已经存在");
        }
        departmentMapper.addDepartment(departmentReqDTO);

        return DataReuslt.success(departmentReqDTO);
    }

    /**
     * 编辑单位
     *
     * @param departmentReqDTOs
     * @return
     */
    @Override
    @Transactional
    public DataReuslt editDepartment(List<DepartmentReqDTO> departmentReqDTOs) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean result = false;
        for (DepartmentReqDTO departmentReqDTO : departmentReqDTOs) {
            DepartmentReqDTO departmentReqDTO1 = departmentMapper.getDepartParam(departmentReqDTO);
            if (departmentReqDTO1 == null) {
                // 修改
                DepartmentReqDTO departmentReqDTO2 = departmentMapper.findByName(departmentReqDTO.getUnitName());
                if (departmentReqDTO2 != null) {
                    stringBuffer.append(departmentReqDTO2.getUnitName());
                    result = true;
                    break;
                }
            }
        }
        if (result == true) {
            return DataReuslt.fail("1", "单名名称已经重复:" + stringBuffer.toString());
        } else {
            departmentMapper.editDepart(departmentReqDTOs);

            return DataReuslt.success(departmentReqDTOs);
        }
    }

    /**
     * 删除单位
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt deleteById(String id) {
        List<AccountReqDTO> accountReqDTOS = accountMapper.getAccountListByDeptId(id);
        if (accountReqDTOS.size() != 0) {
            return DataReuslt.fail("该单位下存在账套,不允许删除!");
        }
        departmentMapper.deleteById(id);

        return DataReuslt.success("删除成功");
    }

    /**
     * 查看单位详情
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt getOne(String id) {
        DepartmentReqDTO departmentReqDTO = departmentMapper.getOne(id);

        return DataReuslt.success(departmentReqDTO);
    }

    /**
     * 获取pos银行集合
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getPosModes")
    public DataReuslt getPosModes() {
        String posMode = PubsimplecateEnum.POSMODE.getCode();
        List<PubSimplenumberRespDTO> posModes = pubSimplenumberMapper.queryNumbersByCateCd(posMode);

        return DataReuslt.success(posModes);
    }
}
