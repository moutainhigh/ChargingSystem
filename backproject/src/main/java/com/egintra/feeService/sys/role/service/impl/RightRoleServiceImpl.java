package com.egintra.feeService.sys.role.service.impl;

import com.egintra.common.dto.BaseFuncDTO;
import com.egintra.common.dto.RightRoleReqDTO;
import com.egintra.common.dto.RightRoleRespDTO;
import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.dto.RightRoleUserRespDTO;
import com.egintra.common.enums.RoleStatusEnum;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.entity.RightRoleuser;
import com.egintra.common.repository.mapper.RightRoleMapper;
import com.egintra.common.repository.mapper.RightRoleuserMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.role.service.RightRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RightRoleServiceImpl implements RightRoleService {

    @Resource
    private RightRoleMapper rightRoleMapper;
    @Resource
    private RightRoleuserMapper rightRoleuserMapper;

    /**
     * 查询所有角色信息
     *
     * @return 结果
     */
    @Override
    public DataReuslt queryAllRoles(RightRoleReqDTO rightRoleReqDTO) {

        // 在执行SQL时获取分页参数
        Page page = PageHelper.startPage(rightRoleReqDTO.getPage(), rightRoleReqDTO.getSize());
        // 查询所有角色信息
        List<RightRoleRespDTO> rightRoleRespDTOS = rightRoleMapper.queryAllRoles();
        // 分页处理
        PageResult<RightRoleRespDTO> pagingData = PageHelperUtils.getPagingData(rightRoleRespDTOS, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 查询所有角色信息
     *
     * @param baseFuncDTO 参数
     * @return 结果
     */
    @Override
    public DataReuslt getRoleList(BaseFuncDTO baseFuncDTO) {

        List<RightRoleRespDTO> rightRoleRespDTOS = rightRoleMapper.queryAllRoles();

        return DataReuslt.success(rightRoleRespDTOS);
    }

    /**
     * 查询角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    @Override
    public DataReuslt queryRole(RightRoleReqDTO rightRoleReqDTO) {
        // 验证参数，为空则报错
        if (null == rightRoleReqDTO) {
            return DataReuslt.fail(ResultCode.USER_INFO_IS_EMPTY.code(),
                    ResultCode.USER_INFO_IS_EMPTY.message());
        }

        // 查询角色信息
        List<RightRoleRespDTO> rightRoleRespDTOS = rightRoleMapper.queryRole(rightRoleReqDTO);

        // 返回结果
        return DataReuslt.success(rightRoleRespDTOS);
    }

    /**
     * 新增角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addRole(RightRoleReqDTO rightRoleReqDTO) {
        // 验证参数，为空则报错
        if (null == rightRoleReqDTO) {
            return DataReuslt.fail(ResultCode.USER_INFO_IS_EMPTY.code(),
                    ResultCode.USER_INFO_IS_EMPTY.message());
        }

        // 查询是否已经存在当前角色
        RightRoleReqDTO dto = new RightRoleReqDTO();
        dto.setName(rightRoleReqDTO.getName());
        List<RightRoleRespDTO> rightRoles = rightRoleMapper.queryRole(dto);
        if (!CollectionUtils.isEmpty(rightRoles)) {
            return DataReuslt.fail(ResultCode.ADD_ROLE_FAILED.code(),
                    ResultCode.ADD_ROLE_FAILED.message());
        }

        // 设置参数保存角色数据
        rightRoleReqDTO.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
        rightRoleReqDTO.setRecordStatus(RoleStatusEnum.NO.getCode());
        Date curDate = DateUtils.getCurrentDate();
        rightRoleReqDTO.setRecordCreateTm(curDate);
        rightRoleReqDTO.setRecordModifyTm(curDate);
        // 新增角色
        rightRoleMapper.insertRole(rightRoleReqDTO);

        // 返回结果
        return DataReuslt.success();
    }

    /**
     * 删除角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteRole(RightRoleReqDTO rightRoleReqDTO) {
        // 验证参数，为空则报错
        if (null == rightRoleReqDTO.getId()) {
            return DataReuslt.fail(ResultCode.USER_INFO_IS_EMPTY.code(),
                    ResultCode.USER_INFO_IS_EMPTY.message());
        }

        Date curDate = DateUtils.getCurrentDate();
        rightRoleReqDTO.setRecordModifyTm(curDate);
        // 根据ID删除数据
        rightRoleMapper.deleteRole(rightRoleReqDTO);

        // 返回结果
        return DataReuslt.success("删除成功");
    }

    /**
     * 修改角色信息
     *
     * @param rightRoles 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateRole(List<RightRoleReqDTO> rightRoles) {

        // 1.数据校验，判断是否修改时重复保存
        // 1）原数据直接判断：如果角色名称重复，报错提示
        List<String> roleList = rightRoles.stream().map(RightRoleReqDTO::getName).distinct()
                .collect(Collectors.toList());
        if (roleList.size() != rightRoles.size()) {
            return DataReuslt.fail(ResultCode.UPDATE_ROLE_FAILED.code(), ResultCode.UPDATE_ROLE_FAILED.message());
        }

        // 2）查询判断：id name 结果存在，保存；不存在=》 name 如果存在提示，不存在保存
        // 根据id name查
        List<RightRoleRespDTO> rightRoleResps = rightRoleMapper.batchQueryRoles(rightRoles);
        if (CollectionUtils.isEmpty(rightRoleResps)
                || (!CollectionUtils.isEmpty(rightRoleResps) && rightRoleResps.size() != rightRoles.size())) {
            List<RightRoleRespDTO> rightRoleRespDTOList = rightRoleMapper.batchQueryRolesByNames(roleList);
            if (!CollectionUtils.isEmpty(rightRoleRespDTOList)) {
                return DataReuslt.fail(ResultCode.UPDATE_ROLE_FAILED.code(), ResultCode.UPDATE_ROLE_FAILED.message());
            }
        }

        // 设置更新时间
        Date curDate = DateUtils.getCurrentDate();
        for (RightRoleReqDTO rightRole : rightRoles) {
            rightRole.setRecordModifyTm(curDate);
        }

        // 根据ID修改数据
        rightRoleMapper.batchUpdateRole(rightRoles);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 更新用户角色信息
     *
     * @param rightRoleUserReqDTO 请求对象
     * @return 处理结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateRoleUserInfo(RightRoleUserReqDTO rightRoleUserReqDTO) {
        // 验证参数，为空则报错
        if (null == rightRoleUserReqDTO) {
            return DataReuslt.fail(ResultCode.USER_INFO_IS_EMPTY.code(),
                    ResultCode.USER_INFO_IS_EMPTY.message());
        }
        List<RightRoleUserRespDTO> roleUsers = rightRoleuserMapper.queryRoleUsers(rightRoleUserReqDTO);
        // 如果当前用户未配置此角色则插入
        if (CollectionUtils.isEmpty(roleUsers)) {
            RightRoleuser rightRoleuser = new RightRoleuser();
            BeanUtils.copyProperties(rightRoleUserReqDTO, rightRoleuser);
            rightRoleuserMapper.insert(rightRoleuser);
        }

        // 返回结果
        return DataReuslt.success();
    }
}