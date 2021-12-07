package com.egintra.common.dto.pay;

import java.math.BigDecimal;

/**
 * 收费业务统计返回DTO
 *
 * @author liushihao
 * @date 2021/9/16
 */
public class FeeInvoiceDetailRespDTO {

    /**
     * 部门
     */
    private String department;

    /**
     * 账套号
     */
    private String accountId;

    /**
     * 项目编码
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 所有数量
     */
    private String allQuantity;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

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

    public String getAllQuantity() {
        return allQuantity;
    }

    public void setAllQuantity(String allQuantity) {
        this.allQuantity = allQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "FeeInvoiceDetailRespDTO{" +
                "department='" + department + '\'' +
                ", accountId='" + accountId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", unitPrice=" + unitPrice +
                ", allQuantity='" + allQuantity + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
