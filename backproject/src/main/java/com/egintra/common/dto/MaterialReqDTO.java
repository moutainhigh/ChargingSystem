package com.egintra.common.dto;

import java.math.BigDecimal;

/**
 * 材料信息DTO
 *
 * @author zyt
 * @date 2021/07/28
 */
public class MaterialReqDTO extends CommonPage {

    /**
     * 材料Id
     */
    private String materialId;

    /**
     * 材料名称
     */
    private String materialName;

    /**
     * 项目明细名称
     */
    private String projectName;

    /**
     * 价格
     */
    private BigDecimal materialCost;

    /**
     * 明细id
     */
    private String projectId;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
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

    @Override
    public String toString() {
        return "MaterialReqDTO{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", materialCost=" + materialCost +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
