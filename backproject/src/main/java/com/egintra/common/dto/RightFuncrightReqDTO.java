package com.egintra.common.dto;

import java.util.List;

/**
 * 功能权限请求DTO
 *
 * @author liushihao
 * @date 2021/06/24
 */
public class RightFuncrightReqDTO {

    /**
     * 内码
     */
    private String id;

    /**
     * 角色内码
     */
    private String roleId;

    /**
     * 分类内码
     */
    private String funccateId;

    /**
     * 功能内码集合
     */
    private List<String> funcIds;

    /**
     * 按钮内码集合
     */
    private List<String> actionIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getFuncIds() {
        return funcIds;
    }

    public void setFuncIds(List<String> funcIds) {
        this.funcIds = funcIds;
    }

    public String getFunccateId() {
        return funccateId;
    }

    public void setFunccateId(String funccateId) {
        this.funccateId = funccateId;
    }

    public List<String> getActionIds() {
        return actionIds;
    }

    public void setActionIds(List<String> actionIds) {
        this.actionIds = actionIds;
    }

    @Override
    public String toString() {
        return "RightFuncrightReqDTO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", funccateId=" + funccateId +
                ", funcIds=" + funcIds +
                ", actionIds=" + actionIds +
                '}';
    }
}
