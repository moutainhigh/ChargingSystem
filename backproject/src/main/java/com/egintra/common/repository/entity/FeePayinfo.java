package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

@TableName("fee_payinfo")
public class FeePayinfo extends Model<FeePayinfo> {

    private static final long serialVersionUID = 1703239924180685441L;

    /**
     * 支付订单号
     */
    @TableField(value = "orderid")
    private String orderid;

    /**
     * 支付网址
     */
    @TableField(value = "zfwz")
    private String zfwz;

    /**
     * 业务类别 1：车 2：驾'
     */
    @TableField(value = "ywlb")
    private String ywlb;

    /**
     * 业务流水号
     */
    @TableField(value = "lsh")
    private String lsh;

    /**
     * 支付金额
     */
    @TableField(value = "je")
    private BigDecimal je;

    /**
     * 业务类型
     */
    @TableField(value = "ywlx")
    private String ywlx;

    /**
     * 业务原因
     */
    @TableField(value = "ywyy")
    private String ywyy;

    /**
     * 管理部门
     */
    @TableField(value = "glbm")
    private String glbm;

    /**
     * 商户代码
     */
    @TableField(value = "shdm")
    private String shdm;

    /**
     * 商户柜台代码
     */
    @TableField(value = "shgtdm")
    private String shgtdm;

    /**
     * 分行代码
     */
    @TableField(value = "fhdm")
    private String fhdm;

    /**
     * 币种
     */
    @TableField(value = "bizh")
    private String bizh;

    /**
     * 校验码
     */
    @TableField(value = "jym")
    private String jym;

    /**
     * 交款类型
     */
    @TableField(value = "jklx")
    private String jklx;

    /**
     * 公钥后30位
     */
    @TableField(value = "gkey")
    private String gkey;

    /**
     * 网关类型
     */
    @TableField(value = "wglx")
    private String wglx;

    /**
     * 发证机关
     */
    @TableField(value = "fzjg")
    private String fzjg;

    /**
     * 用户代号
     */
    @TableField(value = "yhdh")
    private String yhdh;

    /**
     * 用户姓名
     */
    @TableField(value = "yhxm")
    private String yhxm;

    /**
     * 支付请求ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 对应账套
     */
    @TableField(value = "dyzt")
    private String dyzt;

    /**
     * 交款人
     */
    @TableField(value = "jkr")
    private String jkr;

    /**
     * 交款人身份证明号码
     */
    @TableField(value = "sfzmhm")
    private String sfzmhm;

    /**
     * 缴费类别
     */
    @TableField(value = "jflb")
    private String jflb;

    /**
     * 支付方式
     */
    @TableField(value = "zffs")
    private String zffs;

    /**
     * 日期
     */
    @TableField(value = "dates")
    private Date dates;

    /**
     * 支付标记
     */
    @TableField(value = "zfbj")
    private String zfbj;

    /**
     * 支付描述
     */
    @TableField(value = "zfmsg")
    private String zfmsg;

    /**
     * 记账标记
     */
    @TableField(value = "jzbj")
    private String jzbj;

    /**
     * 记账描述
     */
    @TableField(value = "jzmsg")
    private String jzmsg;

    /**
     * 解锁标记
     */
    @TableField(value = "jsbj")
    private String jsbj;

    /**
     * 解锁描述
     */
    @TableField(value = "jsmsg")
    private String jsmsg;

    /**
     * 更新时间
     */
    @TableField(value = "gxsj")
    private Date gxsj;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getZfwz() {
        return zfwz;
    }

    public void setZfwz(String zfwz) {
        this.zfwz = zfwz;
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

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getYwyy() {
        return ywyy;
    }

    public void setYwyy(String ywyy) {
        this.ywyy = ywyy;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getShdm() {
        return shdm;
    }

    public void setShdm(String shdm) {
        this.shdm = shdm;
    }

    public String getShgtdm() {
        return shgtdm;
    }

    public void setShgtdm(String shgtdm) {
        this.shgtdm = shgtdm;
    }

    public String getFhdm() {
        return fhdm;
    }

    public void setFhdm(String fhdm) {
        this.fhdm = fhdm;
    }

    public String getBizh() {
        return bizh;
    }

    public void setBizh(String bizh) {
        this.bizh = bizh;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getJklx() {
        return jklx;
    }

    public void setJklx(String jklx) {
        this.jklx = jklx;
    }

    public String getGkey() {
        return gkey;
    }

    public void setGkey(String gkey) {
        this.gkey = gkey;
    }

    public String getWglx() {
        return wglx;
    }

    public void setWglx(String wglx) {
        this.wglx = wglx;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getYhdh() {
        return yhdh;
    }

    public void setYhdh(String yhdh) {
        this.yhdh = yhdh;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDyzt() {
        return dyzt;
    }

    public void setDyzt(String dyzt) {
        this.dyzt = dyzt;
    }

    public String getJkr() {
        return jkr;
    }

    public void setJkr(String jkr) {
        this.jkr = jkr;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getJflb() {
        return jflb;
    }

    public void setJflb(String jflb) {
        this.jflb = jflb;
    }

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getZfbj() {
        return zfbj;
    }

    public void setZfbj(String zfbj) {
        this.zfbj = zfbj;
    }

    public String getZfmsg() {
        return zfmsg;
    }

    public void setZfmsg(String zfmsg) {
        this.zfmsg = zfmsg;
    }

    public String getJzbj() {
        return jzbj;
    }

    public void setJzbj(String jzbj) {
        this.jzbj = jzbj;
    }

    public String getJzmsg() {
        return jzmsg;
    }

    public void setJzmsg(String jzmsg) {
        this.jzmsg = jzmsg;
    }

    public String getJsbj() {
        return jsbj;
    }

    public void setJsbj(String jsbj) {
        this.jsbj = jsbj;
    }

    public String getJsmsg() {
        return jsmsg;
    }

    public void setJsmsg(String jsmsg) {
        this.jsmsg = jsmsg;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    @Override
    public String toString() {
        return "FeePayinfo{" +
                "orderid='" + orderid + '\'' +
                ", zfwz='" + zfwz + '\'' +
                ", ywlb='" + ywlb + '\'' +
                ", lsh='" + lsh + '\'' +
                ", je=" + je +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", glbm='" + glbm + '\'' +
                ", shdm='" + shdm + '\'' +
                ", shgtdm='" + shgtdm + '\'' +
                ", fhdm='" + fhdm + '\'' +
                ", bizh='" + bizh + '\'' +
                ", jym='" + jym + '\'' +
                ", jklx='" + jklx + '\'' +
                ", gkey='" + gkey + '\'' +
                ", wglx='" + wglx + '\'' +
                ", fzjg='" + fzjg + '\'' +
                ", yhdh='" + yhdh + '\'' +
                ", yhxm='" + yhxm + '\'' +
                ", ip='" + ip + '\'' +
                ", dyzt='" + dyzt + '\'' +
                ", jkr='" + jkr + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", jflb='" + jflb + '\'' +
                ", zffs='" + zffs + '\'' +
                ", dates=" + dates +
                ", zfbj='" + zfbj + '\'' +
                ", zfmsg='" + zfmsg + '\'' +
                ", jzbj='" + jzbj + '\'' +
                ", jzmsg='" + jzmsg + '\'' +
                ", jsbj='" + jsbj + '\'' +
                ", jsmsg='" + jsmsg + '\'' +
                ", gxsj=" + gxsj +
                '}';
    }
}
