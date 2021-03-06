package com.egintra.common.dto;

/**
 * 编码模块相关接口请求对象参数
 *
 * @author liushihao
 * @date 2021/8/25
 */
public class PubSimplenumberReqDTO extends CommonPage {
    /**
     * 内码
     */
    private String id;

    /**
     * 分类内码
     */
    private String cateId;

    /**
     * 分类内码
     */
    private String cateNm;

    /**
     * 名称
     */
    private String name;

    /**
     * 名称
     */
    private String code;

    /**
     * 状态
     */
    private String recordStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
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

    public String getCateNm() {
        return cateNm;
    }

    public void setCateNm(String cateNm) {
        this.cateNm = cateNm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PubSimplenumberReqDTO{" +
                "id='" + id + '\'' +
                ", cateId='" + cateId + '\'' +
                ", cateNm='" + cateNm + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                '}';
    }
}
