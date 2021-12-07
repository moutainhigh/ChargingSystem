package com.egintra.common.dto;

import java.util.Date;

/**
 * 更新用户信息保存参数DTO
 *
 * @author liushihao
 * @date 2021/7/30
 */
public class RightUserSaveReqDTO {

    /**
     * ID
     */
    private String id;

    /**
     * 账号
     */
    private String no;

    /**
     * 单位ID
     */
    private String departmentId;

    /**
     * 账套号
     */
    private String accountId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户级别
     */
    private String userLevel;

    /**
     * 起始ip
     */
    private String startIp;

    /**
     * 终止ip
     */
    private String endIp;

    /**
     * 创建时间
     */
    private Date recordCreateTm;

    /**
     * 修改日期
     */
    private Date recordModifyTm;

    /**
     * 状态
     */
    private String recordStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
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
        return "RightUserSaveReqDTO{" +
                "id='" + id + '\'' +
                ", no='" + no + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", name='" + name + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", startIp='" + startIp + '\'' +
                ", endIp='" + endIp + '\'' +
                ", recordCreateTm=" + recordCreateTm +
                ", recordModifyTm=" + recordModifyTm +
                ", recordStatus='" + recordStatus + '\'' +
                '}';
    }
}
