package com.egintra.common.dto.pay;

/**
 * 考试费线上支付请求DTO
 *
 * @author liushihao
 * @date 2021/8/10
 */
public class PayExaminationFeeReqDTO {

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 身份证明号码
     */
    private String sfzmhm;

    /**
     * 考试科目
     */
    private String kskm;

    /**
     * 考试费类型：考试费1 补考费2
     */
    private String ksflb;

    /**
     * 号码（业务流水号或者身份证明号码）
     */
    private String number;

    /**
     * 支付方式
     */
    private String zffs;

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getKskm() {
        return kskm;
    }

    public void setKskm(String kskm) {
        this.kskm = kskm;
    }

    public String getKsflb() {
        return ksflb;
    }

    public void setKsflb(String ksflb) {
        this.ksflb = ksflb;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }

    @Override
    public String toString() {
        return "PayExaminationFeeReqDTO{" +
                "lsh='" + lsh + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", kskm='" + kskm + '\'' +
                ", ksflb='" + ksflb + '\'' +
                ", number='" + number + '\'' +
                ", zffs='" + zffs + '\'' +
                '}';
    }
}
