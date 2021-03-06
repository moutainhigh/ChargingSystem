package com.egintra.common.dto;

/**
 * 考试预约请求对象
 *
 * @author zyt
 * @date 2021/08/06
 */
public class DrvPreasignReqDTO {

    /**
     * 考试费类型：考试费1 补考费2
     */
    private String ksflb;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 考试科目
     */
    private String kskm;

    /**
     * 准考证明编号
     */
    private String zkzmbh;

    /**
     * 身份证明名称
     */
    private String sfzmmc;

    /**
     * 身份证明号码
     */
    private String sfzmhm;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 考试原因
     */
    private String ksyy;

    /**
     * 学习时间
     */
    private String xxsj;

    /**
     * 预约日期
     */
    private String yyrq;

    /**
     * 预考日期
     */
    private String ykrq;

    /**
     * 考试车型
     */
    private String kscx;

    /**
     * 考试地点
     */
    private String ksdd;

    /**
     * 考试场次
     */
    private String kscc;

    /**
     * 考车号牌
     */
    private String kchp;

    /**
     * 经办人
     */
    private String jbr;

    /**
     * 管理部门
     */
    private String glbm;

    /**
     * 代理人
     */
    private String dlr;

    /**
     * 考试成绩
     */
    private String kscj;

    /**
     * 考试日期
     */
    private String ksrq;

    /**
     * 考试次数
     */
    private String kscs;

    /**
     * 考试员1
     */
    private String ksy1;

    /**
     * 考试员2
     */
    private String ksy2;

    /**
     * 校验位
     */
    private String jyw;

    /**
     * 状态
     */
    private String zt;

    /**
     * 培训审核日期
     */
    private String pxshrq;

    /**
     * 是否夜考
     */
    private String sfyk;

    /**
     * 桩考约考日期
     */
    private String zkykrq;

    /**
     * 桩考是否合格
     */
    private String zksfhg;

    /**
     * 车辆种类
     */
    private String clzl;

    /**
     * 教练员
     */
    private String jly;

    /**
     * 桩考扣分
     */
    private String zkkf;

    /**
     * 场考是否已约
     */
    private String ckyy;

    /**
     * 桩位号
     */
    private String zwh;

    /**
     * 业务办理部门
     */
    private String ywblbm;

    /**
     * 预约次数
     */
    private String yycs;

    /**
     * 本次预约考试次数
     */
    private String bcyykscs;

    /**
     * 更新时间
     */
    private String gxsj;

    /**
     * 发证机关
     */
    private String fzjg;

    /**
     * 异地考试实际流水号
     */
    private String sjlsh;

    /**
     * 扩展字段
     */
    private String jyw2;

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getKskm() {
        return kskm;
    }

    public void setKskm(String kskm) {
        this.kskm = kskm;
    }

    public String getZkzmbh() {
        return zkzmbh;
    }

    public void setZkzmbh(String zkzmbh) {
        this.zkzmbh = zkzmbh;
    }

    public String getSfzmmc() {
        return sfzmmc;
    }

