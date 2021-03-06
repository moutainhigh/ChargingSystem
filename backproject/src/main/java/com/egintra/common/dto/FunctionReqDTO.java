package com.egintra.common.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 菜单查询请求DTO
 *
 * @author liushihao
 * @date 2021/06/22
 */
public class FunctionReqDTO extends CommonPage {

    /**
     * ID
     */
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 分类内码
     */
    @NotNull(message = "分类菜单不能为空")
    private String funccateId;

    /**
     * 功能图标
     */
    private String img;

    /**
     * 功能地址
     */
    @NotNull(message = "功能地址不能为空")
    private String url;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private String orderNo;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 修改日期
     */
    private Date recordModifyTm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunccateId() {
        return funccateId;
    }

    public void setFunccateId(String funccateId) {
        this.funccateId = funccateId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getRecordModifyTm() {
        return recordModifyTm;
    }

    public void setRecordModifyTm(Date recordModifyTm) {
        this.recordModifyTm = recordModifyTm;
    }

    @Override
    public String toString() {
        return "FunctionReqDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", funccateId='" + funccateId + '\'' +
                ", img='" + img + '\'' +
                ", url='" + url + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", roleId='" + roleId + '\'' +
                ", recordModifyTm=" + recordModifyTm +
                '}';
    }
}
