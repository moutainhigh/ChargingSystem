package com.egintra.common.dto.business;

import com.egintra.common.dto.CommonPage;

/**
 * 发票作废查询发票信息请求DTO
 *
 * @author liushihao
 * @date 2021/9/8
 */
public class FeeInvoiceGiveUpQueryReqDTO extends CommonPage {

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 身份证明号码
     */
    private String sfzmhm;

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

    @Override
    public String toString() {
        return "FeeInvoiceGiveUpReqDTO{" +
                ", lsh='" + lsh + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", department='" + department + '\'' +
                ", accountId='" + accountId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                '}';
    }
}
