package com.egintra.common.dto.business;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 驾驶证解锁信息请求DTO
 *
 * @author zhangyunting
 * @date 2021/9/8
 */
public class DUnlockReqDTO {

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
    private Date sfsj;

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

    public Date getSfsj() {
        return sfsj;
    }

    public void setSfsj(Date sfsj) {
        this.sfsj = sfsj;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }
}
