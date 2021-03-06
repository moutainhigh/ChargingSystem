package com.egintra.common.dto.pay;

/**
 * 线上支付信息参数设置返回DTO
 *
 * @author liushihao
 * @date 2021/8/18
 */
public class FeePayparaRespDTO {

    /**
     * 管理部门和收费系统对应
     */
    private String department;

    /**
     * 账套号
     */
    private String accountid;

    /**
     * 支付网址
     */
    private String zfwz;

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
     * 币种：01
     */
    private String bizh;

    /**
     * 交易码 由银行统一分配为 建行为：520100
     */
    private String jym;

    /**
     * 1- 防钓鱼接口
     */
    private String jklx;

    /**
     * 公钥后30位 仅作为源串参加MD5摘要，不作为参数传递
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getZfwz() {
        return zfwz;
    }

    public void setZfwz(String zfwz) {
        this.zfwz = zfwz;
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

    @Override
    public String toString() {
        return "FeePaypara{" +
                "department='" + department + '\'' +
                ", accountid='" + accountid + '\'' +
                ", zfwz='" + zfwz + '\'' +
                ", shdm='" + shdm + '\'' +
                ", shgtdm='" + shgtdm + '\'' +
                ", fhdm='" + fhdm + '\'' +
                ", bizh='" + bizh + '\'' +
                ", jym='" + jym + '\'' +
                ", jklx='" + jklx + '\'' +
                ", gkey='" + gkey + '\'' +
                ", wglx='" + wglx + '\'' +
                ", fzjg='" + fzjg + '\'' +
                '}';
    }
}
