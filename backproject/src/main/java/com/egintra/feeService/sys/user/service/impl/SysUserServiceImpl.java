package com.egintra.feeService.sys.user.service.impl;

import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.dto.RightRoleUserRespDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.RightUserSaveReqDTO;
import com.egintra.common.dto.SysUserDTO;
import com.egintra.common.dto.sys.RightRoleUserSaveDTO;
import com.egintra.common.enums.RecordStatusEnum;
import com.egintra.common.enums.UserSexEnum;
import com.egintra.common.enums.UserStatusEnum;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.repository.mapper.RightRoleuserMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.DesUtil;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.user.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private RightRoleuserMapper rightRoleuserMapper;

    /**
     * 密码加密解密Key
     */
    @Value("${user.password.desKey}")
    private String desKey;
    /**
     * 初始密码
     */
    @Value("${user.passwork.default.password}")
    private String defaultPassword;

    /**
     * 根据用户ID查询已配置的角色
     *
     * @param rightUserDTO 参数
     * @return 结果
     */
    @Override
    public DataReuslt getUserAllRoles(RightUserDTO rightUserDTO) {
        // 设置查询条件查询角色用户信息
        RightRoleUserReqDTO dto = new RightRoleUserReqDTO();
        dto.setUserId(rightUserDTO.getId());
        List<RightRoleUserRespDTO> rightRoleUsers = rightRoleuserMapper.queryRoleUsers(dto);
        List<String> roleIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rightRoleUsers)) {
            roleIds = rightRoleUsers.stream().map(RightRoleUserRespDTO::getRoleId).collect(Collectors.toList());
        }

        // 返回结果
        return DataReuslt.success(roleIds);
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

        // 先根据用户ID查询用户角色关系表获取当前用户已经设置的角色权限
        List<RightRoleUserRespDTO> rightRoleUsers = rightRoleuserMapper.queryRoleUsers(rightRoleUserReqDTO);

        // 如果查询当前用户角色关系不为空，删除原已配置的，再保存新的;否则直接保存
        if (!CollectionUtils.isEmpty(rightRoleUsers)) {
            // id集合
            List<String> deleteIds = rightRoleUsers.stream().map(RightRoleUserRespDTO::getId).collect(Collectors.toList());
            // 批量删除
            int deletes = rightRoleuserMapper.batchDeleteUserRoles(deleteIds, RecordStatusEnum.YES.getCode());
        }

        // 设置保存用户角色关系数据
        // 批量保存数据集合单元对象：insertDto;批量保存数据集合：insertList
        List<String> roleIds = rightRoleUserReqDTO.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) {
            // 返回结果
            return DataReuslt.success("保存成功");
        }
        // 根据功能ID集合循环生成insertList
        RightRoleUserSaveDTO saveDTO;
        List<RightRoleUserSaveDTO> insertList = new ArrayList<>();
        Date currentDate = DateUtils.getCurrentDate();
        for (String roleId : roleIds) {
            saveDTO = new RightRoleUserSaveDTO();
            saveDTO.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
            saveDTO.setUserId(rightRoleUserReqDTO.getUserId());
            saveDTO.setRoleId(roleId);
            saveDTO.setRecordStatus(RecordStatusEnum.NO.getCode());
            saveDTO.setRecordCreateTm(currentDate);
            saveDTO.setRecordModifyTm(currentDate);
            insertList.add(saveDTO);
        }
        // 保存入库
        int inserts = rightRoleuserMapper.batchInsertUserRoles(insertList);

        // 返回结果
        return DataReuslt.success("保存成功");
    }

    /**
     * 查询所有用户信息
     *
     * @param sysUserDTO 查询条件
     * @return 结果
     */
    @Override
    public DataReuslt queryUserInfo(SysUserDTO sysUserDTO) {

        //  如果参数为空则返回报错
        if (null == sysUserDTO.getNo()
                || null == sysUserDTO.getPassword()) {
            return DataReuslt.fail(ResultCode.REQUEST_PARAMS_IS_EMPTY.code(),
                    ResultCode.REQUEST_PARAMS_IS_EMPTY.message());
        }
        // 设置查询条件，验证是否存在当前用户
        RightUser rightUser = new RightUser();
        BeanUtils.copyProperties(sysUserDTO, rightUser);
        RightUser resultRightUser = loginMapper.queryUserInfo(rightUser);
        // 查询数据结果为空返回报错，否则查询所有用户返回
        if (null == resultRightUser) {
            return DataReuslt.fail(ResultCode.CURRENT_USER_HAS_NO_PERMISSION.code(),
                    ResultCode.CURRENT_USER_HAS_NO_PERMISSION.message());
        }
        // 查询所有用户
        Page page = PageHelper.startPage(sysUserDTO.getPage(), sysUserDTO.getSize());
        List<RightUser> userInfos = loginMapper.queryUserInfos();
        // 分页
        PageResult<RightUser> pagingData = PageHelperUtils.getPagingData(userInfos, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 根据条件查询用户信息
     *
     * @param rightUserDTO 查询条件
     * @return 结果
     */
    @Override
    public DataReuslt querySomeUsers(RightUserDTO rightUserDTO) {
        // 根据条件查询用户信息
        Page page = PageHelper.startPage(rightUserDTO.getPage(), rightUserDTO.getSize());
        List<RightUser> userInfos = loginMapper.querySomeUsers(rightUserDTO);
        // 分页
        PageResult<RightUser> pagingData = PageHelperUtils.getPagingData(userInfos, page);

        return DataReuslt.success(pagingData);
    }

    /**
     * 修改密码
     *
     * @param rightUserDTO 请求对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt changePwd(RightUserDTO rightUserDTO) {

        //  如果参数为空则返回报错
        if (StringUtils.isEmpty(rightUserDTO.getNo())
                || StringUtils.isEmpty(rightUserDTO.getOldPassword())
                || StringUtils.isEmpty(rightUserDTO.getNewPassword())) {
            // 请求参数为空
            return DataReuslt.fail(ResultCode.REQUEST_PARAMS_IS_EMPTY.code(),
                    ResultCode.REQUEST_PARAMS_IS_EMPTY.message());
        }

        // 根据入参查询用户信息
        RightUser rightUser = new RightUser();
        rightUser.setNo(rightUserDTO.getNo());
        // 加密原密码查询用户信息
        String oldPwd = DesUtil.encrypt(desKey, rightUserDTO.getOldPassword());
        rightUser.setPassword(oldPwd);
        RightUser resultRightUser = loginMapper.queryUserInfo(rightUser);

        // 如果查询结果为空，报错提示
        if (null == resultRightUser) {
            return DataReuslt.fail(ResultCode.UPDATE_USER_PASSWORD_FAILED.code(),
                    ResultCode.UPDATE_USER_PASSWORD_FAILED.message());
        }

        // 如果查询结果不为空，更新用户密码
        String newDesPwd = DesUtil.encrypt(desKey, rightUserDTO.getNewPassword());
        int i = loginMapper.updatePwd(rightUser, newDesPwd);

        return DataReuslt.success();
    }

    /**
     * 查询当前用户信息
     *
     * @param sysUserDTO 入参对象
     * @return 查询结果
     */
    @Override
    public DataReuslt getCurUserInfo(@RequestBody SysUserDTO sysUserDTO) {

        // 验证参数
        if (null == sysUserDTO) {
            return DataReuslt.fail(ResultCode.REQUEST_PARAMS_IS_EMPTY.code(),
                    ResultCode.REQUEST_PARAMS_IS_EMPTY.message());
        }
        // 设置查询条件，验证是否存在当前用户
        RightUser rightUser = new RightUser();
        BeanUtils.copyProperties(sysUserDTO, rightUser);
        RightUser curUserInfo = loginMapper.queryUserInfo(rightUser);
        // 查询数据结果为空报错
        if (null == curUserInfo) {
            return DataReuslt.fail(ResultCode.CURRENT_USER_HAS_NO_PERMISSION.code(),
                    ResultCode.CURRENT_USER_HAS_NO_PERMISSION.message());
        }
        // 通过枚举修改性别码值为中文
        if (null != curUserInfo.getSex()) {
            curUserInfo.setSex(UserSexEnum.userSexMap.get(curUserInfo.getSex()));
        }

        return DataReuslt.success(curUserInfo);
    }

    /**
     * 更新用户信息
     *
     * @param sysUsers 参数
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt updateUser(List<RightUserSaveReqDTO> sysUsers) {

        // 更新
        int i = loginMapper.batchUpdateUsers(sysUsers);

        return DataReuslt.success("更新成功");
    }

    /**
     * 删除用户信息
     *
     * @param sysUserDTO 参数
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt deleteUser(SysUserDTO sysUserDTO) {
        // 验证参数
        if (null == sysUserDTO) {
            return DataReuslt.fail(ResultCode.REQUEST_PARAMS_IS_EMPTY.code(),
                    ResultCode.REQUEST_PARAMS_IS_EMPTY.message());
        }
        int i = loginMapper.deleteUser(sysUserDTO);

        return DataReuslt.success("删除成功");
    }

    /**
     * 新增用户信息
     *
     * @param saveReqDTO 参数
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataReuslt addUser(@RequestBody RightUserSaveReqDTO saveReqDTO) {
        // 查询当前账号是否存在，存在报错
        RightUser rightUser = new RightUser();
        rightUser.setNo(saveReqDTO.getNo());
        RightUser curUserInfo = loginMapper.queryUserInfo(rightUser);
        if (null != curUserInfo) {
            return DataReuslt.fail(ResultCode.ADD_USER_NO_FAILED.code(),
                    ResultCode.ADD_USER_NO_FAILED.message());
        }

        // 设置参数新增用户
        rightUser = new RightUser();
        BeanUtils.copyProperties(saveReqDTO, rightUser);
        rightUser.setRecordStatus(UserStatusEnum.NO.getCode());
        Date currentDate = DateUtils.getCurrentDate();
        rightUser.setRecordCreateTm(currentDate);
        rightUser.setRecordModifyTm(currentDate);
        // 初始化密码
        String password = DesUtil.encrypt(desKey, defaultPassword);
        rightUser.setPassword(password);
        rightUser.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
        rightUser.setDepartId(saveReqDTO.getDepartmentId());
        if (null == rightUser.getStartIp()) {
            rightUser.setStartIp("");
        }
        if (null == rightUser.getEndIp()) {
            rightUser.setEndIp("");
        }
        // 插入数据
        loginMapper.insertUser(rightUser);

        return DataReuslt.success();
    }
}
