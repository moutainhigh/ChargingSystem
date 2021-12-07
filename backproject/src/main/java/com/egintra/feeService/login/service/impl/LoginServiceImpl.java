package com.egintra.feeService.login.service.impl;

import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.dto.SysInfoDTO;
import com.egintra.common.dto.SysUserDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.DepartmentMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DesUtil;
import com.egintra.common.utils.TokenUtils;
import com.egintra.feeService.login.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private DepartmentMapper departmentMapper;
    /**
     * 密码加密解密Key
     */
    @Value("${user.password.desKey}")
    private String desKey;

    /**
     * 登录查询验证用户信息
     *
     * @param sysUserDTO 查询条件
     * @return 结果
     */
    @Override
    public DataReuslt login(SysUserDTO sysUserDTO) {

        //  如果参数为空则返回空对象
        if (null == sysUserDTO.getNo()
                || null == sysUserDTO.getPassword()) {
            return DataReuslt.fail(ResultCode.USER_INFO_IS_EMPTY.code(),
                    ResultCode.USER_INFO_IS_EMPTY.message());
        }

        // 设置查询条件
        RightUser rightUser = new RightUser();
        BeanUtils.copyProperties(sysUserDTO, rightUser);
        rightUser.setPassword(DesUtil.encrypt(desKey, rightUser.getPassword()));
        RightUser userInfo = loginMapper.queryUserInfo(rightUser);
        // 查询数据结果为空返回异常信息
        if (null == userInfo) {
            return DataReuslt.fail(ResultCode.USER_LOGIN_ERROR.code(),
                    ResultCode.USER_LOGIN_ERROR.message());
        }

        SysInfoDTO sysInfoDTO = new SysInfoDTO();
        sysInfoDTO.setRightUser(userInfo);
        // 生成token并返回
        String token = TokenUtils.getToken(String.valueOf(userInfo.getId()),
                DesUtil.decrypt(desKey, userInfo.getPassword()));
        // 返回token
        sysInfoDTO.setToken(token);
        DepartmentReqDTO department = departmentMapper.getOne(userInfo.getDepartId());
        sysInfoDTO.setPosMode(department.getPosMode());

        // 返回结果
        return DataReuslt.success(sysInfoDTO);
    }
}
