package com.egintra.common.dto.pay;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 线上支付信息
 *
 * @author zyt
 * @date 2021/8/18
 */
public class PayReqDTO {

    /**
     * 支付订单号
     */
    private String orderId;

    /**
     * 业务流水号
     */
    private String lsh;

    /**
     * 支付金额
     */
    private BigDecimal totalfee;

    /**
     * 支付方式
     */
    private String zffs;

    /**
     * 支付时间
     */
    private Date zfsj;

    /**
     * 支付标记
     */
    private String zfbj;

    /**
     *
     */
    private String hxbj;

    /**
     * 管理部门
     */
    private String glbm;

    /**
     * 备注1
     */
    private String bz1;

    /**
     * 备注2
     */
    private String bz2;

    /**
     * 备注3
     */
    private String bz3;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public BigDecimal getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(BigDecimal totalfee) {
        this.totalfee = totalfee;
    }

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }

    public Date getZfsj() {
        return zfsj;
    }

    public void setZfsj(Date zfsj) {
        this.zfsj = zfsj;
    }

    public String getZfbj() {
        return zfbj;
    }

    public void setZfbj(String zfbj) {
        this.zfbj = zfbj;
    }

    public String getHxbj() {
        return hxbj;
    }

    public void setHxbj(String hxbj) {
        this.hxbj = hxbj;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }
}
