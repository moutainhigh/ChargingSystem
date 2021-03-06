package com.egintra.common.dto.pay;

import java.math.BigDecimal;

/**
 * 解锁时驾驶证请求平台接口对象
 *
 * @author liushihao
 * @date 2021/8/12
 */
public class DriveUnlockReqDTO {

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 收费项目
     */
    private String sfxm;

    /**
     * 金额
     */
    private BigDecimal je;

    /**
     * 收费人
     */
    private String sfr;

    /**
     * 收费时间
     */
    private String sfsj;

    /**
     * 标记
     */
    private String bj;

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

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public String getSfr() {
        return sfr;
    }

    public void setSfr(String sfr) {
        this.sfr = sfr;
    }

    public String getSfsj() {
        return sfsj;
    }

    public void setSfsj(String sfsj) {
        this.sfsj = sfsj;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    @Override
    public String toString() {
        return "DriveUnlockReqDTO{" +
                "lsh='" + lsh + '\'' +
                ", sfxm='" + sfxm + '\'' +
                ", je='" + je + '\'' +
                ", sfr='" + sfr + '\'' +
                ", sfsj='" + sfsj + '\'' +
                ", bj='" + bj + '\'' +
                '}';
    }
}
