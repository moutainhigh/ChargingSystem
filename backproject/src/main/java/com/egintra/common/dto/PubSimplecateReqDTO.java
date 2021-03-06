package com.egintra.common.dto;

/**
 * 编码分类模块请求对象参数
 *
 * @author liushihao
 * @date 2021/8/25
 */
public class PubSimplecateReqDTO extends CommonPage {

    /**
     * 分类内码
     */
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类名称
     */
    private String code;

    /**
     * 状态
     */
    private String recordStatus;

    /**
     * 是否继续
     */
    private String isContinue;

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

    public String getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(String isContinue) {
        this.isContinue = isContinue;
    }

    @Override
    public String toString() {
        return "PubSimplecateReqDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", isContinue='" + isContinue + '\'' +
                '}';
    }
}
