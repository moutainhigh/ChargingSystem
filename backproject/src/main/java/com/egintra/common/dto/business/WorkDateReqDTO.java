package com.egintra.common.dto.business;

/**
 * 设置工作日期的实体
 *
 * @author zhangyunting
 * @date 2021/9/8
 */
public class WorkDateReqDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 工作时间
     */
    private String workDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    @Override
    public String toString() {
        return "WorkDateReqDTO{" +
                "userId='" + userId + '\'' +
                ", workDate='" + workDate + '\'' +
                '}';
    }
}
