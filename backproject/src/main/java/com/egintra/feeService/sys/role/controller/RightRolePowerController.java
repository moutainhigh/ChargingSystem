package com.egintra.feeService.sys.role.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.RightFuncrightReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.role.service.RightRolePowerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统角色接口
 *
 * @author liushihao
 * @date 2021/07/27
 */
@RestController
@RequestMapping("/rolePower")
@ResponseResult
public class RightRolePowerController {

    @Resource
    private RightRolePowerService rightRolePowerService;

    /**
     * 保存功能权限
     *
     * @param rightFuncrightReqDTO 请求对象
     * @return 保存结果
     */
    @SysToken
    @RequestMapping(value = "/saveRoleConfigure")
    public DataReuslt saveRoleConfigure(@RequestBody RightFuncrightReqDTO rightFuncrightReqDTO) {

        return rightRolePowerService.saveRoleConfigure(rightFuncrightReqDTO);
    }

    /**
     * 查询角色功能权限数据，返回功能ID集合
     *
     * @param rightFuncrightReqDTO 入参
     * @return 返回结果
     */
    @SysToken
    @RequestMapping(value = "/queryRolePowers")
    public DataReuslt queryRolePowers(@RequestBody RightFuncrightReqDTO rightFuncrightReqDTO) {

        return rightRolePowerService.queryRolePowers(rightFuncrightReqDTO);
    }
}
