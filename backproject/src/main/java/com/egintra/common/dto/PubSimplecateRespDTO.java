package com.egintra.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 编码分类模块请求对象返回参数
 *
 * @author liushihao
 * @date 2021/8/25
 */
public class PubSimplecateRespDTO {
    /**
     * 分类内码
     */
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类码值
     */
    private String code;

    /**
     * 状态
     */
    private String recordStatus;

    /**
     * 创建人编码
     */
    private String recordCreateCd;

    /**
     * 创建人名称
     */
    private String recordCreateNm;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordCreateTm;

    /**
     * 修改人编码
     */
    private String recordModifyCd;

    /**
     * 修改人名称
     */
    private String recordModifyNm;

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
        return "PubSimplecateRespDTO{" +
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
