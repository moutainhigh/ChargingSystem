package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("fee_vehjk")
public class Vehicle {

    /**
     * 号牌种类
     */
    private String hpzl;


    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 业务原因
     */
    private String ywyy;

    /**
     * 业务名称
     */
    private String ywmc;

    /**
     * 收费项目明细代码
     */
    private String projectId;

    /**
     * 明细名称
     */
    private String projectName;


    /**
     * 收费项目数量
     */
    private String quantity;


    /**
     * 收费项目可选必选 0：可选 1：必选
     */
    private String options;


    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
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

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
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
        return "Vehicle{" +
                "hpzl='" + hpzl + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", ywmc='" + ywmc + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
