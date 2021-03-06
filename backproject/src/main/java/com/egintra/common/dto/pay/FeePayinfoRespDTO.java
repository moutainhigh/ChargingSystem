package com.egintra.common.dto.pay;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付请求信息表返回结果
 *
 * @author liushihao
 * @date 2021/8/11
 */
public class FeePayinfoRespDTO {

    /**
     * 支付订单号
     */
    private String orderid;

    /**
     * 支付网址
     */
    private String zfwz;

    /**
     * 业务类别 1：车 2：驾'
     */
    private String ywlb;

    /**
     * 业务流水号
     */
    private String lsh;

    /**
     * 支付金额
     */
    private BigDecimal je;

    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 业务原因
     */
    private String ywyy;

    /**
     * 管理部门
     */
    private String glbm;

    /**
     * 商户代码
     */
    private String shdm;

    /**
     * 商户柜台代码
     */
    private String shgtdm;

    /**
     * 分行代码
     */
    private String fhdm;

    /**
     * 币种
     */
    private String bizh;

    /**
     * 校验码
     */
    private String jym;

    /**
     * 交款类型
     */
    private String jklx;

    /**
     * 公钥后30位
     */
    private String gkey;

    /**
     * 网关类型
     */
    private String wglx;

    /**
     * 发证机关
     */
    private String fzjg;

    /**
     * 用户代号
     */
    private String yhdh;

    /**
     * 用户姓名
     */
    private String yhxm;

    /**
     * 支付请求ip地址
     */
    private String ip;

    /**
     * 对应账套
     */
    private String dyzt;

    /**
     * 交款人
     */
    private String jkr;

    /**
     * 交款人身份证明号码
     */
    private String sfzmhm;

    /**
     * 缴费类别
     */
    private String jflb;

    /**
     * 支付方式
     */
    private String zffs;

    /**
     * 日期
     */
    private Date dates;

    /**
     * 支付标记
     */
    private String zfbj;

    /**
     * 支付描述
     */
    private String zfmsg;

    /**
     * 记账标记
     */
    private String jzbj;

    /**
     * 记账描述
     */
    private String jzmsg;

    /**
     * 解锁标记
     */
    private String jsbj;

    /**
     * 解锁描述
     */
    private String jsmsg;

    /**
     * 更新时间
     */
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
        return "PayinfoReqDTO{" +
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
