package com.egintra.feeService.sys.role.service.impl;

import com.egintra.common.dto.RightFuncrightReqDTO;
import com.egintra.common.dto.SysRolePowerReqDTO;
import com.egintra.common.dto.SysRolePowerRespDTO;
import com.egintra.common.enums.RecordStatusEnum;
import com.egintra.common.repository.mapper.SysRolePowerMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.feeService.sys.role.service.RightRolePowerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RightRolePowerServiceImpl implements RightRolePowerService {

    @Resource
    private SysRolePowerMapper sysRolePowerMapper;

    /**
     * 保存功能权限
     *
     * @param rightFuncrightReqDTO 请求对象
     * @return 保存结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt saveRoleConfigure(RightFuncrightReqDTO rightFuncrightReqDTO) {

        // 根据当前角色的ID查询角色功能关系表
        SysRolePowerReqDTO queryDto = new SysRolePowerReqDTO();
        queryDto.setRoleId(rightFuncrightReqDTO.getId());
        List<SysRolePowerRespDTO> rolePowers = sysRolePowerMapper.queryRolePower(queryDto);

        // 如果查询当前角色功能关系不为空，删除原已配置的，再保存新的;否则直接保存
        if (!CollectionUtils.isEmpty(rolePowers)) {
            // id集合
            List<String> deleteIds = rolePowers.stream().map(SysRolePowerRespDTO::getId).collect(Collectors.toList());
            // 批量删除
            int deletes = sysRolePowerMapper.batchDeleteRolePowers(deleteIds, RecordStatusEnum.YES.getCode());
        }

        // 设置保存角色功能关系数据
        // 批量保存数据集合单元对象：insertDto;批量保存数据集合：insertList
        List<String> funcIds = rightFuncrightReqDTO.getFuncIds();
        if (CollectionUtils.isEmpty(funcIds)) {
            // 返回结果
            return DataReuslt.success("保存成功");
        }
        SysRolePowerReqDTO insertDto;
        // 根据功能ID集合循环生成insertList
        List<SysRolePowerReqDTO> insertList = new ArrayList<>();
        Date currentDate = DateUtils.getCurrentDate();
        for (String funcId : funcIds) {
            insertDto = new SysRolePowerReqDTO();
            insertDto.setRoleId(rightFuncrightReqDTO.getId());
            insertDto.setRecordStatus(RecordStatusEnum.NO.getCode());
            insertDto.setRecordCreateTm(currentDate);
            insertDto.setRecordModifyTm(currentDate);
            insertDto.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
            insertDto.setPowerId(funcId);
            insertList.add(insertDto);
        }
        // 保存入库
        int inserts = sysRolePowerMapper.batchInsertRolePowers(insertList);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 查询角色功能权限数据，返回功能ID集合
     *
     * @param rightFuncrightReqDTO 入参
     * @return 返回结果
     */
    @Override
    public DataReuslt queryRolePowers(RightFuncrightReqDTO rightFuncrightReqDTO) {

        // 根据当前角色的ID查询角色功能关系表
        SysRolePowerReqDTO queryDto = new SysRolePowerReqDTO();
        queryDto.setRoleId(rightFuncrightReqDTO.getId());
        List<SysRolePowerRespDTO> rolePowers = sysRolePowerMapper.queryRolePower(queryDto);
        List<String> powers = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rolePowers)) {
            powers = rolePowers.stream().map(SysRolePowerRespDTO::getPowerId).collect(Collectors.toList());
        }

        // 返回结果
        return DataReuslt.success(powers);
    }
}
