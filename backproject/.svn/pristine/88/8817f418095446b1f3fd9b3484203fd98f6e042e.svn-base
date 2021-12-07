package com.egintra.common.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 分类菜单请求DTO
 *
 * @author liushihao
 * @date 2021/06/22
 */
public class RightFunccateReqDTO extends CommonPage {

    /**
     * 分类内码
     */
    private String id;

    /**
     * 上级ID
     */
    private String parentId;

    /**
     * 分类名称
     */
    @NotNull(message = "分类名称不能为空")
    private String name;

    /**
     * 分类图片
     */
    private String img;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private String orderNo;

    /**
     * 是否继续
     */
    private String isContinue;

    /**
     * 修改日期
     */
    private Date recordModifyTm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(String isContinue) {
        this.isContinue = isContinue;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    @Override
    public String toString() {
        return "RightFunccateReqDTO{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", isContinue='" + isContinue + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                '}';
    }
}
