package com.egintra.feeService.pay.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.InvoiceDetailTmpReqDTO;
import com.egintra.common.dto.InvoiceTmpDTO;
import com.egintra.common.dto.pay.CommonFieldReqDTO;
import com.egintra.common.dto.pay.FeePayparaRespDTO;
import com.egintra.common.dto.pay.PayReqDTO;
import com.egintra.common.dto.pay.PayinfoReqDTO;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.repository.mapper.InvoiceTmpDetailMapper;
import com.egintra.common.repository.mapper.InvoiceTmpMapper;
import com.egintra.common.repository.mapper.PayInfoMapper;
import com.egintra.common.repository.mapper.PayMapper;
import com.egintra.common.repository.mapper.VehicleOnlinePayMapper;
import com.egintra.common.utils.DateUtils;
import com.egintra.feeService.pay.service.TemporaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 记临时账公共接口
 */
@Service
public class TemporaryServiceImpl implements TemporaryService {

    @Resource
    private VehicleOnlinePayMapper vehicleOnlinePayMapper;
    @Resource
    private InvoiceTmpMapper invoiceTmpMapper;
    @Resource
    private InvoiceTmpDetailMapper invoiceTmpDetailMapper;
    @Resource
    private PayInfoMapper payInfoMapper;
    @Resource
    private PayMapper payMapper;

    @Resource
    private AccountMapper accountMapper;

    /**
     * 记临时账
     *
     * @param commonField
     * @param feePayparaRespDTO
     */
    @Override
    @Transactional
    public void temporaryAccount(CommonFieldReqDTO commonField, FeePayparaRespDTO feePayparaRespDTO) {
        // 记临时账
        InvoiceTmpDTO invoiceTmp = new InvoiceTmpDTO();
        String tmpId = vehicleOnlinePayMapper.getTmpId();
       AccountReqDTO accountReqDTO = accountMapper.getOne(commonField.getAccountId());
        invoiceTmp.setId(tmpId);
        invoiceTmp.setAccountId(commonField.getAccountId());
        invoiceTmp.setOrderid(commonField.getOrderid());
        invoiceTmp.setInvoiceId(accountReqDTO.getFictitiousId());
        invoiceTmp.setDepartment(commonField.getGlbm());
        invoiceTmp.setPaperId("");
        invoiceTmp.setPayUnit(commonField.getXm());
        invoiceTmp.setPayWay("05");
        invoiceTmp.setTotalMoney(commonField.getTotalSum());
        invoiceTmp.setTotalPure(commonField.getProfitPrice());
        invoiceTmp.setReceiver("intnernet");
        invoiceTmp.setReceiverId("intnernet");
        invoiceTmp.setPosId("");
        invoiceTmp.setDates(accountReqDTO.getWorkDate());
        invoiceTmp.setDtimes(DateUtils.getCurrentDate());
        invoiceTmp.setReceiveAccount(accountReqDTO.getReceiveAccount());
        invoiceTmp.setCheckNumber("");
        invoiceTmp.setPosInfo("");
        invoiceTmp.setStatus("1");
        invoiceTmp.setTypes("1");
        invoiceTmp.setSfzmhm("");
        invoiceTmp.setLsh(commonField.getLsh());
        invoiceTmp.setCxhp(commonField.getCxhp());
        invoiceTmp.setYwlx(commonField.getYwlx());
        invoiceTmp.setYwyy(commonField.getYwyy());
        invoiceTmp.setYwlb(commonField.getYwlb());
        //返回主键id
        invoiceTmpMapper.addInvoiceTmp(invoiceTmp);
        InvoiceDetailTmpReqDTO invoiceDetailTmp = new InvoiceDetailTmpReqDTO();
        String detailId = vehicleOnlinePayMapper.getTmpDetailId();
        invoiceDetailTmp.setId(detailId);
        invoiceDetailTmp.setFeeInvoiceId(tmpId);
        invoiceDetailTmp.setDepartment(commonField.getGlbm());
        invoiceDetailTmp.setAccountId(commonField.getAccountId());
        invoiceDetailTmp.setInvoiceId(accountReqDTO.getFictitiousId());
        invoiceDetailTmp.setPaperId("");
        invoiceDetailTmp.setDates(DateUtils.getCurDateString());
        invoiceDetailTmp.setStatus("1");
        invoiceDetailTmp.setProjectId(commonField.getProjectId());
        invoiceDetailTmp.setProjectName(commonField.getProjectName());
        invoiceDetailTmp.setUnitPrice(commonField.getUnitPrice());
        invoiceDetailTmp.setQuantity(commonField.getQuantity());
        invoiceDetailTmp.setClassId(commonField.getClassId());
        invoiceDetailTmp.setProvinceRate(commonField.getProvinceRate());
        invoiceDetailTmp.setCityRate(commonField.getCityRate());
        invoiceDetailTmp.setMaterialCost(commonField.getMaterialCost());
        invoiceDetailTmp.setFinanceCode(commonField.getFinanceCode());
        invoiceDetailTmp.setPayWay("05");
        invoiceDetailTmp.setOrderid(commonField.getOrderid());
        invoiceTmpDetailMapper.addInvoiceTmpDetail(invoiceDetailTmp);
        payInfoOnLine(commonField, feePayparaRespDTO);
    }

