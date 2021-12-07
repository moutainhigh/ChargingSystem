package com.egintra.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 角色信息返回对象
 *
 * @author liushihao
 * @date 2021/06/18
 */
public class RightRoleRespDTO {

    /**
     * 角色内码
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 数据状态
     */
    private String recordStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordCreateTm;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordModifyTm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    @Override
    public String toString() {
        return "RightRoleRespDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", recordCreateTm='" + recordCreateTm + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                '}';
    }
}
