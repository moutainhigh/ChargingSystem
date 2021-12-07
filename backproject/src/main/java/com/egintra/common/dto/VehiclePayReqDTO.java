package com.egintra.common.dto;

import com.egintra.common.dto.pay.FeePayparaRespDTO;

import java.math.BigDecimal;

/**
 * 动车业务缴费信息请求对象
 *
 * @author zzyt
 * @date 2021/08/06
 */
public class VehiclePayReqDTO {

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 业务类型
     */
    private String ywlx;


    /**
     * 业务原因
     */
    private String ywyy;

    /**
     * 项目明细编号
     */
    private String projectId;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 项目明细名称
     */
    private String projectName;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 项目所属财政大类
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
     * 材料价格
     */
    private BigDecimal materialCost;

    /**
     * 财政部门执行号
     */
    private String financeCode;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 部门管理
     */
    private String glbm;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 是否可选
     */
    private String options;

    /**
     * 价格
     */
    private String price;

    /**
     * 参数设置
     */
    private FeePayparaRespDTO dto;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public FeePayparaRespDTO getDto() {
        return dto;
    }

    public void setDto(FeePayparaRespDTO dto) {
        this.dto = dto;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "VehiclePayReqDTO{" +
                "hpzl='" + hpzl + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", ywyy='" + ywyy + '\'' +
                ", projectId='" + projectId + '\'' +
                ", quantity=" + quantity +
                ", projectName='" + projectName + '\'' +
                ", unitPrice=" + unitPrice +
                ", classId='" + classId + '\'' +
                ", provinceRate='" + provinceRate + '\'' +
                ", cityRate='" + cityRate + '\'' +
                ", materialCost=" + materialCost +
                ", financeCode='" + financeCode + '\'' +
                ", hphm='" + hphm + '\'' +
                ", glbm='" + glbm + '\'' +
                ", lsh='" + lsh + '\'' +
                ", dto=" + dto +
                '}';
    }
}