    /**
     * 添加请求支付信息
     *
     * @param commonField
     * @param feePayparaRespDTO
     */
    @Override
    @Transactional
    public void payInfoOnLine(CommonFieldReqDTO commonField, FeePayparaRespDTO feePayparaRespDTO) {
        PayinfoReqDTO payinfoReqDTO = new PayinfoReqDTO();
        payinfoReqDTO.setOrderid(commonField.getOrderid());
        payinfoReqDTO.setZfwz("");
        payinfoReqDTO.setYwlb(commonField.getYwlb());
        payinfoReqDTO.setLsh(commonField.getLsh());
        payinfoReqDTO.setJe(commonField.getTotalSum());
        payinfoReqDTO.setYwlx(commonField.getYwlx());
        payinfoReqDTO.setYwyy(commonField.getYwyy());
        payinfoReqDTO.setGlbm(commonField.getGlbm());
        payinfoReqDTO.setShdm(feePayparaRespDTO.getShdm());
        payinfoReqDTO.setShgtdm(feePayparaRespDTO.getShgtdm());
        payinfoReqDTO.setBizh(feePayparaRespDTO.getBizh());
        payinfoReqDTO.setJym(feePayparaRespDTO.getJym());
        payinfoReqDTO.setJklx(feePayparaRespDTO.getJklx());
        payinfoReqDTO.setGkey(feePayparaRespDTO.getGkey());
        payinfoReqDTO.setWglx(feePayparaRespDTO.getWglx());
        payinfoReqDTO.setFzjg(feePayparaRespDTO.getFzjg());
        payinfoReqDTO.setYhdh("intnernet");
        payinfoReqDTO.setYhxm("微信公众号");
        payinfoReqDTO.setIp(feePayparaRespDTO.getZfwz());
        payinfoReqDTO.setDyzt(feePayparaRespDTO.getAccountid());
        payinfoReqDTO.setJkr(commonField.getHphm());
        payinfoReqDTO.setSfzmhm(commonField.getHphm());
        payinfoReqDTO.setJflb("05");
        payinfoReqDTO.setZffs("");
        payinfoReqDTO.setDates(DateUtils.getCurrentDate());
        payinfoReqDTO.setZfbj("0");
        payinfoReqDTO.setZfmsg("");
        payinfoReqDTO.setJzmsg("");
        payinfoReqDTO.setJzbj("0");
        payinfoReqDTO.setJsbj("0");
        payinfoReqDTO.setJsmsg("");
        payinfoReqDTO.setFhdm("");
        payinfoReqDTO.setGxsj(DateUtils.getCurrentDate());
        payInfoMapper.addPayInfo(payinfoReqDTO);
        PayReqDTO payReqDTO = new PayReqDTO();
        payReqDTO.setOrderId(commonField.getOrderid());
        payReqDTO.setLsh(commonField.getLsh());
        payReqDTO.setTotalfee(commonField.getTotalSum());
        payReqDTO.setZffs("05");
        payReqDTO.setZfsj(DateUtils.getCurrentDate());
        payReqDTO.setZfbj("0");
        payReqDTO.setHxbj("");
        payReqDTO.setBz1("");
        payReqDTO.setBz2("");
        payReqDTO.setBz3("");
        payReqDTO.setGlbm(commonField.getGlbm());
        payMapper.addPay(payReqDTO);
    }
}
