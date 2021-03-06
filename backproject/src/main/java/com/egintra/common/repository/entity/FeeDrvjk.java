package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName(value = "fee_drvjk")
public class FeeDrvjk extends Model<FeeDrvjk> {

    private static final long serialVersionUID = -5316240689238357880L;

    /**
     * 准驾车型
     */
    @TableId(value = "zjcx")
    private String zjcx;

    /**
     * 业务类型
     */
    @TableId(value = "ywlx")
    private String ywlx;

    /**
     * 业务原因
     */
    @TableId(value = "ywyy")
    private String ywyy;

    /**
     * 业务名称
     */
    @TableId(value = "ywmc")
    private String ywmc;

    /**
     * 收费项目代码
     */
    @TableId(value = "project_id")
    private String projectId;

    /**
     * 收费项目数量
     */
    @TableId(value = "quantity")
    private String quantity;

    /**
     * 收费项目可选必选 0：可选 1：必选
     */
    @TableId(value = "options")
    private String options;

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
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
        return "FeeDrvjk{" +
                "zjcx='" + zjcx + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", ywmc='" + ywmc + '\'' +
                ", projectId='" + projectId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
