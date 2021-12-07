package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发票作废表
 *
 * @author liushihao
 * @date 2021/9/8
 */
@TableName("FEE_INVOICE_GIVEUP")
public class FeeInvoiceGiveUp extends Model<FeeInvoiceGiveUp> {

    private static final long serialVersionUID = 1826180154085008979L;

    /**
     * 单位代码
     */
    @TableField(value = "department")
    private String department;

    /**
     * 账套号
     */
    @TableField(value = "account_id")
    private String accountId;

    /**
     * 发票号
     */
    @TableField(value = "invoice_id")
    private String invoiceId;

    /**
     * 纸质发票号
     */
    @TableField(value = "paper_id")
    private String paperId;

    /**
     * 金额
     */
    @TableField(value = "total_money")
    private BigDecimal totalMoney;

    /**
     * 收费日期
     */
    @TableField(value = "dates")
    private Date dates;

    /**
     * 作废用户代号
     */
    @TableField(value = "userid")
    private String userid;

    /**
     * 作废用户姓名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 作废IP地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 作废原因
     */
    @TableField(value = "cause")
    private String cause;

    /**
     * 作废日期
     */
    @TableField(value = "giveupdates")
    private Date giveupdates;

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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getGiveupdates() {
        return giveupdates;
    }

    public void setGiveupdates(Date giveupdates) {
        this.giveupdates = giveupdates;
    }

    @Override
    public String toString() {
        return "FeeInvoiceGiveUp{" +
                "department='" + department + '\'' +
                ", accountId='" + accountId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", paperId='" + paperId + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", dates='" + dates + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", cause='" + cause + '\'' +
                ", giveupdates='" + giveupdates + '\'' +
                '}';
    }
}
