package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

@TableName("sys_pub_simplecate")
public class PubSimplecate extends Model<PubSimplecate> {

    private static final long serialVersionUID = 6356286604021243638L;

    /**
     * 分类内码
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 分类名称
     */
    @TableId(value = "name")
    private String name;

    /**
     * 分类名称
     */
    @TableId(value = "code")
    private String code;

    /**
     * 状态
     */
    @TableId(value = "record_status")
    private String recordStatus;

    /**
     * 创建人编码
     */
    @TableId(value = "record_create_cd")
    private String recordCreateCd;

    /**
     * 创建人名称
     */
    @TableId(value = "record_create_nm")
    private String recordCreateNm;

    /**
     * 创建时间
     */
    @TableId(value = "record_create_tm")
    private Date recordCreateTm;

    /**
     * 修改人编码
     */
    @TableId(value = "record_modify_cd")
    private String recordModifyCd;

    /**
     * 修改人名称
     */
    @TableId(value = "record_modify_nm")
    private String recordModifyNm;

    /**
     * 修改日期
     */
    @TableId(value = "record_modify_tm")
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordCreateCd() {
        return recordCreateCd;
    }

    public void setRecordCreateCd(String recordCreateCd) {
        this.recordCreateCd = recordCreateCd;
    }

    public String getRecordCreateNm() {
        return recordCreateNm;
    }

    public void setRecordCreateNm(String recordCreateNm) {
        this.recordCreateNm = recordCreateNm;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
    }

    public String getRecordModifyCd() {
        return recordModifyCd;
    }

    public void setRecordModifyCd(String recordModifyCd) {
        this.recordModifyCd = recordModifyCd;
    }

    public String getRecordModifyNm() {
        return recordModifyNm;
    }

    public void setRecordModifyNm(String recordModifyNm) {
        this.recordModifyNm = recordModifyNm;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    @Override
    public String toString() {
        return "PubSimplecate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", recordCreateCd='" + recordCreateCd + '\'' +
                ", recordCreateNm='" + recordCreateNm + '\'' +
                ", recordCreateTm=" + recordCreateTm +
                ", recordModifyCd='" + recordModifyCd + '\'' +
                ", recordModifyNm='" + recordModifyNm + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                '}';
    }
}
