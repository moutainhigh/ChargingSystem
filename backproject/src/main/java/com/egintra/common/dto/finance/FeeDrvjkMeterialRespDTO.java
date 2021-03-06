package com.egintra.common.dto.finance;

import com.egintra.common.dto.pay.FeePayparaRespDTO;

import java.math.BigDecimal;

/**
 * 获取驾驶证业务缴费信息返回DTO
 *
 * @author liushihao
 * @date 2021/8/6
 */
public class FeeDrvjkMeterialRespDTO {
    /**
     * 准驾车型
     */
    private String zjcx;

    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 业务原因
     */
    private String ywyy;

    /**
     * 收费项目代码
     */
    private String projectId;

    /**
     * 收费项目数量
     */
    private String quantity;

    /**
     * 收费项目名称
     */
    private String projectName;

    /**
     * 收费项目单价
     */
    private BigDecimal unitPrice;

    /**
     * 收费项目类别
     */
    private String classId;

    /**
     * 上缴省财政比率
     */
    private String provinceRate;

    /**
     * 上缴市财政比率
     */
    private String cityRate;

    /**
     * 财政执行号
     */
    private String financeCode;

    /**
     * 材料费
     */
    private BigDecimal cost;

    /**
     * 业务办理部门
     */
    private String ywblbm;

    /**
     * 线上支付信息参数设置返回DTO
     */
    private FeePayparaRespDTO feePayparaRespDTO;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 身份证号码
     */
    private String sfzhm;

    /**
     * 收费项目可选必选 0：可选 1：必选
     */
    private String options;

    /**
     * 价格
     */
    private String price;

    /**
     * 单位
     */
    private String department;

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getYwblbm() {
        return ywblbm;
    }

    public void setYwblbm(String ywblbm) {
        this.ywblbm = ywblbm;
    }

    public FeePayparaRespDTO getFeePayparaRespDTO() {
        return feePayparaRespDTO;
    }

    public void setFeePayparaRespDTO(FeePayparaRespDTO feePayparaRespDTO) {
        this.feePayparaRespDTO = feePayparaRespDTO;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getYwyy() {
        return ywyy;
    }

    public void setYwyy(String ywyy) {
        this.ywyy = ywyy;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(String provinceRate) {
        this.provinceRate = provinceRate;
    }

    public String getCityRate() {
        return cityRate;
    }

    public void setCityRate(String cityRate) {
        this.cityRate = cityRate;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "FeeDrvjkMeterialRespDTO{" +
                "zjcx='" + zjcx + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", projectId='" + projectId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", projectName='" + projectName + '\'' +
                ", unitPrice=" + unitPrice +
                ", classId='" + classId + '\'' +
                ", provinceRate='" + provinceRate + '\'' +
                ", cityRate='" + cityRate + '\'' +
                ", financeCode='" + financeCode + '\'' +
                ", cost=" + cost +
                ", ywblbm='" + ywblbm + '\'' +
                ", feePayparaRespDTO=" + feePayparaRespDTO +
                ", lsh='" + lsh + '\'' +
                ", xm='" + xm + '\'' +
                ", sfzhm='" + sfzhm + '\'' +
                ", options='" + options + '\'' +
                ", price='" + price + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
