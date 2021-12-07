package com.egintra.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 动车业务缴费信息请求对象
 *
 * @author zyt
 * @date 2021/08/06
 */
public class InvoiceDetailReqDTO extends CommonPage{

    /**
     * id
     */
    @TableField(value = "id")
    private String id;

    /**
     * 父id
     */
    @TableField(value = "fee_invoice_id")
    private String feeInvoiceId;

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
     * 纸质发票号
     */
    @TableField(value = "paper_id")
    private String paperId;

    /**
     * 收费日期（yyyy-mm-dd）
     */
    @TableField(value = "dates")
    private Date dates;

    /**
     * 发票状态 1：正常 0：作废 2：负数发票
     */
    @TableField(value = "status")
    private String status;

    /**
     * 收费项目编号
     */
    @TableField(value = "project_id")
    private String projectId;

    /**
     * 收费项目名称
     */
    @TableField(value = "project_name")
    private String projectName;

    /**
     * 收费项目单价
     */
    @TableField(value = "unit_price")
    private BigDecimal unitPrice;

    /**
     * 收费项目数量
     */
    @TableField(value = "quantity")
    private String quantity;

    /**
     * 收费项目类别
     */
    @TableField(value = "class_id")
    private String classId;

    /**
     * 上缴省财政比率
     */
    @TableField(value = "province_rate")
    private String provinceRate;

    /**
     * 上缴市财政比率
     */
    @TableField(value = "city_rate")
    private String cityRate;

    /**
     * 材料价格
     */
    @TableField(value = "material_cost")
    private BigDecimal materialCost;

    /**
     * 财政执行号(打印发票时用)
     */
    @TableField(value = "finance_code")
    private String financeCode;

    /**
     * 支付方式
     */
    @TableField(value = "pay_way")
    private String payWay;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 转化时间
     */
    private String chargeDate;

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
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

    public String getFeeInvoiceId() {
        return feeInvoiceId;
    }

    public void setFeeInvoiceId(String feeInvoiceId) {
        this.feeInvoiceId = feeInvoiceId;
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

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(String provinceRate) {
        this.provinceRate = provinceRate;
    }

    public String getCityRate() {
        return cityRate;
    }

    public void setCityRate(String cityRate) {
        this.cityRate = cityRate;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @Override
    public String toString() {
        return "FeeInvoiceDetail{" +
                "id='" + id + '\'' +
                ", feeInvoiceId='" + feeInvoiceId + '\'' +
                ", department='" + department + '\'' +
                ", accountId='" + accountId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", paperId='" + paperId + '\'' +
                ", dates=" + dates +
                ", status='" + status + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", classId='" + classId + '\'' +
                ", provinceRate='" + provinceRate + '\'' +
                ", cityRate='" + cityRate + '\'' +
                ", materialCost='" + materialCost + '\'' +
                ", financeCode='" + financeCode + '\'' +
                ", payWay='" + payWay + '\'' +
                '}';
    }
}
