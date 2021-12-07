package com.egintra.common.dto.finance;

import com.egintra.common.dto.ProjectDetailReqDTO;

import java.util.List;

/**
 * 驾驶证业务收费新增、更新请求DTO
 *
 * @author liushihao
 * @date 2021/8/19
 */
public class FeeDrvjkChangeReqDTO {

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
     * 收费项目
     */
    private List<ProjectDetailReqDTO> payProject;

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

    public List<ProjectDetailReqDTO> getPayProject() {
        return payProject;
    }

    public void setPayProject(List<ProjectDetailReqDTO> payProject) {
        this.payProject = payProject;
    }

    @Override
    public String toString() {
        return "FeeDrvjkReqDTO{" +
                "zjcx='" + zjcx + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", ywmc='" + ywmc + '\'' +
                ", payProject=" + payProject +
                '}';
    }
}
