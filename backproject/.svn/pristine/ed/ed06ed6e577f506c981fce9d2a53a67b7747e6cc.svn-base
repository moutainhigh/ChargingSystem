package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 项目大类设置表实体
 *
 * @author liushihao
 * @date 2021/8/18
 */
@TableName("fee_project_class")
public class ProjectClass extends Model<ProjectClass> {

    /**
     * 大类代码
     */
    @TableId(value = "class_id")
    private String classId;

    /**
     * 大类名称
     */
    @TableId(value = "class_name")
    private String className;

    /**
     * 上缴省财政比例
     */
    @TableId(value = "province_rate")
    private double provinceRate;

    /**
     * 上缴市财政比例
     */
    @TableId(value = "city_rate")
    private double cityRate;

    /**
     * 收费项目属性 （1:预算收入 2:财政专户款收入）
     */
    @TableId(value = "project_property")
    private String projectProperty;

    /**
     * 分类业务属性（0:其他 1:车 2:驾）
     */
    @TableId(value = "class_property")
    private String classProperty;

    /**
     * 财政部门执行号
     */
    @TableId(value = "finance_code")
    private String financeCode;

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

    public double getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(double provinceRate) {
        this.provinceRate = provinceRate;
    }

    public double getCityRate() {
        return cityRate;
    }

    public void setCityRate(double cityRate) {
        this.cityRate = cityRate;
    }

    public String getProjectProperty() {
        return projectProperty;
    }

    public void setProjectProperty(String projectProperty) {
        this.projectProperty = projectProperty;
    }

    public String getClassProperty() {
        return classProperty;
    }

    public void setClassProperty(String classProperty) {
        this.classProperty = classProperty;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    @Override
    public String toString() {
        return "ProjectClass{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", provinceRate=" + provinceRate +
                ", cityRate=" + cityRate +
                ", projectProperty='" + projectProperty + '\'' +
                ", classProperty='" + classProperty + '\'' +
                ", financeCode='" + financeCode + '\'' +
                '}';
    }
}
