package com.egintra.common.dto.sys;

/**
 * 数据访问参数设置模块保存参数对象
 *
 * @author liushihao
 * @date 2021/6/8
 */
public class FeeGetDataSetSaveReqDTO {
    /**
     * 项目模块id
     */
    private String modelid;

    /**
     * 系统类别
     */
    private String xtlb;

    /**
     * 综合平台访问接口ID
     */
    private String jkid;

    /**
     * 访问接口名称
     */
    private String jkmc;

    /**
     * 访问接口序列号
     */
    private String jkxlh;

    /**
     * 访问接口用户标志
     */
    private String yhbz;

    /**
     * 访问接口单位名称
     */
    private String dwmc;

    /**
     * 访问接口单位机构代码
     */
    private String dwjgdm;

    /**
     * 访问接口类别 r:读 w:写
     */
    private String jklb;

    /**
     * 访问webip
     */
    private String webip;

    /**
     * 代理访问ip
     */
    private String agentip;

    /**
     * 访问对应表名
     */
    private String tables;

    /**
     * 获取数据方式 1：webservice  2:下发库
     */
    private String datafrom;

    /**
     * 访问接口IP 1:webip  2:agentip
     */
    private String ipfrom;

    /**
     * 发证机关区分地市
     */
    private String fzjg;

    /**
     * 标志
     */
    private String bz;

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getXtlb() {
        return xtlb;
    }

    public void setXtlb(String xtlb) {
        this.xtlb = xtlb;
    }

    public String getJkid() {
        return jkid;
    }

    public void setJkid(String jkid) {
        this.jkid = jkid;
    }

    public String getJkmc() {
        return jkmc;
    }

    public void setJkmc(String jkmc) {
        this.jkmc = jkmc;
    }

    public String getJkxlh() {
        return jkxlh;
    }

    public void setJkxlh(String jkxlh) {
        this.jkxlh = jkxlh;
    }

    public String getYhbz() {
        return yhbz;
    }

    public void setYhbz(String yhbz) {
        this.yhbz = yhbz;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwjgdm() {
        return dwjgdm;
    }

    public void setDwjgdm(String dwjgdm) {
        this.dwjgdm = dwjgdm;
    }

    public String getJklb() {
        return jklb;
    }

    public void setJklb(String jklb) {
        this.jklb = jklb;
    }

    public String getWebip() {
        return webip;
    }

    public void setWebip(String webip) {
        this.webip = webip;
    }

    public String getAgentip() {
        return agentip;
    }

    public void setAgentip(String agentip) {
        this.agentip = agentip;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getDatafrom() {
        return datafrom;
    }

    public void setDatafrom(String datafrom) {
        this.datafrom = datafrom;
    }

    public String getIpfrom() {
        return ipfrom;
    }

    public void setIpfrom(String ipfrom) {
        this.ipfrom = ipfrom;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "FeeGetDataSetRespDTO{" +
                "modelid='" + modelid + '\'' +
                ", xtlb='" + xtlb + '\'' +
                ", jkid='" + jkid + '\'' +
                ", jkmc='" + jkmc + '\'' +
                ", jkxlh='" + jkxlh + '\'' +
                ", yhbz='" + yhbz + '\'' +
                ", dwmc='" + dwmc + '\'' +
                ", dwjgdm='" + dwjgdm + '\'' +
                ", jklb='" + jklb + '\'' +
                ", webip='" + webip + '\'' +
                ", agentip='" + agentip + '\'' +
                ", tables='" + tables + '\'' +
                ", datafrom='" + datafrom + '\'' +
                ", ipfrom='" + ipfrom + '\'' +
                ", fzjg='" + fzjg + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}
