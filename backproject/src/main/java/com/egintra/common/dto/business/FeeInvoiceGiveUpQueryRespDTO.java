package com.egintra.common.dto.business;

import java.util.Date;

/**
 * 发票作废查询返回对象
 *
 * @author liushihao
 * @date 2021/9/8
 */
public class FeeInvoiceGiveUpQueryRespDTO {

    /**
     * ID
     */
    private String id;

    /**
     * 单位
     */
    private String department;

    /**
     * 账套号
     */
    private String accountId;

    /**
     * 发票号
     */
    private String invoiceId;

    /**
     * 纸质发票号
     */
    private String paperId;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 身份证明号码
     */
    private String sfzmhm;

    /**
     * 交款人
     */
    private String payUnit;

    /**
     * 交款日期
     */
    private Date dates;

    /**
     * 交款金额
     */
    private String totalMoney;

    /**
     * 收款人
     */
    private String receiver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getPayUnit() {
        return payUnit;
    }

    public void setPayUnit(String payUnit) {
        this.payUnit = payUnit;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "FeeInvoiceGiveUpQueryRespDTO{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", accountId='" + accountId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", paperId='" + paperId + '\'' +
                ", lsh='" + lsh + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", payUnit='" + payUnit + '\'' +
                ", dates=" + dates +
                ", totalMoney='" + totalMoney + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
