package com.egintra.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 动车业务缴费信息请求对象
 *
 * @author zzyt
 * @date 2021/08/06
 */
public class InvoiceReqDTO extends CommonPage{

    /**
     * id
     */
    private String id;

    /**
     * 单位代码
     */
    private String department;

    /**
     * 单位名称
     */
    private String departName;

    /**
     * 账套号
     */
    private String accountId;

    /**
     * 系统发票号
     */
    private String invoiceId;

    /**
     * 纸质发票号 目前12位
     */
    private String paperId;

    /**
     * 交款人
     */
    private String payUnit;

    /**
     * 交款方式01：现金  03：Pos 04：银行卡 05：线上
     */
    private String payWay;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

    /**
     * 纯收入
     */
    private BigDecimal totalPure;

    /**
     * 收款人代号
     */
    private String receiverId;

    /**
     * 收款人姓名
     */
    private String receiver;

    /**
     * 收款Pos终端号
     */
    private String posId;

    /**
     * 收费日期
     */
    private Date dates;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 写入时间
     */
    private Date dtimes;

    /**
     * 执收单位编码
     */
    private String receiveAccount;

    /**
     * 发票校验码
     */
    private String checkNumber;

    /**
     * Pos支付返回信息
     */
    private String posInfo;

    /**
     * 发票状态 1：正常 0：作废 2：负数发票
     */
    private String status;

    /**
     * 发票类型 1：A101票据 2：电子发票';
     */
    private String types;

    /**
     * 交款人身份证明号码
     */
    private String sfzmhm;

    /**
     * 业务流水号
     */
    private String lsh;

    /**
     * 准驾车型或者号牌种类
     */
    private String cxhp;

    /**
     * 业务类型 注意考试费补考费
     */
    private String ywlx;

    /**
     * 业务原因 注意考试费补考费
     */
    private String ywyy;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

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

    public String getPayUnit() {
        return payUnit;
    }

    public void setPayUnit(String payUnit) {
        this.payUnit = payUnit;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalPure() {
        return totalPure;
    }

    public void setTotalPure(BigDecimal totalPure) {
        this.totalPure = totalPure;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Date getDtimes() {
        return dtimes;
    }

    public void setDtimes(Date dtimes) {
        this.dtimes = dtimes;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getPosInfo() {
        return posInfo;
    }

    public void setPosInfo(String posInfo) {
        this.posInfo = posInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getCxhp() {
        return cxhp;
    }

    public void setCxhp(String cxhp) {
        this.cxhp = cxhp;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getYwyy() {
        return ywyy;
    }

    public void setYwyy(String ywyy) {
        this.ywyy = ywyy;
    }

    @Override
    public String toString() {
        return "InvoiceReqDTO{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", accountId='" + accountId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", paperId=" + paperId +
                ", payUnit='" + payUnit + '\'' +
                ", payWay='" + payWay + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", totalPure='" + totalPure + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", receiver='" + receiver + '\'' +
                ", posId='" + posId + '\'' +
                ", dates=" + dates +
                ", dtimes=" + dtimes +
                ", receiveAccount='" + receiveAccount + '\'' +
                ", checkNumber='" + checkNumber + '\'' +
                ", posInfo='" + posInfo + '\'' +
                ", status='" + status + '\'' +
                ", types='" + types + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", lsh='" + lsh + '\'' +
                ", cxhp='" + cxhp + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                '}';
    }
}
