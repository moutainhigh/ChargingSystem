package com.egintra.common.dto.pay;

/**
 * 调用综合平台 驾驶证缴费需要的参数
 */
public class DriversDTO {

    /**
     * 身份证号码
     */
    private String sfzhm;

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    @Override
    public String toString() {
        return "DriversDTO{" +
                "sfzhm='" + sfzhm + '\'' +
                '}';
    }
}
