package com.egintra.common.dto.business;

/**
 * 系统当前时间
 *
 * @author zhangyunting
 * @date 2021/9/8
 */
public class SysDateReqDTO {

    /**
     * 年月日 时分秒
     */
    private String day;

    /**
     * 年月日
     */
    private String strDay;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStrDay() {
        return strDay;
    }

    public void setStrDay(String strDay) {
        this.strDay = strDay;
    }

    @Override
    public String toString() {
        return "SysDate{" +
                "day='" + day + '\'' +
                ", strDay='" + strDay + '\'' +
                '}';
    }
}
