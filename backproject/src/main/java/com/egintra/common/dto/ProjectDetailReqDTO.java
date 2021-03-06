package com.egintra.common.dto;

import java.math.BigDecimal;

/**
 * 项目明细信息DTO
 *
 * @author zyt
 * @date 2021/07/28
 */
public class ProjectDetailReqDTO extends CommonPage {

    /**
     * 明细id
     */
    private String projectId;

    /**
     * 明细名称
     */
    private String projectName;

    /**
     * 大类名称
     */
    private String className;

    /**
     * 价格
     */
    private BigDecimal unitPrice;

    /**
     * 大类id
     */
    private String classId;

    /**
     * 收费项目数量
     */
    private String quantity;

    /**
     * 收费项目可选必选 0：可选 1：必选
     */
    private String options;

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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ProjectDetailReqDTO{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", className='" + className + '\'' +
                ", unitPrice=" + unitPrice +
                ", classId='" + classId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
