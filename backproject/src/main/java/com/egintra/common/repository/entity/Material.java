package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;

@TableName("fee_material")
public class Material extends Model<Material> {
    /**
     * 材料id
     */
    private String materialId;

    /**
     * 材料名称
     */
    private String materialName;

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

    @Override
    public String toString() {
        return "Material{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialCost=" + materialCost +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
