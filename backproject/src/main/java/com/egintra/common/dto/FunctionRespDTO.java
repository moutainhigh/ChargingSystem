package com.egintra.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 功能菜单信息返回DTO
 *
 * @author liushihao
 * @date 2021/06/22
 */
public class FunctionRespDTO {

    /**
     * ID
     */
    private String id;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 顺序
     */
    private String orderNo;

    /**
     * 状态
     */
    private String recordStatus;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 分类内码ID
     */
    private String funccateId;

    /**
     * 分类内码名称
     */
    private String funccateNm;

    /**
     * 下级功能菜单信息
     */
    private List<FunctionRespDTO> childList;

    /**
     * 是否配置权限
     */
    private String isPower;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordCreateTm;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFunccateId() {
        return funccateId;
    }

    public void setFunccateId(String funccateId) {
        this.funccateId = funccateId;
    }

    public List<FunctionRespDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<FunctionRespDTO> childList) {
        this.childList = childList;
    }

    public String getFunccateNm() {
        return funccateNm;
    }

    public void setFunccateNm(String funccateNm) {
        this.funccateNm = funccateNm;
    }

    public String getIsPower() {
        return isPower;
    }

    public void setIsPower(String isPower) {
        this.isPower = isPower;
    }

    public Date getRecordCreateTm() {
        return recordCreateTm;
    }

    public void setRecordCreateTm(Date recordCreateTm) {
        this.recordCreateTm = recordCreateTm;
    }

    @Override
    public String toString() {
        return "FunctionRespDTO{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", funccateId='" + funccateId + '\'' +
                ", funccateNm='" + funccateNm + '\'' +
                ", childList=" + childList +
                ", isPower='" + isPower + '\'' +
                ", recordCreateTm=" + recordCreateTm +
                '}';
    }
}
