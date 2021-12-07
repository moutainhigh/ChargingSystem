package com.egintra.common.dto.pay;

/**
 * 调用综合平台 机动车缴费需要的参数
 *
 * @author zyt
 * @date  2021/8/18
 */
public class VechicleDTO {

    /**
     * 流水号
     */
    private String lsh;

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    @Override
    public String toString() {
        return "VechicleDTO{" +
                "lsh='" + lsh + '\'' +
                '}';
    }
}