    public void setSfzmmc(String sfzmmc) {
        this.sfzmmc = sfzmmc;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getKsyy() {
        return ksyy;
    }

    public void setKsyy(String ksyy) {
        this.ksyy = ksyy;
    }

    public String getXxsj() {
        return xxsj;
    }

    public void setXxsj(String xxsj) {
        this.xxsj = xxsj;
    }

    public String getYyrq() {
        return yyrq;
    }

    public void setYyrq(String yyrq) {
        this.yyrq = yyrq;
    }

    public String getYkrq() {
        return ykrq;
    }

    public void setYkrq(String ykrq) {
        this.ykrq = ykrq;
    }

    public String getKscx() {
        return kscx;
    }

    public void setKscx(String kscx) {
        this.kscx = kscx;
    }

    public String getKsdd() {
        return ksdd;
    }

    public void setKsdd(String ksdd) {
        this.ksdd = ksdd;
    }

    public String getKscc() {
        return kscc;
    }

    public void setKscc(String kscc) {
        this.kscc = kscc;
    }

    public String getKchp() {
        return kchp;
    }

    public void setKchp(String kchp) {
        this.kchp = kchp;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getDlr() {
        return dlr;
    }

    public void setDlr(String dlr) {
        this.dlr = dlr;
    }

    public String getKscj() {
        return kscj;
    }

    public void setKscj(String kscj) {
        this.kscj = kscj;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getKscs() {
        return kscs;
    }

    public void setKscs(String kscs) {
        this.kscs = kscs;
    }

    public String getKsy1() {
        return ksy1;
    }

    public void setKsy1(String ksy1) {
        this.ksy1 = ksy1;
    }

    public String getKsy2() {
        return ksy2;
    }

    public void setKsy2(String ksy2) {
        this.ksy2 = ksy2;
    }

    public String getJyw() {
        return jyw;
    }

    public void setJyw(String jyw) {
        this.jyw = jyw;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getPxshrq() {
        return pxshrq;
    }

    public void setPxshrq(String pxshrq) {
        this.pxshrq = pxshrq;
    }

    public String getSfyk() {
        return sfyk;
    }

    public void setSfyk(String sfyk) {
        this.sfyk = sfyk;
    }

    public String getZkykrq() {
        return zkykrq;
    }

    public void setZkykrq(String zkykrq) {
        this.zkykrq = zkykrq;
    }

    public String getZksfhg() {
        return zksfhg;
    }

    public void setZksfhg(String zksfhg) {
        this.zksfhg = zksfhg;
    }

    public String getClzl() {
        return clzl;
    }

    public void setClzl(String clzl) {
        this.clzl = clzl;
    }

    public String getJly() {
        return jly;
    }

    public void setJly(String jly) {
        this.jly = jly;
    }

    public String getZkkf() {
        return zkkf;
    }

    public void setZkkf(String zkkf) {
        this.zkkf = zkkf;
    }

    public String getCkyy() {
        return ckyy;
    }

    public void setCkyy(String ckyy) {
        this.ckyy = ckyy;
    }

    public String getZwh() {
        return zwh;
    }

    public void setZwh(String zwh) {
        this.zwh = zwh;
    }

    public String getYwblbm() {
        return ywblbm;
    }

    public void setYwblbm(String ywblbm) {
        this.ywblbm = ywblbm;
    }

    public String getYycs() {
        return yycs;
    }

    public void setYycs(String yycs) {
        this.yycs = yycs;
    }

    public String getBcyykscs() {
        return bcyykscs;
    }

    public void setBcyykscs(String bcyykscs) {
        this.bcyykscs = bcyykscs;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getSjlsh() {
        return sjlsh;
    }

    public void setSjlsh(String sjlsh) {
        this.sjlsh = sjlsh;
    }

    public String getJyw2() {
        return jyw2;
    }

    public void setJyw2(String jyw2) {
        this.jyw2 = jyw2;
    }

    public String getKsflb() {
        return ksflb;
    }

    public void setKsflb(String ksflb) {
        this.ksflb = ksflb;
    }

    @Override
    public String toString() {
        return "DrvPreasignReqDTO{" +
                "ksflb='" + ksflb + '\'' +
                ", lsh='" + lsh + '\'' +
                ", kskm='" + kskm + '\'' +
                ", zkzmbh='" + zkzmbh + '\'' +
                ", sfzmmc='" + sfzmmc + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", xm='" + xm + '\'' +
                ", ksyy='" + ksyy + '\'' +
                ", xxsj='" + xxsj + '\'' +
                ", yyrq='" + yyrq + '\'' +
                ", ykrq='" + ykrq + '\'' +
                ", kscx='" + kscx + '\'' +
                ", ksdd='" + ksdd + '\'' +
                ", kscc='" + kscc + '\'' +
                ", kchp='" + kchp + '\'' +
                ", jbr='" + jbr + '\'' +
                ", glbm='" + glbm + '\'' +
                ", dlr='" + dlr + '\'' +
                ", kscj='" + kscj + '\'' +
                ", ksrq='" + ksrq + '\'' +
                ", kscs='" + kscs + '\'' +
                ", ksy1='" + ksy1 + '\'' +
                ", ksy2='" + ksy2 + '\'' +
                ", jyw='" + jyw + '\'' +
                ", zt='" + zt + '\'' +
                ", pxshrq='" + pxshrq + '\'' +
                ", sfyk='" + sfyk + '\'' +
                ", zkykrq='" + zkykrq + '\'' +
                ", zksfhg='" + zksfhg + '\'' +
                ", clzl='" + clzl + '\'' +
                ", jly='" + jly + '\'' +
                ", zkkf='" + zkkf + '\'' +
                ", ckyy='" + ckyy + '\'' +
                ", zwh='" + zwh + '\'' +
                ", ywblbm='" + ywblbm + '\'' +
                ", yycs='" + yycs + '\'' +
                ", bcyykscs='" + bcyykscs + '\'' +
                ", gxsj='" + gxsj + '\'' +
                ", fzjg='" + fzjg + '\'' +
                ", sjlsh='" + sjlsh + '\'' +
                ", jyw2='" + jyw2 + '\'' +
                '}';
    }
}
