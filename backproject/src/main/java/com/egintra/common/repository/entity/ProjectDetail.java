package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;

/**
 * 项目明细设置表实体
 *
 * @author liushihao
 * @date 2021/8/18
 */
@TableName("fee_project_detail")
public class ProjectDetail extends Model<ProjectDetail> {

    /**
     * 项目明细编号
     */
    @TableId(value = "project_id")
    private String projectId;

    /**
     * 项目明细名称
     */
    @TableId(value = "project_name")
    private String projectName;

    /**
     * 项目明细价格
     */
    @TableId(value = "unit_price")
    private BigDecimal unitPrice;

    /**
     * 所属项目大类
     */
    @TableId(value = "class_id")
    private String classId;

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

    @Override
    public String toString() {
        return "ProjectDetail{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", unitPrice=" + unitPrice +
                ", classId='" + classId + '\'' +
                '}';
    }
}
