package com.egintra.common.dto.reportForm;

/**
 * 报表信息
 *
 * @author zyt
 * @date 2021/08/06
 */
public class ReportReqDTO {

    /**
     *
     */
    private String id;

    /**
     * 编号
     */
    private String bh;

    /**
     * 项目名称
     */
    private String mc;

    /**
     * 本期收入
     */
    private String bqje;

    /**
     * 累计收入
     */
    private String ljje;

    /**
     * 部门
     */
    private String department;

    /**
     * 账套
     */
    private String accountId;

    /**
     * 本期开始时间
     */
    private String bqStartTime;

    /**
     * 本期结束时间
     */
    private String bqEndTime;

    /**
     * 累计开始时间
     */
    private String ljStartTime;

    /**
     * 结束时间
     */
    private String ljEndTime;

    /**
     * 本期(省)
     */
    private String bpje;

    /**
     * 本期(市)
     */
    private String bcje;


    /**
     * 累计(省)
     */
    private String lpje;

    /**
     * 累计(市区)
     */
    private String lcje;


    public String getBpje() {
        return bpje;
    }

    public void setBpje(String bpje) {
        this.bpje = bpje;
    }

    public String getBcje() {
        return bcje;
    }

    public void setBcje(String bcje) {
        this.bcje = bcje;
    }

    public String getLpje() {
        return lpje;
    }

    public void setLpje(String lpje) {
        this.lpje = lpje;
    }

    public String getLcje() {
        return lcje;
    }

    public void setLcje(String lcje) {
        this.lcje = lcje;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBqStartTime() {
        return bqStartTime;
    }

    public void setBqStartTime(String bqStartTime) {
        this.bqStartTime = bqStartTime;
    }

    public String getBqEndTime() {
        return bqEndTime;
    }

    public void setBqEndTime(String bqEndTime) {
        this.bqEndTime = bqEndTime;
    }

    public String getLjStartTime() {
        return ljStartTime;
    }

    public void setLjStartTime(String ljStartTime) {
        this.ljStartTime = ljStartTime;
    }

    public String getLjEndTime() {
        return ljEndTime;
    }

    public void setLjEndTime(String ljEndTime) {
        this.ljEndTime = ljEndTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getBqje() {
        return bqje;
    }

    public void setBqje(String bqje) {
        this.bqje = bqje;
    }

    public String getLjje() {
        return ljje;
    }

    public void setLjje(String ljje) {
        this.ljje = ljje;
    }


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "ReportReqDTO{" +
                "id='" + id + '\'' +
                ", bh='" + bh + '\'' +
                ", mc='" + mc + '\'' +
                ", bqje='" + bqje + '\'' +
                ", ljje='" + ljje + '\'' +
                ", department='" + department + '\'' +
                ", bqStartTime='" + bqStartTime + '\'' +
                ", bqEndTime='" + bqEndTime + '\'' +
                ", ljStartTime='" + ljStartTime + '\'' +
                ", ljEndTime='" + ljEndTime + '\'' +
                '}';
    }
}
