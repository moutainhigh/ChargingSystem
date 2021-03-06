package com.egintra.feeService.login.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.SysUserDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.login.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 系统登录接口
 *
 * @author liushihao
 * @date 2021/06/18
 */
@RestController
@RequestMapping("/login")
@ResponseResult
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param sysUserDTO 查询条件
     * @return 结果
     */
    @SysToken(required = false)
    @RequestMapping(value = "/login")
    public DataReuslt login(@Valid @RequestBody SysUserDTO sysUserDTO) {

        return loginService.login(sysUserDTO);
    }
}
