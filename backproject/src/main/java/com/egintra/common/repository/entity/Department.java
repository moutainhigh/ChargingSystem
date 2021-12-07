package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("fee_department")
public class Department extends Model<Department> {

    /**
     * ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * ��λ����
     */
    @TableId(value = "unit_name")
    private String unitName;

    /**
     * ��λ�ȼ�
     */
    @TableId(value = "unit_level")
    private String unitLevel;

    /**
     * ��ע
     */
    @TableId(value = "remark")
    private String remark;

    /**
     * pos����
     */
    @TableId(value = "pos_mode")
    private String posMode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPosMode() {
        return posMode;
    }

    public void setPosMode(String posMode) {
        this.posMode = posMode;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitLevel='" + unitLevel + '\'' +
                ", remark='" + remark + '\'' +
                ", posMode='" + posMode + '\'' +
                '}';
    }
}
