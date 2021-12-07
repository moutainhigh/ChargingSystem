package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.pay.PayReqDTO;
import com.egintra.common.dto.pay.PayinfoReqDTO;
import com.egintra.common.repository.mapper.PayInfoMapper;
import com.egintra.common.repository.mapper.PayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.feeService.pay.service.BankCallbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 银行回调接口
 */
@Service
public class BankCallbackServiceImpl implements BankCallbackService {

    @Resource
    private PayMapper payMapper;
    @Resource
    private PayInfoMapper payInfoMapper;

    /**
     * 添加银行放回的数据
     *
     * @param data
     * @return
     */
    @Override
    public DataReuslt addBankData(String data) {
        // 解析data数据
        String orderid = "123";
        String lsh = "123";
        String zfsj = "123";
        String zfbj = "1";
        String glbm = "123";
        BigDecimal totalfee = new BigDecimal("0.00");
        PayReqDTO payReqDTO = new PayReqDTO();
        payReqDTO.setOrderId(orderid);
        payReqDTO.setLsh(lsh);
        payReqDTO.setTotalfee(totalfee);
        payReqDTO.setZfbj(zfbj);
        payReqDTO.setZfsj(DateUtils.getCurrentDate());
        payReqDTO.setHxbj("");
        payReqDTO.setBz1("");
        payReqDTO.setBz2("");
        payReqDTO.setBz3("");
        payReqDTO.setGlbm(glbm);
        payReqDTO.setZffs("05");
        payMapper.addPay(payReqDTO);
        PayinfoReqDTO payinfoReqDTO = new PayinfoReqDTO();
        payinfoReqDTO.setZfbj("1");
        payinfoReqDTO.setOrderid(orderid);
        payInfoMapper.updateStatus(payinfoReqDTO);
        return DataReuslt.success("添加成功");
    }
}
