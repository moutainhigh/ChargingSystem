package com.egintra.common.dto.business;

/**
 * 机动车 解锁需要的参数
 *
 * @author zhangyunting
 * @date 2021/9/8
 */
public class VUnlockReqDTO {

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 金额
     */
    private String bz;

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "Unlock{" +
                "lsh='" + lsh + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", hphm='" + hphm + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}
