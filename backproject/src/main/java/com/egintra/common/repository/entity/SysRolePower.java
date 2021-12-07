package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 角色功能关系表
 *
 * @author liushihao
 * @date 2021/7/27
 */
@TableName("sys_role_power")
public class SysRolePower extends Model<SysRolePower> {

    private static final long serialVersionUID = 388874473852187007L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 角色ID
     */
    @TableId(value = "role_id")
    private String roleId;

    /**
     * 功能ID
     */
    @TableId(value = "power_id")
    private String powerId;

    /**
     * 创建人编码
     */
    @TableId(value = "record_create_cd")
    private String recordCreateCd;

    /**
     * 创建人名称
     */
    @TableId(value = "record_create_nm")
    private String recordCreateNm;

    /**
     * 创建时间
     */
    @TableId(value = "record_create_tm")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordCreateTm;

    /**
     * 修改人编码
     */
    @TableId(value = "record_modify_cd")
    private String recordModifyCd;

    /**
     * 修改人名称
     */
    @TableId(value = "record_modify_nm")
    private String recordModifyNm;

    /**
     * 修改日期
     */
    @TableId(value = "record_modify_tm")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordModifyTm;

    /**
     * 状态
     */
    @TableId(value = "record_status")
    private String recordStatus;

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

    public String getRecordCreateCd() {
        return recordCreateCd;
    }

    public void setRecordCreateCd(String recordCreateCd) {
        this.recordCreateCd = recordCreateCd;
    }

    public String getRecordCreateNm() {
        return recordCreateNm;
    }

    public void setRecordCreateNm(String recordCreateNm) {
        this.recordCreateNm = recordCreateNm;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
    }

    public String getRecordModifyCd() {
        return recordModifyCd;
    }

    public void setRecordModifyCd(String recordModifyCd) {
        this.recordModifyCd = recordModifyCd;
    }

    public String getRecordModifyNm() {
        return recordModifyNm;
    }

    public void setRecordModifyNm(String recordModifyNm) {
        this.recordModifyNm = recordModifyNm;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Override
    public String toString() {
        return "SysRolePower{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", powerId=" + powerId +
                ", recordCreateCd='" + recordCreateCd + '\'' +
                ", recordCreateNm='" + recordCreateNm + '\'' +
                ", recordCreateTm=" + recordCreateTm +
                ", recordModifyCd='" + recordModifyCd + '\'' +
                ", recordModifyNm='" + recordModifyNm + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                ", recordStatus='" + recordStatus + '\'' +
                '}';
    }
}
