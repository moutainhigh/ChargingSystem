package com.egintra.feeService.business.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.VehiclePayReqDTO;
import com.egintra.common.dto.business.BusinessPaymentReqDTO;
import com.egintra.common.dto.business.DUnlockReqDTO;
import com.egintra.common.dto.business.ResultReqDTO;
import com.egintra.common.dto.business.SysDateReqDTO;
import com.egintra.common.dto.business.UploadinfoReqDTO;
import com.egintra.common.dto.business.VUnlockReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.dto.pay.DriversDTO;
import com.egintra.common.dto.pay.VechicleDTO;
import com.egintra.common.dto.sys.FeeGetDataSetRespDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.repository.mapper.BusinessMapper;
import com.egintra.common.repository.mapper.FeeDrvjkMapper;
import com.egintra.common.repository.mapper.FeeGetDataSetMapper;
import com.egintra.common.repository.mapper.FeeInvoiceDetailMapper;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.repository.mapper.UploadinfoMapper;
import com.egintra.common.repository.mapper.VehicleOnlinePayMapper;
import com.egintra.common.repository.mapper.VehiclePayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.XmlFileUtils;
import com.egintra.common.utils.XmlUtils;
import com.egintra.feeService.business.service.BusinessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessMapper businessMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private FeeInvoiceDetailMapper detailMapper;
    @Resource
    private VehiclePayMapper vehiclePayMapper;
    @Resource
    private FeeGetDataSetMapper feeGetDataSetMapper;
    @Resource
    private FeeDrvjkMapper feeDrvjkMapper;
    @Resource
    private VehicleOnlinePayMapper vehicleOnlinePayMapper;
    @Resource
    private UploadinfoMapper uploadinfoMapper;
    @Resource
    private LoginMapper loginMapper;

    /**
     * ???????????? ???????????????
     */
    @Value("${Offline.vehvalidate}")
    private String vehvalidate;
    /**
     * ???????????? ???????????????
     */
    @Value("${Offline.drvCheckFee}")
    private String drvCheckFee;

    /**
     * ????????????????????????
     *
     * @param businessPayment ??????
     * @return ??????
     */
    @Override
    public DataReuslt getBusinessList(BusinessPaymentReqDTO businessPayment) {
        String params = businessPayment.getParams();
        //???????????????????????????
        SysDateReqDTO sysDate = businessMapper.getSysDate();
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        // ?????????????????????????????????
        String accoutId = rightUser.getAccountId();
        AccountReqDTO accountReqDTO = accountMapper.getOne(accoutId);
        if(accountReqDTO==null){
            return DataReuslt.fail("?????????????????????");
        }
        Date workDate = accountReqDTO.getWorkDate();
        String strDay = sysDate.getStrDay().replace(".", "-");
        Date nowDate = DateUtils.parseDates(strDay);
        //?????????????????????????????? ??????????????????
        if (nowDate.getTime() > workDate.getTime()) {
            AccountReqDTO account = new AccountReqDTO();
            account.setWorkDate(nowDate);
            account.setAccountId(accoutId);
            accountMapper.updateAccountById(account);
        }
        if (StringUtils.isNotBlank(params)) {
            //???????????????????????????????????????
            //?????????
            if (params.length() == 13) {
                //?????????????????????
                char one = params.charAt(0);
                // ?????????
                if ("1".equals(String.valueOf(one))) {
                    FeeGetDataSetRespDTO feeGetDataSetRespDTO = feeGetDataSetMapper.getOneByModelId(vehvalidate);
                    if (feeGetDataSetRespDTO == null) {
                        return DataReuslt.fail("??????id?????????");
                    }
                    String dataFrom = feeGetDataSetRespDTO.getDatafrom();
                    // ????????????webservice??????
                    if ("1".equals(dataFrom)) {
                        // ?????????????????????????????????01c25?????? ???????????? glbm,hpzl,ywlxx,ywyy
                        return requesWebServiceXml(params, "1", rightUser);
                    }
                    //?????????
                } else if ("2".equals(String.valueOf(one))) {
                    FeeGetDataSetRespDTO feeGetDataSetRespDTO = feeGetDataSetMapper.getOneByModelId(vehvalidate);
                    if (feeGetDataSetRespDTO == null) {
                        return DataReuslt.fail("??????id?????????");
                    }
                    return requesWebServiceXml(params, "2", rightUser);
                }
                //????????????
            } else {
                FeeGetDataSetRespDTO feeGetDataSetRespDTO = feeGetDataSetMapper.getOneByModelId(drvCheckFee);
                if (feeGetDataSetRespDTO == null) {
                    return DataReuslt.fail("??????id?????????");
                }
                return requestServiceSfzhmXml(params, rightUser);
            }
        } else {
            return DataReuslt.fail("??????????????????");
        }

        return DataReuslt.success();
    }

    /**
     * ?????????????????????
     *
     * @param params ??????
     * @return ??????
     */
    public DataReuslt requestServiceSfzhmXml(String params, RightUser rightUser) {
        List<FeeDrvjkMeterialRespDTO> respList = new ArrayList<>();
        List<Map<String, String>> list = getSfzhmXmlMap(params);
        String body = list.get(0).get("body");
        if ("body.".equals(body)) {
            list.remove(0);
            // ?????????????????? ????????????????????? ???????????????
            DataReuslt dataReuslt = getSfzhm(params, list);
            if (!"000000".equals(dataReuslt.getCode())) {
                return dataReuslt.fail("????????????????????????");
            }
            for (Map<String, String> stringStringMap : list) {
                String xygw = stringStringMap.get("xygw");
                if (!"O".equals(xygw) && !"Q".equals(xygw)) {
                    String ywlx = stringStringMap.get("ywlx");
                    String ywyy = stringStringMap.get("ywyy");
                    String zjcx = stringStringMap.get("zjcx");
                    String lsh = stringStringMap.get("lsh");
                    String sfzmhm = stringStringMap.get("sfzmhm");
                    String xm = stringStringMap.get("xm");
                    // ??????????????????
                    InvoiceReqDTO invoiceTmpDTO = invoiceMapper.isPaymentNot(lsh);
                    if (invoiceTmpDTO != null) {
                        if (!"1".equals(invoiceTmpDTO.getStatus())) {
                            respList = getFeeDrvjkMeterialRespDTOList(zjcx, ywlx, ywyy, lsh, respList, xm, sfzmhm);
                        } else {
                            return DataReuslt.success("?????????");
                        }
                    } else {
                        respList = getFeeDrvjkMeterialRespDTOList(zjcx, ywlx, ywyy, lsh, respList, xm, sfzmhm);
                    }

                }
            }
            Map map = new HashMap();
            map.put("list", respList);
            return DataReuslt.success(map);
        } else {
            return DataReuslt.fail("???????????????????????????");
        }
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param zjcx     ????????????
     * @param ywlx     ????????????
     * @param ywyy     ????????????
     * @param lsh      ?????????
     * @param respList ????????????
     * @return ??????
     */
    public List<FeeDrvjkMeterialRespDTO> getFeeDrvjkMeterialRespDTOList(String zjcx, String ywlx, String ywyy,
                                                                        String lsh,
                                                                        List<FeeDrvjkMeterialRespDTO> respList,
                                                                        String xm, String sfzmhm) {
        FeeDrvjkReqDTO feeDrvjkReqDTO = new FeeDrvjkReqDTO();
        feeDrvjkReqDTO.setZjcx(zjcx);
        feeDrvjkReqDTO.setYwlx(ywlx);
        feeDrvjkReqDTO.setYwyy(ywyy);
        respList = feeDrvjkMapper.queryDriverLicensePaymentInfo(feeDrvjkReqDTO);
        respList.forEach(feeDrvjkMeterialRespDTO -> {
            feeDrvjkMeterialRespDTO.setLsh(lsh);
            feeDrvjkMeterialRespDTO.setXm(xm);
            feeDrvjkMeterialRespDTO.setSfzhm(sfzmhm);
        });

        return respList;
    }

    /**
     * ???????????????
     *
     * @param params ??????
     * @param type   1 ????????? 2 ?????????
     * @param user   ????????????
     * @return ??????
     */
    public DataReuslt requesWebServiceXml(String params, String type, RightUser user) {
        // ?????????????????????????????????01c25?????? ???????????? glbm,hpzl,ywlxx,ywyy
        Map<String, String> xmlMap = getXmlMap(params, type);
        // ???????????? ?????????????????????????????????
        if ("1".equals(type)) {
            if (!params.equals(xmlMap.get("body.veh.lsh"))) {
                return DataReuslt.fail("??????????????????");
            }
        } else {
            if (!params.equals(xmlMap.get("body.drv.lsh"))) {
                return DataReuslt.fail("??????????????????");
            }
        }
        String code = xmlMap.get("head.code");
        if ("1".equals(code)) {
            String num = xmlMap.get("head.rownum");
            if (Integer.parseInt(num) != 0) {
                InvoiceReqDTO invoiceReqDTO = invoiceMapper.isPaymentNot(params);
                if (invoiceReqDTO != null) {
                    // ????????????????????????
                    String status = invoiceReqDTO.getStatus();
                    if ("0".equals(status)) {
                        return getRestList(type, xmlMap);
                    } else {
                        return DataReuslt.success("?????????");
                    }
                } else {
                    return getRestList(type, xmlMap);
                }
            } else {
                return DataReuslt.fail("????????????????????????");
            }
        }

        return DataReuslt.success();
    }

    /**
     * ???????????????????????????
     *
     * @param type   ??????
     * @param xmlMap xml????????????
     * @return ??????
     */
    public DataReuslt getRestList(String type, Map<String, String> xmlMap) {
        Map map = new HashMap();
        if ("1".equals(type)) {
            String ywlx = xmlMap.get("body.veh.ywlx");
            String ywyy = xmlMap.get("body.veh.ywyy");
            String hpzl = xmlMap.get("body.veh.hpzl");
            String hphm = xmlMap.get("body.veh.hphm");
            String lsh = xmlMap.get("body.veh.lsh");
            VehiclePayReqDTO vehiclePayReqDTO = new VehiclePayReqDTO();
            vehiclePayReqDTO.setYwlx(ywlx);
            vehiclePayReqDTO.setYwyy(ywyy);
            vehiclePayReqDTO.setHpzl(hpzl);
            // ?????????
            List<VehiclePayReqDTO> vehiclePayList = vehiclePayMapper.getVehiclePayList(vehiclePayReqDTO);
            vehiclePayList.forEach(vehiclePayReqDTO1 -> {
                vehiclePayReqDTO1.setLsh(lsh);
                vehiclePayReqDTO1.setHphm(hphm);
                vehiclePayReqDTO1.setPrice("");
            });
            map.put("list", vehiclePayList);
        } else {
            String ywlx = xmlMap.get("body.drv.ywlx");
            String ywyy = xmlMap.get("body.drv.ywyy");
            String zjcx = xmlMap.get("body.drv.zjcx");
            String lsh = xmlMap.get("body.drv.lsh");
            String xm = xmlMap.get("body.drv.xm");
            String sfzmhm = xmlMap.get("body.drv.sfzmhm");
            FeeDrvjkReqDTO feeDrvjkReqDTO = new FeeDrvjkReqDTO();
            feeDrvjkReqDTO.setZjcx(zjcx);
            feeDrvjkReqDTO.setYwlx(ywlx);
            feeDrvjkReqDTO.setYwyy(ywyy);
            List<FeeDrvjkMeterialRespDTO> respDTOS = feeDrvjkMapper.queryDriverLicensePaymentInfo(feeDrvjkReqDTO);
            respDTOS.forEach(feeDrvjkMeterialRespDTO -> {
                feeDrvjkMeterialRespDTO.setLsh(lsh);
                feeDrvjkMeterialRespDTO.setXm(xm);
                feeDrvjkMeterialRespDTO.setSfzhm(sfzmhm);
                feeDrvjkMeterialRespDTO.setPrice("");
            });
            map.put("list", respDTOS);
        }
        return DataReuslt.success(map);
    }

    /**
     * ????????????  ????????????????????????
     *
     * @param params ??????????????????
     * @param list   ?????????
     * @return ??????
     */
    public DataReuslt getSfzhm(String params, List<Map<String, String>> list) {
        Map<String, String> map = list.get(1);
        String sfzmhm = map.get("sfzmhm");
        if (!params.equals(sfzmhm)) {
            return DataReuslt.fail("???????????????????????????");
        } else {
            return DataReuslt.success();
        }
    }

    /**
     * ??????????????????
     */
    String dxml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
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
            "  <xm>??????</xm>\n" +
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

    /**
     * ??????????????????
     */
    String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
            "<root>\n" +
            "<head>\n" +
            "<code>1</code>\n" +
            "<message>%E6%95%B0%E6%8D%AE%E4%B8%8B%E8%BD%BD%E6%88%90%E5%8A%9F%EF%BC%81</message>\n" +
            "<rownum>1</rownum>\n" +
            "</head>\n" +
            "<body>\n" +
            "<veh id=\"0\">\n" +
            "  <lsh>1210806019988</lsh>\n" +
            "  <xh>37040007001292</xh>\n" +
            "  <ywlx>K</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <syr>%E6%9D%8E%E7%BA%A2%E4%BA%91</syr>\n" +
            "  <hpzl>02</hpzl>\n" +
            "  <hphm>DS0069</hphm>\n" +
            "  <clpp1>%E9%A3%9E%E5%BA%A6%E7%89%8C</clpp1>\n" +
            "  <clxh>HG7132A%28i-DSI+CVT%29</clxh>\n" +
            "  <cllx>K33</cllx>\n" +
            "  <xzqh>370403</xzqh>\n" +
            "  <sqrq>2021-08-06+10%3A44%3A13.0</sqrq>\n" +
            "  <bjrq>2021-08-06+10%3A55%3A37.0</bjrq>\n" +
            "  <xygw>E</xygw>\n" +
            "  <ywlc>25E</ywlc>\n" +
            "  <lszt>E</lszt>\n" +
            "  <glbm>370402000400</glbm>\n" +
            "  <fpbj>0</fpbj>\n" +
            "  <ffbj>0</ffbj>\n" +
            "  <rkbj>0</rkbj>\n" +
            "  <clsbdh>LHGGD182772011600</clsbdh>\n" +
            "</veh>\n" +
            "</body>\n" +
            "</root>";

    /**
     * ???????????????
     */
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
            "  <xm>??????</xm>\n" +
            "  <ywlx>F</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <kssj>2010-06-13+09%3A22%3A24.0</kssj>\n" +
            "  <jssj>2010-06-13+11%3A29%3A32.0</jssj>\n" +
            "  <ywgw>ABCE</ywgw>\n" +
            "  <kskm>010000</kskm>\n" +
            "  <xygw>O</xygw>\n" +
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
            "  <xm>??????</xm>\n" +
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
            "  <xm>??????</xm>\n" +
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
            "  <xm>??????</xm>\n" +
            "  <ywlx>C</ywlx>\n" +
            "  <ywyy>A</ywyy>\n" +
            "  <kssj>2013-03-19+16%3A08%3A10.0</kssj>\n" +
            "  <jssj>2013-03-19+16%3A56%3A20.0</jssj>\n" +
            "  <ywgw>AGE</ywgw>\n" +
            "  <kskm>000000</kskm>\n" +
            "  <xygw>A</xygw>\n" +
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
            "</root>\n";

    /**
     * ??????xml???????????????
     *
     * @param lsh  ?????????
     * @param type ??????
     * @return ??????
     */
    public Map<String, String> getXmlMap(String lsh, String type) {
        try {
            VechicleDTO vechicleDTO = new VechicleDTO();
            vechicleDTO.setLsh(lsh);
            String str = XmlFileUtils.selectXmlDoc(vechicleDTO);
            String queryXmlDoc = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), str, XmlFileUtils.getXmlFileFoot());
            // ?????????????????????????????????
//            String result = webServiceUtil.queryCallWeb("01", "02C16", queryXmlDoc);
            Map<String, String> map = new HashMap<>();
            Map<String, String> xmlMap;
            if ("1".equals(type)) { // ?????????
                xmlMap = XmlUtils.parseXml2Map(xml, map);
            } else { //?????????
                xmlMap = XmlUtils.parseXml2Map(dxml, map);
            }

            return xmlMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * ??????xml???????????????
     *
     * @param sfzhm ??????????????????
     * @return ??????
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

    /**
     * ??????
     *
     * @param body ??????
     * @return ??????
     */
    @Override
    public DataReuslt payBusiness(Map<String, Object> body) {
        String params = body.get("params").toString();
        String payType = body.get("payType").toString();
        String payUnit = body.get("payUnit").toString();
        String totalMoney = body.get("totalMoney").toString();
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        List<Map<String, Object>> drvs = (List<Map<String, Object>>) body.get("drvs");
        BusinessPaymentReqDTO businessPayment = new BusinessPaymentReqDTO();
        businessPayment.setParams(params);
        businessPayment.setPayType(payType);
        businessPayment.setPayUnit(payUnit);
        businessPayment.setDrvs(drvs);
        businessPayment.setTotalMoney(new BigDecimal(totalMoney));
        // ????????????
        if ("1".equals(payType)) {
            //?????????
            if (params.length() == 13) {
                //?????????????????????
                char one = params.charAt(0);
                // ?????????
                if ("1".equals(String.valueOf(one))) {
                    // ???????????????
                    addInvoice(businessPayment, "1", rightUser);
                    //?????????
                } else if ("2".equals(String.valueOf(one))) {
                    // ???????????????
                    addInvoice(businessPayment, "2", rightUser);
                }
            } else { //???????????????
                // ???????????????
                addInvoice(businessPayment, "3", rightUser);
            }
            return DataReuslt.success("????????????");
            // pos??????
        } else {
            return DataReuslt.fail(ResultCode.PAY_NOT_POS.code(),ResultCode.PAY_NOT_POS.message());
        }
    }

    /**
     * ???????????????
     *
     * @param business ????????????
     * @param type     ??????
     * @param user     ??????
     */
    public void addInvoice(BusinessPaymentReqDTO business, String type, RightUser user) {
        DataReuslt dataReuslt;
        // ??????????????????????????????
        if (!"3".equals(type)) {
            dataReuslt = requesWebServiceXml(business.getParams(), type, user);
        } else {
            // ????????????
            dataReuslt = requestServiceSfzhmXml(business.getParams(), user);
        }
        Map map = (Map) dataReuslt.getData();
        List<FeeInvoice> invoiceReqDTOS = new ArrayList<>();
        List<FeeInvoiceDetail> details = new ArrayList<>();
        AccountReqDTO accountReqDTO = accountMapper.getOne(user.getAccountId());
        List<FeeDrvjkMeterialRespDTO> respDTOS;
        VUnlockReqDTO unlock = new VUnlockReqDTO();
        if ("1".equals(type)) {
            List<VehiclePayReqDTO> vehiclePayReqDTOS = (List<VehiclePayReqDTO>) map.get("list");
            VehiclePayReqDTO vehicle = vehiclePayReqDTOS.get(0);
            unlock.setHpzl(vehicle.getHpzl());
            unlock.setHphm(vehicle.getHphm());
            unlock.setLsh(vehicle.getLsh());
            FeeInvoice feeInvoice = getInvoice(accountReqDTO, business, user, vehicle.getHphm(),
                    vehicle.getMaterialCost());
            feeInvoice.setLsh(vehicle.getLsh());
            feeInvoice.setCxhp(vehicle.getHpzl());
            feeInvoice.setYwlx(vehicle.getYwlx());
            feeInvoice.setYwyy(vehicle.getYwyy());
            feeInvoice.setYwlb("1");
            invoiceReqDTOS.add(feeInvoice);
            vehiclePayReqDTOS.forEach(vehiclePayReqDTO -> {
                FeeInvoiceDetail feeInvoiceDetail = getFeeInvoiceDetail(accountReqDTO, feeInvoice.getId());
                feeInvoiceDetail.setProjectId(vehiclePayReqDTO.getProjectId());
                feeInvoiceDetail.setProjectName(vehiclePayReqDTO.getProjectName());
                feeInvoiceDetail.setQuantity(vehiclePayReqDTO.getQuantity());
                feeInvoiceDetail.setClassId(vehiclePayReqDTO.getClassId());
                feeInvoiceDetail.setProvinceRate(vehiclePayReqDTO.getProvinceRate());
                feeInvoiceDetail.setUnitPrice(vehiclePayReqDTO.getUnitPrice());
                feeInvoiceDetail.setCityRate(vehiclePayReqDTO.getCityRate());
                feeInvoiceDetail.setMaterialCost(vehiclePayReqDTO.getMaterialCost());
                feeInvoiceDetail.setFinanceCode(vehiclePayReqDTO.getFinanceCode());
                feeInvoiceDetail.setPayWay("01");
                details.add(feeInvoiceDetail);
            });

        } else {
            respDTOS = (List<FeeDrvjkMeterialRespDTO>) map.get("list");
            FeeDrvjkMeterialRespDTO dto = respDTOS.get(0);
            FeeInvoice feeInvoice = getInvoice(accountReqDTO, business, user, dto.getXm(), dto.getCost());
            business.setParams(dto.getLsh());
            feeInvoice.setLsh(dto.getLsh());
            feeInvoice.setSfzmhm(dto.getSfzhm());
            feeInvoice.setCxhp(dto.getZjcx());
            feeInvoice.setYwlx(dto.getYwlx());
            feeInvoice.setYwyy(dto.getYwyy());
            feeInvoice.setYwlb("2");
            invoiceReqDTOS.add(feeInvoice);
            respDTOS.forEach(feeDrvjkMeterialRespDTO -> {
                FeeInvoiceDetail feeInvoiceDetail = getFeeInvoiceDetail(accountReqDTO, feeInvoice.getId());
                feeInvoiceDetail.setProjectId(feeDrvjkMeterialRespDTO.getProjectId());
                feeInvoiceDetail.setProjectName(feeDrvjkMeterialRespDTO.getProjectName());
                feeInvoiceDetail.setQuantity(feeDrvjkMeterialRespDTO.getQuantity());
                feeInvoiceDetail.setClassId(feeDrvjkMeterialRespDTO.getClassId());
                feeInvoiceDetail.setProvinceRate(feeDrvjkMeterialRespDTO.getProvinceRate());
                feeInvoiceDetail.setUnitPrice(feeDrvjkMeterialRespDTO.getUnitPrice());
                feeInvoiceDetail.setCityRate(feeDrvjkMeterialRespDTO.getCityRate());
                feeInvoiceDetail.setMaterialCost(feeDrvjkMeterialRespDTO.getCost());
                feeInvoiceDetail.setFinanceCode(feeDrvjkMeterialRespDTO.getFinanceCode());
                feeInvoiceDetail.setPayWay("01");
                details.add(feeInvoiceDetail);
            });
        }
        // ??????
        bookkeeping(invoiceReqDTOS, details, accountReqDTO);
        // ??????????????????
        if ("1".equals(type)) {
            // ??????
            unlock.setBz(String.valueOf(business.getTotalMoney()));
            Map<String, String> resultMap = vehicleUnlock(unlock);
            String code = resultMap.get("head.code");
            String msg = resultMap.get("head.message");
            // ?????? ????????????
            if ("0".equals(code)) {
                // ?????? ?????????   ??????????????????
                try {
                    String sfxm = "???????????????";
                    unlockLog(accountReqDTO, business, user, code, msg, type, sfxm);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // ?????? ?????????
            List<Map<String, Object>> list = business.getDrvs();
            for (Map<String, Object> dto : list) {
                Integer quantity = Integer.parseInt(dto.get("quantity").toString());
                for (int i = 0; i < quantity; i++) {
                    DUnlockReqDTO dUnlock = new DUnlockReqDTO();
                    dUnlock.setSfxm(getFeeCodeCk(dto.get("projectId").toString()));
                    dUnlock.setJe(new BigDecimal(dto.get("unitPrice").toString()));
                    dUnlock.setSfr(user.getName());
                    dUnlock.setSfsj(new Date());
                    dUnlock.setBj("1");
                    dUnlock.setLsh(business.getParams());
                    // ????????????
                    Map<String, String> resultMap = driverLicenseUnlock(dUnlock);
                    String code = resultMap.get("head.code");
                    String msg = resultMap.get("head.message");
                    if ("0".equals(code)) {
                        try {
                            // ??????????????????
                            String sfxm = "???????????????";
                            unlockLog(accountReqDTO, business, user, code, msg, type, sfxm);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    /**
     * ????????????????????????
     *
     * @param accountReqDTO ????????????
     * @param business      ????????????
     * @param user          ????????????
     * @param xm            ??????
     * @param cost          ?????????
     * @return ??????
     */
    public FeeInvoice getInvoice(AccountReqDTO accountReqDTO, BusinessPaymentReqDTO business, RightUser user,
                                 String xm, BigDecimal cost) {
        String invoiceId = vehicleOnlinePayMapper.getTmpId();
        FeeInvoice feeInvoice = new FeeInvoice();
        feeInvoice.setId(invoiceId);
        feeInvoice.setDepartment(accountReqDTO.getDepartmentId());
        feeInvoice.setAccountId(accountReqDTO.getAccountId());
        feeInvoice.setInvoiceId(accountReqDTO.getFictitiousId());
        feeInvoice.setPayUnit(xm);
        feeInvoice.setPayWay("01");
        feeInvoice.setPosid("");
        feeInvoice.setPaperId("");
        feeInvoice.setDtimes(DateUtils.getCurrentDate());
        feeInvoice.setReceiveAccount(accountReqDTO.getReceiveAccount()!=null?accountReqDTO.getReceiveAccount():"");
        feeInvoice.setCheckNumber(accountReqDTO.getFictitiousId());
        feeInvoice.setPosInfo("");
        feeInvoice.setTotalMoney(business.getTotalMoney());
        feeInvoice.setTotalPure(business.getTotalMoney().subtract(cost));
        feeInvoice.setReceiver(user.getName());
        feeInvoice.setReceiverId(user.getNo());
        feeInvoice.setDates(accountReqDTO.getWorkDate());
        feeInvoice.setStatus("1");
        feeInvoice.setTypes("1");
        feeInvoice.setSfzmhm("");

        return feeInvoice;
    }

    /**
     * ????????????????????????
     *
     * @param accountReqDTO ????????????
     * @param invoiceId     ?????????ID
     * @return ??????
     */
    public FeeInvoiceDetail getFeeInvoiceDetail(AccountReqDTO accountReqDTO, String invoiceId) {
        String invoiceDetailId = vehicleOnlinePayMapper.getTmpDetailId();
        FeeInvoiceDetail feeInvoiceDetail = new FeeInvoiceDetail();
        feeInvoiceDetail.setId(invoiceDetailId);
        feeInvoiceDetail.setPaperId("");
        feeInvoiceDetail.setDepartment(accountReqDTO.getDepartmentId());
        feeInvoiceDetail.setInvoiceId(accountReqDTO.getFictitiousId());
        feeInvoiceDetail.setFeeInvoiceId(invoiceId);
        feeInvoiceDetail.setAccountId(accountReqDTO.getAccountId());
        feeInvoiceDetail.setDates(accountReqDTO.getWorkDate());
        feeInvoiceDetail.setStatus("1");

        return feeInvoiceDetail;
    }

    /**
     * ?????????????????????
     *
     * @param projectId ??????ID
     * @return ??????
     */
    @Override
    public String getFeeCodeCk(String projectId) {
        String codes;
        //3201(??????????????????):	201(????????????)
        if ("3201".equals(projectId)) {
            codes = "201";
            //3501(?????????????????????):	201(????????????)
        } else if ("3501".equals(projectId)) {
            codes = "201";
            //32021(??????):		202(???????????????)
        } else if ("32021".equals(projectId) || "32041".equals(projectId)) {
            codes = "202";
            //32022(????????????):	203(???????????????)
        } else if ("32022".equals(projectId) || "32042".equals(projectId)) {
            codes = "203";
            //32023(????????????):	204(???????????????)
        } else if ("32023".equals(projectId) || "32043".equals(projectId)) {
            codes = "204";
            //32024(??????):		208(???????????????)
        } else if ("32024".equals(projectId) || "32044".equals(projectId)) {
            codes = "208";
        } else {
            codes = "";
        }

        return codes;
    }

    /**
     * ?????????????????????
     * 32021(??????):		202(???????????????)		bk:205 ?????????????????????
     * 32022(????????????):	203(???????????????)		bk:206 ?????????????????????
     * 32023(????????????):	204(???????????????)		bk:207 ?????????????????????????????????
     * 32024(??????):		208(???????????????)		bk:209 ????????????????????????????????????
     * 32042(????????????):	203(???????????????)		bk:206 ?????????????????????
     * 32043(????????????):	204(???????????????)		bk:207 ?????????????????????????????????
     *
     * @param projectId ??????ID
     * @return ??????
     */
    @Override
    public String getFeeCodeBk(String projectId) {
        String codes;
        //32021(??????)  bk:205 ?????????????????????
        if ("32021".equals(projectId) || "32041".equals(projectId)) {
            codes = "205";
            //32022(????????????):	bk:206 ?????????????????????
        } else if ("32022".equals(projectId) || "32042".equals(projectId)) {
            codes = "206";
            //32023(????????????):	bk:207 ?????????????????????????????????
        } else if ("32023".equals(projectId) || "32043".equals(projectId)) {
            codes = "207";
            //32024(??????):		bk:209 ????????????????????????????????????
        } else if ("32024".equals(projectId) || "32044".equals(projectId)) {
            codes = "209";
        } else {
            codes = "";
        }

        return codes;
    }


    /**
     * ?????? ????????????
     */
    public void unlockLog(AccountReqDTO accountReqDTO, BusinessPaymentReqDTO business, RightUser user, String code,
                          String message, String type, String sfxm) throws UnknownHostException {
        //?????? ??????webService?????? 01c74
        //???????????????????????? fee_uploadinfo
        UploadinfoReqDTO uploadinfoReqDTO = new UploadinfoReqDTO();
        uploadinfoReqDTO.setId(UUID.randomUUID().toString());
        uploadinfoReqDTO.setDepartId(accountReqDTO.getDepartmentId());
        uploadinfoReqDTO.setAccountId(accountReqDTO.getAccountId());
        uploadinfoReqDTO.setInvoiceId(accountReqDTO.getFictitiousId());
        uploadinfoReqDTO.setYwlb(type);
        uploadinfoReqDTO.setLsh(business.getParams());
        uploadinfoReqDTO.setSfxm(sfxm);
        uploadinfoReqDTO.setTotalMoney(business.getTotalMoney());
        uploadinfoReqDTO.setUserId(user.getId());
        InetAddress addr = InetAddress.getLocalHost();
        uploadinfoReqDTO.setUserIp(addr.getHostAddress());
        uploadinfoReqDTO.setDate(new Date());
        uploadinfoReqDTO.setCode(code);
        uploadinfoReqDTO.setMsg(message);
        uploadinfoReqDTO.setGxsj(new Date());

        uploadinfoMapper.saveUploadinfo(uploadinfoReqDTO);
    }

    /**
     * ????????????
     */
    String writeXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
            "<root>\n" +
            "<head>\n" +
            "<code>0</code>\n" +
            "<message>??????????????????</message>\n" +
            "<rownum>0</rownum>\n" +
            "</head>\n" +
            "<body>\n" +
            "</body>\n" +
            "</root>";

    /**
     * ?????? ?????????????????????
     *
     * @param unlock ????????????
     * @return ??????
     */
    public Map<String, String> vehicleUnlock(VUnlockReqDTO unlock) {

        try {
            String str = XmlFileUtils.writeXmlDoc(unlock);
            String writeXmlDoc = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), str, XmlFileUtils.getXmlFileFoot());
            // ?????????????????????????????????
//            String result = webServiceUtil.writeCallWeb("01", "01C74", writeXmlDoc);
            Map<String, String> map = new HashMap<>();
            Map<String, String> resultMap = XmlUtils.parseXml2Map(writeXml, map);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * ?????? ?????????????????????
     *
     * @param dUnlock ????????????
     * @return ??????
     */
    @Override
    public Map<String, String> driverLicenseUnlock(DUnlockReqDTO dUnlock) {

        try {
            String str = XmlFileUtils.writeXmlDoc(dUnlock);
            String writeXmlDoc = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), str, XmlFileUtils.getXmlFileFoot());
            // ?????????????????????????????????
//            String result = webServiceUtil.writeCallWeb("01", "02C84", writeXmlDoc);
            Map<String, String> map = new HashMap<>();
            Map<String, String> resultMap = XmlUtils.parseXml2Map(writeXml, map);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * ??????
     *
     * @param invoiceReqDTOS
     * @param details
     */
    @Transactional
    public void bookkeeping(List<FeeInvoice> invoiceReqDTOS, List<FeeInvoiceDetail> details, AccountReqDTO account) {

        // ?????????????????????????????????
        invoiceMapper.batchInsertInvoices(invoiceReqDTOS);
        // ?????????????????????????????????
        detailMapper.batchInsertDetails(details);
        // ????????????????????????FICTITIOUS_ID
        AccountReqDTO accountReqDTO = new AccountReqDTO();
        // ????????????1
        String fId = String.valueOf(Integer.parseInt(account.getFictitiousId()) + 1);
        accountReqDTO.setAccountId(account.getAccountId());
        accountReqDTO.setFictitiousId(fId);
        accountMapper.updateParam(accountReqDTO);
    }


    /**
     * ??????????????????????????????
     *
     * @param businessPayment ??????
     * @return ??????
     */
    @Override
    public DataReuslt getPayBasicsMessage(BusinessPaymentReqDTO businessPayment) {

        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        AccountReqDTO accountReqDTO = accountMapper.getOne(rightUser.getAccountId());
        ResultReqDTO resultReq = new ResultReqDTO();
        resultReq.setPayee(rightUser.getName());
        resultReq.setReceiceAccount(accountReqDTO.getReceiveAccount());
        String workDate = DateUtils.formatDates(accountReqDTO.getWorkDate());
        String[] arr = workDate.split("-");
        String newDate = arr[0] + "???" + arr[1] + "???" + arr[2] + "???";
        resultReq.setWorkDate(newDate);
        resultReq.setNoA("");
        resultReq.setFictitiousId(accountReqDTO.getFictitiousId());
        return DataReuslt.success(resultReq);
    }
}
