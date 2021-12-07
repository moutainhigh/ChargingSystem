package com.egintra.common.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 数据访问参数设置模块实体
 *
 * @author liushihao
 * @date 2021/6/8
 */
@TableName("fee_getDataSet")
public class FeeGetDataSet extends Model<FeeGetDataSet> {

    private static final long serialVersionUID = 2988651116109645449L;

    /**
     * 项目模块id
     */
    @TableId(value = "modelid")
    private String modelid;

    /**
     * 系统类别
     */
    @TableId(value = "xtlb")
    private String xtlb;

    /**
     * 综合平台访问接口ID
     */
    @TableId(value = "jkid")
    private String jkid;

    /**
     * 访问接口名称
     */
    @TableId(value = "jkmc")
    private String jkmc;

    /**
     * 访问接口序列号
     */
    @TableId(value = "jkxlh")
    private String jkxlh;

    /**
     * 访问接口用户标志
     */
    @TableId(value = "yhbz")
    private String yhbz;

    /**
     * 访问接口单位名称
     */
    @TableId(value = "dwmc")
    private String dwmc;

    /**
     * 访问接口单位机构代码
     */
    @TableId(value = "dwjgdm")
    private String dwjgdm;

    /**
     * 访问接口类别 r:读 w:写
     */
    @TableId(value = "jklb")
    private String jklb;

    /**
     * 访问webip
     */
    @TableId(value = "webip")
    private String webip;

    /**
     * 代理访问ip
     */
    @TableId(value = "agentip")
    private String agentip;

    /**
     * 访问对应表名
     */
    @TableId(value = "tables")
    private String tables;

    /**
     * 获取数据方式 1：webservice  2:下发库
     */
    @TableId(value = "datafrom")
    private String datafrom;

    /**
     * 访问接口IP 1:webip  2:agentip
     */
    @TableId(value = "ipfrom")
    private String ipfrom;

    /**
     * 发证机关区分地市
     */
    @TableId(value = "fzjg")
    private String fzjg;

    /**
     * 标志
     */
    @TableId(value = "bz")
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
        return "FeeGetDataSet{" +
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
