package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.VehiclePayReqDTO;
import com.egintra.common.dto.pay.CommonFieldReqDTO;
import com.egintra.common.dto.pay.FeePayparaReqDTO;
import com.egintra.common.dto.pay.FeePayparaRespDTO;
import com.egintra.common.dto.pay.VechicleDTO;
import com.egintra.common.dto.sys.FeeGetDataSetRespDTO;
import com.egintra.common.repository.mapper.FeeGetDataSetMapper;
import com.egintra.common.repository.mapper.FeePayparaMapper;
import com.egintra.common.repository.mapper.VehicleOnlinePayMapper;
import com.egintra.common.repository.mapper.VehiclePayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.WebServiceUtil;
import com.egintra.common.utils.XmlFileUtils;
import com.egintra.common.utils.XmlUtils;
import com.egintra.feeService.pay.service.InvoiceService;
import com.egintra.feeService.pay.service.TemporaryService;
import com.egintra.feeService.pay.service.VehicleOnlinePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehicleOnlinePayServiceImpl implements VehicleOnlinePayService {

    @Resource
    private InvoiceService invoiceService;
    @Resource
    private VehiclePayMapper vehiclePayMapper;
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

    @Value("${vOnlinePay.fee.onLine.modelid}")
    private String vPayModelId;


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
     * ?????????????????????
     *
     * @param lsh
     * @return
     */
    @Override
    public DataReuslt onlinePayment(String lsh) {
        DataReuslt dataReuslt = getVehicleOnlinePay(lsh);
        if ("000000".equals(dataReuslt.getCode())) {
            List<VehiclePayReqDTO> vehiclePayList = (List<VehiclePayReqDTO>) dataReuslt.getData();
            if (vehiclePayList.size() != 0) {
                vehiclePayList.forEach(vehiclePayReqDTO -> {
                    commonTemp(vehiclePayReqDTO, vehiclePayReqDTO.getDto());
                });
            }
            return DataReuslt.success("????????????");
        } else {
            return dataReuslt;
        }
    }


    /**
     * ????????????
     *
     * @param vehiclePayReqDTO
     */
    public void commonTemp(VehiclePayReqDTO vehiclePayReqDTO, FeePayparaRespDTO dto) {
        // ?????????
        BigDecimal totalSum = vehiclePayReqDTO.getUnitPrice().multiply(
                new BigDecimal(vehiclePayReqDTO.getQuantity()));
        // ?????????
        BigDecimal profitPrice = totalSum.subtract(vehiclePayReqDTO.getMaterialCost());
        // ??????orderId
        String orderId = vehicleOnlinePayMapper.getGenerateOrder(vehiclePayReqDTO.getLsh());
        CommonFieldReqDTO commonFieldReqDTO = new CommonFieldReqDTO();
        commonFieldReqDTO.setOrderid(orderId);
        commonFieldReqDTO.setTotalSum(totalSum);
        commonFieldReqDTO.setProfitPrice(profitPrice);
        commonFieldReqDTO.setProjectId(vehiclePayReqDTO.getProjectId());
        commonFieldReqDTO.setProjectName(vehiclePayReqDTO.getProjectName());
        commonFieldReqDTO.setUnitPrice(vehiclePayReqDTO.getUnitPrice());
        commonFieldReqDTO.setQuantity(vehiclePayReqDTO.getQuantity());
        commonFieldReqDTO.setClassId(vehiclePayReqDTO.getClassId());
        commonFieldReqDTO.setProvinceRate(vehiclePayReqDTO.getProvinceRate());
        commonFieldReqDTO.setCityRate(vehiclePayReqDTO.getCityRate());
        commonFieldReqDTO.setMaterialCost(vehiclePayReqDTO.getMaterialCost());
        commonFieldReqDTO.setFinanceCode(vehiclePayReqDTO.getFinanceCode());
        commonFieldReqDTO.setHpzl(vehiclePayReqDTO.getHpzl());
        commonFieldReqDTO.setYwlx(vehiclePayReqDTO.getYwlx());
        commonFieldReqDTO.setYwyy(vehiclePayReqDTO.getYwyy());
        commonFieldReqDTO.setXm(vehiclePayReqDTO.getHphm());
        commonFieldReqDTO.setLsh(vehiclePayReqDTO.getLsh());
        commonFieldReqDTO.setAccountId(dto.getAccountid());
        commonFieldReqDTO.setGlbm(vehiclePayReqDTO.getGlbm());
        commonFieldReqDTO.setYwlb("1");
        commonFieldReqDTO.setCxhp(vehiclePayReqDTO.getHpzl());
        commonFieldReqDTO.setHphm(vehiclePayReqDTO.getHphm());
        temporaryService.temporaryAccount(commonFieldReqDTO, dto);
        // ????????????????????????
    }


    /**
     * ??????xml???????????????
     *
     * @return
     */
    public Map<String, String> getXmlMap(String lsh) {
        try {
            VechicleDTO vechicleDTO = new VechicleDTO();
            vechicleDTO.setLsh(lsh);
            String str = XmlFileUtils.selectXmlDoc(vechicleDTO);
            String queryXmlDoc = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), str, XmlFileUtils.getXmlFileFoot());
            // ?????????????????????????????????
//            String result = webServiceUtil.queryCallWeb("01", "02C16", queryXmlDoc);
            Map<String, String> map = new HashMap<String, String>();
            Map<String, String> xmlMap = XmlUtils.parseXml2Map(xml, map);
            return xmlMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ????????????????????????????????????
     *
     * @param lsh
     * @return
     */
    @Override
    public DataReuslt getVehicleOnlinePay(String lsh) {
        //???????????????????????????????????????????????????????????????
        FeeGetDataSetRespDTO feeGetDataSetRespDTO = feeGetDataSetMapper.getOneByModelId(vPayModelId);
        if (feeGetDataSetRespDTO == null) {
            return DataReuslt.fail("??????id?????????");
        }
        String dataFrom = feeGetDataSetRespDTO.getDatafrom();
        if ("1".equals(dataFrom)) { // ????????????webservice??????
            // ?????????????????????????????????01c25?????? ???????????? glbm,hpzl,ywlxx,ywyy
            Map<String, String> xmlMap = getXmlMap(lsh);
            String code = xmlMap.get("head.code");
            if ("1".equals(code)) {
                String num = xmlMap.get("head.rownum");
                if (Integer.parseInt(num) != 0) {
                    String ywlx = xmlMap.get("body.veh.ywlx");
                    String ywyy = xmlMap.get("body.veh.ywyy");
                    String hpzl = xmlMap.get("body.veh.hpzl");
                    String hphm = xmlMap.get("body.veh.hphm");
                    String glbm = xmlMap.get("body.veh.glbm");
                    // ????????????????????????
                    DataReuslt dataReuslt = invoiceService.isPaymentNot(lsh);
                    String reusltCode = dataReuslt.getCode();
                    if (!"000000".equals(reusltCode)) {
                        if ("01".equals(reusltCode)) {
                            return DataReuslt.fail(dataReuslt.getMessage());
                        }

                        FeePayparaReqDTO feePayparaReqDTO = new FeePayparaReqDTO();
                        feePayparaReqDTO.setGlbm(glbm);
                        FeePayparaRespDTO dto = feePayparaMapper.queryFeePaypara(feePayparaReqDTO);
                        if (dto != null) {
                            VehiclePayReqDTO vehiclePayReqDTO = new VehiclePayReqDTO();
                            vehiclePayReqDTO.setHpzl(hpzl);
                            vehiclePayReqDTO.setYwlx(ywlx);
                            vehiclePayReqDTO.setYwyy(ywyy);
                            List<VehiclePayReqDTO> vehiclePayList = vehiclePayMapper.getVehiclePayList(vehiclePayReqDTO);
                            if (vehiclePayList.size() != 0) {
                                vehiclePayList.forEach(vehiclePayReqDTO1 -> {
                                    vehiclePayReqDTO1.setHphm(hphm);
                                    vehiclePayReqDTO1.setGlbm(glbm);
                                    vehiclePayReqDTO1.setYwyy(ywyy);
                                    vehiclePayReqDTO1.setYwlx(ywlx);
                                    vehiclePayReqDTO1.setDto(dto);
                                    vehiclePayReqDTO1.setLsh(lsh);
                                });
                                return DataReuslt.success(vehiclePayList);
                            } else {
                                return DataReuslt.fail("???????????????????????????");
                            }
                        }
                    } else { //??????
                        return DataReuslt.success("?????????");
                    }
                } else {
                    return DataReuslt.fail("????????????????????????");
                }
            }
        }
        return DataReuslt.success("????????????");
    }


}
