package com.egintra.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 账套设置DTO
 *
 * @author zyt
 * @date 2021/08/04
 */
public class AccountReqDTO extends CommonPage {

    /**
     * 单位代码
     */
    private String departmentId;

    /**
     * 单位名称
     */
    private String departName;

    /**
     * 账套号
     */
    private String accountId;

    /**
     * 状态 1：正常 0：停用 2：暂停
     */
    private String status;

    /**
     * 工作日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 结算标记
     */
    private String collectFlag;

    /**
     * 执收单位编码
     */
    private String receiveAccount;

    /**
     * Pos收银机号
     */
    private String posMachineId;

    /**
     * Pos收银员工
     */
    private String posPersonId;

    /**
     * 虚拟发票号 默认0 开票后自增
     */
    private String fictitiousId;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getCollectFlag() {
        return collectFlag;
    }

    public void setCollectFlag(String collectFlag) {
        this.collectFlag = collectFlag;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getPosMachineId() {
        return posMachineId;
    }

    public void setPosMachineId(String posMachineId) {
        this.posMachineId = posMachineId;
    }

    public String getPosPersonId() {
        return posPersonId;
    }

    public void setPosPersonId(String posPersonId) {
        this.posPersonId = posPersonId;
    }

    public String getFictitiousId() {
        return fictitiousId;
    }

    public void setFictitiousId(String fictitiousId) {
        this.fictitiousId = fictitiousId;
    }

    @Override
    public String toString() {
        return "AccountReqDTO{" +
                "departmentId='" + departmentId + '\'' +
                ", departName='" + departName + '\'' +
                ", accountId='" + accountId + '\'' +
                ", status='" + status + '\'' +
                ", workDate=" + workDate +
                ", collectFlag='" + collectFlag + '\'' +
                ", receiveAccount='" + receiveAccount + '\'' +
                ", posMachineId='" + posMachineId + '\'' +
                ", posPersonId='" + posPersonId + '\'' +
                ", fictitiousId='" + fictitiousId + '\'' +
                '}';
    }
}
