package com.egintra.common.dto;

/**
 * 材料信息DTO
 *
 * @author zyt
 * @date 2021/07/28
 */
public class VehicleReqDTO extends CommonPage {

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 号牌种类名称
     */
    private String hpzlName;

    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 业务类型名称
     */
    private String ywlxName;
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
     * 项目id
     */
    private String pId;

    /**
     * 收费项目可选必选 0：可选 1：必选
     */
    private String options;

    public String getHpzlName() {
        return hpzlName;
    }

    public void setHpzlName(String hpzlName) {
        this.hpzlName = hpzlName;
    }

    public String getYwlxName() {
        return ywlxName;
    }

    public void setYwlxName(String ywlxName) {
        this.ywlxName = ywlxName;
    }

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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "VehicleReqDTO{" +
                "hpzl='" + hpzl + '\'' +
                ", hpzlName='" + hpzlName + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywlxName='" + ywlxName + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", ywmc='" + ywmc + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", pId='" + pId + '\'' +
                ", options='" + options + '\'' +
                '}';
    }
}
