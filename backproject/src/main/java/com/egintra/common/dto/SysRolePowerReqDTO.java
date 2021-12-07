package com.egintra.common.dto;

import java.util.Date;

/**
 * 角色功能关系请求对象
 *
 * @author liushihao
 * @date 2021/7/27
 */
public class SysRolePowerReqDTO {

    /**
     * ID
     */
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 功能ID
     */
    private String powerId;

    /**
     * 状态
     */
    private String recordStatus;

    /**
     * 修改日期
     */
    private Date recordModifyTm;

    /**
     * 创建时间
     */
    private Date recordCreateTm;

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

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
    }

    @Override
    public String toString() {
        return "SysRolePowerReqDTO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", powerId=" + powerId +
                ", recordStatus='" + recordStatus + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                ", recordCreateTm=" + recordCreateTm +
                '}';
    }
}
