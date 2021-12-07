package com.egintra.common.dto.finance;

/**
 * 驾驶证业务收费信息设置返回DTO
 *
 * @author liushihao
 * @date 2021/7/28
 */
public class FeeDrvjkRespDTO {

    /**
     * 准驾车型
     */
    private String zjcx;

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
     * 收费项目代码
     */
    private String projectId;

    /**
     * 收费项目名称
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
        return "FeeDrvjkRespDTO{" +
                "zjcx='" + zjcx + '\'' +
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
