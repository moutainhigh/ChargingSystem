package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.DrvPreasignReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.dto.pay.FeePayparaReqDTO;
import com.egintra.common.dto.pay.FeePayparaRespDTO;
import com.egintra.common.dto.pay.PayExaminationFeeReqDTO;
import com.egintra.common.dto.sys.FeeGetDataSetRespDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.repository.mapper.FeeGetDataSetMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.StringUtil;
import com.egintra.feeService.pay.service.DrvPreasignService;
import com.egintra.feeService.pay.service.ExaminationFeeOnLineService;
import com.egintra.feeService.pay.service.FeeInterfaceTwoService;
import com.egintra.feeService.pay.service.FeePayparaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExaminationFeeOnLineServiceImpl implements ExaminationFeeOnLineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExaminationFeeOnLineServiceImpl.class);

    @Resource
    private FeeGetDataSetMapper feeGetDataSetMapper;
    @Resource
    private DrvPreasignService drvPreasignService;
    @Resource
    private FeePayparaService feePayparaService;
    @Resource
    private FeeInterfaceTwoService feeInterfaceTwoService;

    @Value("${examination.fee.onLine.modelid}")
    private String examinationModelid;

    /**
     * 考试费线上缴费接口
     *
     * @param payExaminationFeeReqDTO 请求参数
     * @return 结果
     */
    @Override
    public DataReuslt queryExaminationFee(PayExaminationFeeReqDTO payExaminationFeeReqDTO) {
        // 流水号或身份证号码不能都为空
        if (StringUtil.isEmpty(payExaminationFeeReqDTO.getLsh())
                && StringUtil.isEmpty(payExaminationFeeReqDTO.getSfzmhm())) {
            return DataReuslt.fail(ResultCode.PLEASE_ENTER_LSH_OR_SFZMHM.code(),
                    ResultCode.PLEASE_ENTER_LSH_OR_SFZMHM.message());
        }
        // 根据modelid取得获取数据方式，判断
        FeeGetDataSetRespDTO feeGetDataSet = feeGetDataSetMapper.getOneByModelId(examinationModelid);
        // 如果是下发库，查下发库获取考试预约数据
//        if (DatafromEnum.LESSUE.getCode().equals(feeGetDataSet.getDatafrom())) {
//
//        }
        // 如果是调用webservice接口获取考试预约数据
//        if (DatafromEnum.WEB.getCode().equals(feeGetDataSet.getDatafrom())) {
//
//        }
        // 获取考试预约数据（根据流水号或身份号码）
        DrvPreasignReqDTO dto = new DrvPreasignReqDTO();
        if (!StringUtil.isEmpty(payExaminationFeeReqDTO.getLsh())) {
            dto.setLsh(payExaminationFeeReqDTO.getLsh());
        }
        if (!StringUtil.isEmpty(payExaminationFeeReqDTO.getSfzmhm())) {
            dto.setSfzmhm(payExaminationFeeReqDTO.getSfzmhm());
        }
        DataReuslt drvPreasignReuslt = drvPreasignService.queryPreasigns(dto);
        if (!ResultCode.SUCCESS.code().equals(drvPreasignReuslt.getCode())) {
            return DataReuslt.fail(drvPreasignReuslt.getCode(), drvPreasignReuslt.getMessage());
        }
        DrvPreasignReqDTO drvPreasign = (DrvPreasignReqDTO) drvPreasignReuslt.getData();

        // 判断是否欠费（根据考试费类型）
        dto.setKsflb(payExaminationFeeReqDTO.getKsflb());
        DataReuslt checkReuslt = drvPreasignService.checkExamPay(dto);
        if (!ResultCode.SUCCESS.code().equals(checkReuslt.getCode())) {
            return DataReuslt.fail(checkReuslt.getCode(), checkReuslt.getMessage());
        }
        // 欠费次数
        Integer arrearsNum = Integer.valueOf(checkReuslt.getMessage());
        // 如果欠费次数小于1，即不欠费，则提示“当前无可缴费项目”
        if (arrearsNum < 1) {
            return DataReuslt.fail("", "");
        }

        // 获取当前管理部门下对应的线上缴费账套号、线上支付银行等信息
        FeePayparaReqDTO queryDto = new FeePayparaReqDTO();
        queryDto.setGlbm(drvPreasign.getGlbm());
        DataReuslt payparaReuslt = feePayparaService.queryPayInfo(queryDto);
        if (!ResultCode.SUCCESS.code().equals(payparaReuslt.getCode())) {
            return DataReuslt.fail(payparaReuslt.getCode(), payparaReuslt.getMessage());
        }
        FeePayparaRespDTO feePaypara = (FeePayparaRespDTO) payparaReuslt.getData();

        // 获取考试科目收费参数数据
        FeeDrvjkReqDTO businessInfo = this.getBusinessInfo(
                payExaminationFeeReqDTO.getKskm(), payExaminationFeeReqDTO.getKsflb());
        businessInfo.setZjcx(drvPreasign.getKscx());
        DataReuslt dataReuslt = feeInterfaceTwoService.driverLicensePaymentInfo(businessInfo);
        List<FeeDrvjkMeterialRespDTO> resp = (List<FeeDrvjkMeterialRespDTO>) dataReuslt.getData();
        LOGGER.info("考试科目收费参数数据：{}", resp);
        if (CollectionUtils.isEmpty(resp)) {
            return DataReuslt.fail(ResultCode.BUSINESS_DOES_NOT_EXIST.code(),
                    ResultCode.BUSINESS_DOES_NOT_EXIST.message());
        }

        return DataReuslt.success(resp);
    }

    /**
     * 根据考试费类型获取业务类型和业务原因
     *
     * @param kskm  考试科目
     * @param ksflx 考试费类型
     * @return 结果
     */
    public FeeDrvjkReqDTO getBusinessInfo(String kskm, String ksflx) {
        FeeDrvjkReqDTO param = new FeeDrvjkReqDTO();
        if ("1".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm1");
            param.setYwyy("exm1");
        }
        if ("1".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm1");
            param.setYwyy("mexm1");
        }
        if ("2".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm2");
            param.setYwyy("exm2");
        }
        if ("2".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm2");
            param.setYwyy("mexm2");
        }
        if ("3".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm3");
            param.setYwyy("exm3");
        }
        if ("3".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm3");
            param.setYwyy("mexm3");
        }
        if ("4".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm4");
            param.setYwyy("exm4");
        }
        if ("4".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm4");
            param.setYwyy("mexm4");
        }

        return param;
    }
}
