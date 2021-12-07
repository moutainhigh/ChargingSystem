package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发票主表
 *
 * @author liushihao
 * @date 2021/8/11
 */
@TableName("fee_invoice")
public class FeeInvoice extends Model<FeeInvoice> {

    private static final long serialVersionUID = 2281063378599414901L;

    /**
     * id
     */
    @TableField(value = "id")
    private String id;

    /**
     * 单位代码
     */
    @TableField(value = "department")
    private String department;

    /**
     * 部门名称
     */
    private String departName;

    /**
     * 账套号
     */
    @TableField(value = "account_id")
    private String accountId;

    /**
     * 系统发票号
     */
    @TableField(value = "invoice_id")
    private String invoiceId;

    /**
     * 纸质发票号 目前12位
     */
    @TableField(value = "paper_id")
    private String paperId;

    /**
     * 交款人
     */
    @TableField(value = "pay_unit")
    private String payUnit;

    /**
     * 交款方式01：现金  03：Pos 04：银行卡 05：线上
     */
    @TableField(value = "pay_way")
    private String payWay;

    /**
     * 总金额
     */
    @TableField(value = "total_money")
    private BigDecimal totalMoney;

    /**
     * 纯收入
     */
    @TableField(value = "total_pure")
    private BigDecimal totalPure;

    /**
     * 收款人代号
     */
    @TableField(value = "receiver_id")
    private String receiverId;

    /**
     * 收款人姓名
     */
    @TableField(value = "receiver")
    private String receiver;

    /**
     * 收款Pos终端号
     */
    @TableField(value = "posid")
    private String posid;

    /**
     * 收费日期
     */
    @TableField(value = "dates")
    private Date dates;

    /**
     * 转化时间
     */
    private String chargeDate;

    /**
     * 写入时间
     */
    @TableField(value = "dtimes")
    private Date dtimes;

    /**
     * 执收单位编码
     */
    @TableField(value = "receive_account")
    private String receiveAccount;

    /**
     * 发票校验码
     */
    @TableField(value = "check_number")
    private String checkNumber;

    /**
     * Pos支付返回信息
     */
    @TableField(value = "pos_info")
    private String posInfo;

    /**
     * 开票年度
     */
    @TableField(value = "work_year")
    private String workYear;

    /**
     * 开票月份
     */
    @TableField(value = "work_month")
    private String work_Month;

    /**
     * 发票状态 1：正常 0：作废 2：负数发票
     */
    @TableField(value = "status")
    private String status;

    /**
     * 发票类型 1：A101票据 2：电子发票';
     */
    @TableField(value = "types")
    private String types;

    /**
     * 交款人身份证明号码
     */
    @TableField(value = "sfzmhm")
    private String sfzmhm;

    /**
     * 业务流水号
     */
    @TableField(value = "lsh")
    private String lsh;

    /**
     * 准驾车型或者号牌种类
     */
    @TableField(value = "cxhp")
    private String cxhp;

    /**
     * 业务类型
     */
    @TableField(value = "ywlx")
    private String ywlx;

    /**
     * 业务原因
     */
    @TableField(value = "ywyy")
    private String ywyy;

    /**
     * 业务类别
     */
    @TableField(value = "ywlb")
    private String ywlb;

    /**
     * 打印次数
     */
    @TableField(value = "print_count")
    private String printCount;

    /**
     * 打印人
     */
    @TableField(value = "print_userid")
    private String printUserid;

    /**
     * 打印时间
     */
    @TableField(value = "print_dates")
    private Date printDates;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
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

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
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

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getWork_Month() {
        return work_Month;
    }

    public void setWork_Month(String work_Month) {
        this.work_Month = work_Month;
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

    public String getPrintCount() {
        return printCount;
    }

    public void setPrintCount(String printCount) {
        this.printCount = printCount;
    }

    public String getPrintUserid() {
        return printUserid;
    }

    public void setPrintUserid(String printUserid) {
        this.printUserid = printUserid;
    }

    public Date getPrintDates() {
        return printDates;
    }

    public void setPrintDates(Date printDates) {
        this.printDates = printDates;
    }

    @Override
    public String toString() {
        return "FeeInvoice{" +
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
                ", posid='" + posid + '\'' +
                ", dates=" + dates +
                ", dtimes=" + dtimes +
                ", receiveAccount='" + receiveAccount + '\'' +
                ", checkNumber='" + checkNumber + '\'' +
                ", posInfo='" + posInfo + '\'' +
                ", workYear='" + workYear + '\'' +
                ", work_Month='" + work_Month + '\'' +
                ", status='" + status + '\'' +
                ", types='" + types + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", lsh='" + lsh + '\'' +
                ", cxhp='" + cxhp + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", printCount='" + printCount + '\'' +
                ", printUserid='" + printUserid + '\'' +
                ", printDates=" + printDates +
                '}';
    }
}
