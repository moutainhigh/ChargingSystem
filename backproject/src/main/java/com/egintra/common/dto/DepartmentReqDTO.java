package com.egintra.common.dto;

/**
 * 单位信息DTO
 *
 * @author zyt
 * @date 2021/07/28
 */
public class DepartmentReqDTO extends CommonPage {

    /**
     * 主键
     */
    private String unitRegion;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 单位类型
     */
    private String unitLevel;

    /**
     * 单位名称
     */
    private String levelName;

    /**
     * 单位描述
     */
    private String remark;

    /**
     * POS方式
     */
    private String posMode;

    public String getUnitRegion() {
        return unitRegion;
    }

    public void setUnitRegion(String unitRegion) {
        this.unitRegion = unitRegion;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(String unitLevel) {
        this.unitLevel = unitLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getPosMode() {
        return posMode;
    }

    public void setPosMode(String posMode) {
        this.posMode = posMode;
    }

    @Override
    public String toString() {
        return "DepartmentReqDTO{" +
                "unitRegion='" + unitRegion + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitLevel='" + unitLevel + '\'' +
                ", levelName='" + levelName + '\'' +
                ", remark='" + remark + '\'' +
                ", posMode='" + posMode + '\'' +
                '}';
    }
}
