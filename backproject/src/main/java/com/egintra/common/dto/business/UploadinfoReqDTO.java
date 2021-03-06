package com.egintra.common.dto.business;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 日志信息记录
 *
 * @author zhangyunting
 * @date 2021/9/8
 */
public class UploadinfoReqDTO {

    /**
     *单位Id
     */
    private String departId;
	
    /**
     * 账套id
     */
    private String accountId;
	
    /**
     *虚拟发票号
     */
    private String invoiceId;
	
    /**
     *业务类别  1：机动车  2：驾驶证
     */
    private String ywlb;
	
    /**
     *业务流水号
     */
    private String lsh;
	
    /**
     *业务收费项目
     */
    private String sfxm;
	
    /**
     *业务收费金额
     */
    private BigDecimal totalMoney;
	
    /**
     *操作用户代号
     */
    private String userId;
	
    /**
     *操作用户ip
     */
    private String userIp;
	
    /**
     *数据产生时间
     */
    private Date date;
	
    /**
     *上传代码值 0：未成功上传 1：已上传    3:手工置为不传
     */
    private String code;
	
    /**
     *返回信息描述
     */
    private String msg;
	
    /**
     *更新时间
     */
    private Date gxsj;
	
    /**
     *id
     */
    private String id;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getSfxm() {
        return sfxm;
    }

    public void setSfxm(String sfxm) {
        this.sfxm = sfxm;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
