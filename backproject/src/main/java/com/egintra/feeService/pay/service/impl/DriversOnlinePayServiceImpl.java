package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.dto.pay.CommonFieldReqDTO;
import com.egintra.common.dto.pay.DriversDTO;
import com.egintra.common.dto.pay.FeePayparaReqDTO;
import com.egintra.common.dto.pay.FeePayparaRespDTO;
import com.egintra.common.dto.pay.VechicleDTO;
import com.egintra.common.dto.sys.FeeGetDataSetRespDTO;
import com.egintra.common.repository.mapper.FeeDrvjkMapper;
import com.egintra.common.repository.mapper.FeeGetDataSetMapper;
import com.egintra.common.repository.mapper.FeePayparaMapper;
import com.egintra.common.repository.mapper.VehicleOnlinePayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.WebServiceUtil;
import com.egintra.common.utils.XmlFileUtils;
import com.egintra.common.utils.XmlUtils;
import com.egintra.feeService.pay.service.DriversOnlinePayService;
import com.egintra.feeService.pay.service.InvoiceService;
import com.egintra.feeService.pay.service.TemporaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriversOnlinePayServiceImpl implements DriversOnlinePayService {

    @Resource
    private InvoiceService invoiceService;
    @Resource
    private FeeDrvjkMapper feeDrvjkMapper;
    @Resource
    private VehicleOnlinePayMapper vehicleOnlinePayMapper;
    @Resource
    private FeeGetDataSetMapper feeGetDataSetMapper;
    @Resource
    private FeePayparaMapper feePayparaMapper;
    @Resource
    private TemporaryService temporaryService;
    @Autowired
    private WebServiceUtil webServiceUtil;

    @Value("${DOnlinePay.fee.onLine.modelid}")
    private String DPayModelId;

    String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
            "<root>\n" +
            "<head>\n" +
            "<code>1</code>\n" +
            "<message>%E6%95%B0%E6%8D%AE%E4%B8%8B%E8%BD%BD%E6%88%90%E5%8A%9F%EF%BC%81</message>\n" +
            "<rownum>1</rownum>\n" +
            "</head>\n" +
            "<body>\n" +
            "<drv id=\"0\">\n" +
            "  <lsh>2210802443940</lsh>\n" +
            "  <sfzmhm>37040220010202608X</sfzmhm>\n" +
            "  <dabh></dabh>\n" +
            "  <xm>%E8%B/</xm>\n" +
            "  <ywlx>A</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <kssj>2021-08-02+09%3A51%3A37.0</kssj>\n" +
            "  <jssj></jssj>\n" +
            "  <ywgw>ABCGE</ywgw>\n" +
            "  <kskm>010101</kskm>\n" +
            "  <xygw>C</xygw>\n" +
            "  <glbm>370402000400</glbm>\n" +
            "  <ffbz>0</ffbz>\n" +
            "  <rkbz>0</rkbz>\n" +
            "  <hdbz>A</hdbz>\n" +
            "  <xgzl></xgzl>\n" +
            "  <zjcx>C1</zjcx>\n" +
            "  <ywzt>A</ywzt>\n" +
            "  <ywblbm>370402000400</ywblbm>\n" +
            "  <fzjg>%E9%B2%81D</fzjg>\n" +
            "</drv>\n" +
            "</body>\n" +
            "</root>";


    String sfzhmxml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
            "<root>\n" +
            "<head>\n" +
            "<code>1</code>\n" +
            "<message>%E6%95%B0%E6%8D%AE%E4%B8%8B%E8%BD%BD%E6%88%90%E5%8A%9F%EF%BC%81</message>\n" +
            "<rownum>4</rownum>\n" +
            "</head>\n" +
            "<body>\n" +
            "<drv id=\"0\">\n" +
            "  <lsh>2D00613110691</lsh>\n" +
            "  <sfzmhm>370481198810018537</sfzmhm>\n" +
            "  <dabh>370400918919</dabh>\n" +
            "  <xm>%E5%BC%</xm>\n" +
            "  <ywlx>F</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <kssj>2010-06-13+09%3A22%3A24.0</kssj>\n" +
            "  <jssj>2010-06-13+11%3A29%3A32.0</jssj>\n" +
            "  <ywgw>ABCE</ywgw>\n" +
            "  <kskm>010000</kskm>\n" +
            "  <xygw>A</xygw>\n" +
            "  <glbm>370400000400</glbm>\n" +
            "  <ffbz></ffbz>\n" +
            "  <rkbz></rkbz>\n" +
            "  <hdbz></hdbz>\n" +
            "  <xgzl>I</xgzl>\n" +
            "  <zjcx>C1</zjcx>\n" +
            "  <ywzt>A</ywzt>\n" +
            "  <ywblbm>370400000400</ywblbm>\n" +
            "  <fzjg>%E9%B2%81D</fzjg>\n" +
            "</drv>\n" +
            "<drv id=\"1\">\n" +
            "  <lsh>2190304915227</lsh>\n" +
            "  <sfzmhm>370481198810018537</sfzmhm>\n" +
            "  <dabh>370400918919</dabh>\n" +
            "  <xm>%E5%BC%</xm>\n" +
            "  <ywlx>C</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <kssj>2019-03-04+15%3A10%3A06.0</kssj>\n" +
            "  <jssj>2019-03-04+15%3A38%3A33.0</jssj>\n" +
            "  <ywgw>AGE</ywgw>\n" +
            "  <kskm>000000</kskm>\n" +
            "  <xygw>O</xygw>\n" +
            "  <glbm>370400000400</glbm>\n" +
            "  <ffbz>3</ffbz>\n" +
            "  <rkbz></rkbz>\n" +
            "  <hdbz></hdbz>\n" +
            "  <xgzl>ABCM</xgzl>\n" +
            "  <zjcx>C1</zjcx>\n" +
            "  <ywzt>A</ywzt>\n" +
            "  <ywblbm>370481000400</ywblbm>\n" +
            "  <fzjg>%E9%B2%81D</fzjg>\n" +
            "</drv>\n" +
            "<drv id=\"2\">\n" +
            "  <lsh>2160324731855</lsh>\n" +
            "  <sfzmhm>370481198810018537</sfzmhm>\n" +
            "  <dabh>370400918919</dabh>\n" +
            "  <xm>%E5%BC</xm>\n" +
            "  <ywlx>C</ywlx>\n" +
            "  <ywyy>EF</ywyy>\n" +
            "  <kssj>2016-03-24+16%3A42%3A22.0</kssj>\n" +
            "  <jssj>2016-03-24+17%3A02%3A35.0</jssj>\n" +
            "  <ywgw>AGE</ywgw>\n" +
            "  <kskm>000000</kskm>\n" +
            "  <xygw>O</xygw>\n" +
            "  <glbm>370400000400</glbm>\n" +
            "  <ffbz>3</ffbz>\n" +
            "  <rkbz></rkbz>\n" +
            "  <hdbz></hdbz>\n" +
            "  <xgzl>ABZ</xgzl>\n" +
            "  <zjcx>C1</zjcx>\n" +
            "  <ywzt>A</ywzt>\n" +
            "  <ywblbm>370481000400</ywblbm>\n" +
            "  <fzjg>%E9%B2%81D</fzjg>\n" +
            "</drv>\n" +
            "<drv id=\"3\">\n" +
            "  <lsh>2130319517247</lsh>\n" +
            "  <sfzmhm>370481198810018537</sfzmhm>\n" +
            "  <dabh>370400918919</dabh>\n" +
            "  <xm>%E5%B</xm>\n" +
            "  <ywlx>C</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <kssj>2013-03-19+16%3A08%3A10.0</kssj>\n" +
            "  <jssj>2013-03-19+16%3A56%3A20.0</jssj>\n" +
            "  <ywgw>AGE</ywgw>\n" +
            "  <kskm>000000</kskm>\n" +
            "  <xygw>O</xygw>\n" +
            "  <glbm>370400000400</glbm>\n" +
            "  <ffbz>3</ffbz>\n" +
            "  <rkbz></rkbz>\n" +
            "  <hdbz></hdbz>\n" +
            "  <xgzl>BC</xgzl>\n" +
            "  <zjcx>C1</zjcx>\n" +
            "  <ywzt>A</ywzt>\n" +
            "  <ywblbm>370481000400</ywblbm>\n" +
            "  <fzjg>%E9%B2%81D</fzjg>\n" +
            "</drv>\n" +
            "</body>\n" +
            "</root>";

    /**
     * ???????????????
     *
     * @param invoiceReqDTO
     * @return
     */
    @Override
    public DataReuslt onlinePayment(InvoiceReqDTO invoiceReqDTO) {
        DataReuslt dataReuslt = getDriversOnlinePay(invoiceReqDTO);
        if ("000000".equals(dataReuslt.getCode())) {
            List<FeeDrvjkMeterialRespDTO> feeDrvjkRespDTOList = (List<FeeDrvjkMeterialRespDTO>) dataReuslt.getData();
            if (feeDrvjkRespDTOList.size() != 0) {
                feeDrvjkRespDTOList.forEach(feeDrvjkMeterialRespDTO -> {
                    commonTemp(feeDrvjkMeterialRespDTO);
                });
            }
        } else {
            return dataReuslt;
        }
        return DataReuslt.success("????????????");
    }

    /**
     * ????????????????????????????????????
     *
     * @param invoiceReqDTO
     * @return
     */
    @Override
    public DataReuslt getDriversOnlinePay(InvoiceReqDTO invoiceReqDTO) {
        //???????????????????????????????????????????????????????????????
        FeeGetDataSetRespDTO feeGetDataSetRespDTO = feeGetDataSetMapper.getOneByModelId(DPayModelId);
        if (feeGetDataSetRespDTO != null) {
            String dataFrom = feeGetDataSetRespDTO.getDatafrom();
            if ("1".equals(dataFrom)) { // ????????????webservice??????
                Map<String, String> xmlMap = null;
                List<Map<String, String>> list = new ArrayList<>();
                List<FeeDrvjkMeterialRespDTO> respList = new ArrayList<>();
                // ?????????????????????????????????01c25?????? ???????????? glbm,hpzl,ywlxx,ywyy
                // ?????????
                if (invoiceReqDTO.getLsh() != null || (invoiceReqDTO.getLsh() != null && invoiceReqDTO.getSfzmhm() != null)) {
                    xmlMap = getLshXmlMap(invoiceReqDTO.getLsh());
                    String code = xmlMap.get("head.code");
                    if ("1".equals(code)) {
                        String num = xmlMap.get("head.rownum");
                        if (Integer.parseInt(num) != 0) {
                            String ywlx = xmlMap.get("body.drv.ywlx");
                            String ywyy = xmlMap.get("body.drv.ywyy");
                            String zjcx = xmlMap.get("body.drv.zjcx");
                            String lsh = xmlMap.get("body.drv.lsh");
                            String ywblbm = xmlMap.get("body.drv.ywblbm");
                            String xm = xmlMap.get("body.drv.xm");
                            // ????????????????????????
                            DataReuslt dataReuslt = invoiceService.isPaymentNot(invoiceReqDTO.getLsh());
                            String reusltCode = dataReuslt.getCode();
                            if (!"000000".equals(reusltCode)) {
                                if ("01".equals(reusltCode)) {
                                    return DataReuslt.fail(dataReuslt.getMessage());
                                }
                                FeePayparaReqDTO feePayparaReqDTO = new FeePayparaReqDTO();
                                feePayparaReqDTO.setGlbm(ywblbm);
                                FeePayparaRespDTO dto = feePayparaMapper.queryFeePaypara(feePayparaReqDTO);
                                if (dto != null) {
                                    FeeDrvjkReqDTO feeDrvjkReqDTO = new FeeDrvjkReqDTO();
                                    feeDrvjkReqDTO.setZjcx(zjcx);
                                    feeDrvjkReqDTO.setYwlx(ywlx);
                                    feeDrvjkReqDTO.setYwyy(ywyy);
                                    respList = feeDrvjkMapper.queryDriverLicensePaymentInfo(feeDrvjkReqDTO);
                                    respList.forEach(feeDrvjkMeterialRespDTO -> {
                                        feeDrvjkMeterialRespDTO.setXm(xm);
                                        feeDrvjkMeterialRespDTO.setLsh(lsh);
                                        feeDrvjkMeterialRespDTO.setYwblbm(ywblbm);
                                        feeDrvjkMeterialRespDTO.setFeePayparaRespDTO(dto);
                                    });
                                    if (respList.size() != 0) {
                                        return DataReuslt.success(respList);
                                    } else {
                                        return DataReuslt.fail("???????????????????????????");
                                    }
                                }
                            } else {
                                return DataReuslt.success("?????????");
                            }
                        } else {
                            return DataReuslt.fail("????????????????????????");
                        }
                    }
                } else {
                    // ????????????
                    list = getSfzhmXmlMap(invoiceReqDTO.getSfzmhm());
                    String body = list.get(0).get("body");
                    if ("body.".equals(body)) {
                        list.remove(0);
                        List<FeeDrvjkMeterialRespDTO> feeDrvjkRespDTOList = new ArrayList<>();
                        for (Map<String, String> stringStringMap : list) {
                            String xygw = stringStringMap.get("xygw");
                            if (!"O".equals(xygw) && !"Q".equals(xygw)) {
                                String ywlx = stringStringMap.get("ywlx");
                                String ywyy = stringStringMap.get("ywyy");
                                String zjcx = stringStringMap.get("zjcx");
                                String lsh = stringStringMap.get("lsh");
                                DataReuslt dataReuslt = invoiceService.isPaymentNot(lsh);
                                String reusltCode = dataReuslt.getCode();
                                if (!"000000".equals(reusltCode)) {
                                    if ("01".equals(reusltCode)) {
                                        return DataReuslt.fail(dataReuslt.getMessage());
                                    }
                                    String ywblbm = stringStringMap.get("ywblbm");
                                    String xm = stringStringMap.get("xm");
                                    FeePayparaReqDTO feePayparaReqDTO = new FeePayparaReqDTO();
                                    feePayparaReqDTO.setGlbm(ywblbm);
                                    FeePayparaRespDTO dto = feePayparaMapper.queryFeePaypara(feePayparaReqDTO);
                                    if (dto != null) {
                                        FeeDrvjkReqDTO feeDrvjkReqDTO = new FeeDrvjkReqDTO();
                                        feeDrvjkReqDTO.setZjcx(zjcx);
                                        feeDrvjkReqDTO.setYwlx(ywlx);
                                        feeDrvjkReqDTO.setYwyy(ywyy);
                                        respList = feeDrvjkMapper.queryDriverLicensePaymentInfo(feeDrvjkReqDTO);
                                        respList.forEach(feeDrvjkMeterialRespDTO -> {
                                            feeDrvjkMeterialRespDTO.setYwblbm(ywblbm);
                                            feeDrvjkMeterialRespDTO.setLsh(lsh);
                                            feeDrvjkMeterialRespDTO.setXm(xm);
                                            feeDrvjkMeterialRespDTO.setFeePayparaRespDTO(dto);
                                        });
                                    }
                                } else {
                                    return DataReuslt.success("?????????");
                                }
                            }
                        }
                        if (respList.size() == 0) {
                            return DataReuslt.fail("???????????????????????????");
                        } else {
                            return DataReuslt.success(respList);
                        }

                    } else {
                        return DataReuslt.fail("??????????????????");
                    }
                }
            }
        } else {
            return DataReuslt.fail("??????id?????????");
        }
        return DataReuslt.success("????????????");
    }

    /**
     * ????????????
     *
     * @param feeDrvjkMeterialRespDTO
     */
    public void commonTemp(FeeDrvjkMeterialRespDTO feeDrvjkMeterialRespDTO) {
        // ?????????
        BigDecimal totalSum = feeDrvjkMeterialRespDTO.getUnitPrice().multiply(
                new BigDecimal(feeDrvjkMeterialRespDTO.getQuantity()));
        // ?????????
        BigDecimal profitPrice = totalSum.subtract(feeDrvjkMeterialRespDTO.getCost());
        String orderId = vehicleOnlinePayMapper.getGenerateOrder(feeDrvjkMeterialRespDTO.getLsh());
        CommonFieldReqDTO commonFieldReqDTO = new CommonFieldReqDTO();
        commonFieldReqDTO.setOrderid(orderId);
        commonFieldReqDTO.setTotalSum(totalSum);
        commonFieldReqDTO.setProfitPrice(profitPrice);
        commonFieldReqDTO.setProjectId(feeDrvjkMeterialRespDTO.getProjectId());
        commonFieldReqDTO.setProjectName(feeDrvjkMeterialRespDTO.getProjectName());
        commonFieldReqDTO.setUnitPrice(feeDrvjkMeterialRespDTO.getUnitPrice());
        commonFieldReqDTO.setQuantity(feeDrvjkMeterialRespDTO.getQuantity());
        commonFieldReqDTO.setClassId(feeDrvjkMeterialRespDTO.getClassId());
        commonFieldReqDTO.setProvinceRate(feeDrvjkMeterialRespDTO.getProvinceRate());
        commonFieldReqDTO.setCityRate(feeDrvjkMeterialRespDTO.getCityRate());
        commonFieldReqDTO.setMaterialCost(feeDrvjkMeterialRespDTO.getCost());
        commonFieldReqDTO.setFinanceCode(feeDrvjkMeterialRespDTO.getFinanceCode());
        commonFieldReqDTO.setZjcx(feeDrvjkMeterialRespDTO.getZjcx());
        commonFieldReqDTO.setYwlx(feeDrvjkMeterialRespDTO.getYwlx());
        commonFieldReqDTO.setYwyy(feeDrvjkMeterialRespDTO.getYwyy());
        commonFieldReqDTO.setXm(feeDrvjkMeterialRespDTO.getXm());
        commonFieldReqDTO.setLsh(feeDrvjkMeterialRespDTO.getLsh());
        commonFieldReqDTO.setAccountId(feeDrvjkMeterialRespDTO.getFeePayparaRespDTO().getAccountid());
        commonFieldReqDTO.setGlbm(feeDrvjkMeterialRespDTO.getYwblbm());
        commonFieldReqDTO.setYwlb("2");
        commonFieldReqDTO.setCxhp(feeDrvjkMeterialRespDTO.getZjcx());
        commonFieldReqDTO.setHphm(feeDrvjkMeterialRespDTO.getXm());
        temporaryService.temporaryAccount(commonFieldReqDTO, feeDrvjkMeterialRespDTO.getFeePayparaRespDTO());
        //?????? ??????????????????

    }


    /**
     * ??????xml???????????????
     *
     * @return
     */
    public Map<String, String> getLshXmlMap(String lsh) {
        try {
            VechicleDTO vechicleDTO = new VechicleDTO();
            vechicleDTO.setLsh(lsh);
            String str = XmlFileUtils.selectXmlDoc(vechicleDTO);
            String queryXmlDoc = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), str, XmlFileUtils.getXmlFileFoot());
            // ?????????????????????????????????
//            String result = webServiceUtil.queryCallWeb("01", "02C15", queryXmlDoc);
            Map<String, String> map = new HashMap<String, String>();
            Map<String, String> xmlMap = XmlUtils.parseXml2Map(xml, map);
            return xmlMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ??????xml???????????????
     *
     * @return
     */
    public List<Map<String, String>> getSfzhmXmlMap(String sfzhm) {
        DriversDTO driversDTO = new DriversDTO();
        driversDTO.setSfzhm(sfzhm);
        try {
            String str = XmlFileUtils.selectXmlDoc(driversDTO);
            String queryXmlDoc = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), str, XmlFileUtils.getXmlFileFoot());
            // ?????????????????????????????????
//            String result = webServiceUtil.queryCallWeb("01", "02C15", queryXmlDoc);
            List<Map<String, String>> list = new ArrayList<>();
            List<Map<String, String>> list1 = XmlUtils.parseXml2List(sfzhmxml, list);
            List<Map<String, String>> mapList = XmlUtils.removeRepeatMapByKey(list1, "lsh");
            return mapList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
