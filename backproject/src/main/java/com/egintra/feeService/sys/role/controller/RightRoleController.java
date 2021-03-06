package com.egintra.feeService.sys.role.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.BaseFuncDTO;
import com.egintra.common.dto.RightRoleReqDTO;
import com.egintra.common.dto.RightRoleUserReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.role.service.RightRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统角色接口
 *
 * @author liushihao
 * @date 2021/06/18
 */
@RestController
@RequestMapping("/role")
@ResponseResult
public class RightRoleController {

    @Resource
    private RightRoleService rightRoleService;

    /**
     * 查询所有角色信息
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/queryAllRoles")
    public DataReuslt queryAllRoles(@RequestBody RightRoleReqDTO rightRoleReqDTO) {

        return rightRoleService.queryAllRoles(rightRoleReqDTO);
    }

    /**
     * 查询所有角色信息
     *
     * @param baseFuncDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "/getRoleList")
    public DataReuslt getRoleList(@RequestBody BaseFuncDTO baseFuncDTO) {

        return rightRoleService.getRoleList(baseFuncDTO);
    }

    /**
     * 查询角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/queryRole")
    public DataReuslt queryRole(@RequestBody RightRoleReqDTO rightRoleReqDTO) {

        return rightRoleService.queryRole(rightRoleReqDTO);
    }

    /**
     * 新增角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/addRole")
    public DataReuslt addRole(@RequestBody RightRoleReqDTO rightRoleReqDTO) {

        return rightRoleService.addRole(rightRoleReqDTO);
    }

    /**
     * 删除角色信息
     *
     * @param rightRoleReqDTO 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteRole")
    public DataReuslt deleteRole(@RequestBody RightRoleReqDTO rightRoleReqDTO) {

        return rightRoleService.deleteRole(rightRoleReqDTO);
    }

    /**
     * 修改角色信息
     *
     * @param rightRoles 请求对象
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/updateRole")
    public DataReuslt updateRole(@RequestBody List<RightRoleReqDTO> rightRoles) {

        return rightRoleService.updateRole(rightRoles);
    }

    /**
     * 更新用户角色信息
     *
     * @param rightRoleUserReqDTO 请求对象
     * @return 处理结果
     */
    @SysToken
    @RequestMapping(value = "/updateRoleUserInfo")
    public DataReuslt updateRoleUserInfo(@RequestBody RightRoleUserReqDTO rightRoleUserReqDTO) {

        return rightRoleService.updateRoleUserInfo(rightRoleUserReqDTO);
    }
}
