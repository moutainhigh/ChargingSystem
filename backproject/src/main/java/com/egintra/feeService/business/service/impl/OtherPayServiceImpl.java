package com.egintra.feeService.business.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.ProjectClassReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.business.BusinessPaymentReqDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.repository.mapper.FeeInvoiceDetailMapper;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.repository.mapper.MaterialMapper;
import com.egintra.common.repository.mapper.ProjectClassMapper;
import com.egintra.common.repository.mapper.VehicleOnlinePayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.business.service.OtherPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OtherPayServiceImpl implements OtherPayService {

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private VehicleOnlinePayMapper vehicleOnlinePayMapper;
    @Resource
    private ProjectClassMapper projectClassMapper;
    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private FeeInvoiceDetailMapper detailMapper;
    @Resource
    private MaterialMapper materialMapper;

    /**
     * 获取其他缴费信息
     *
     * @param body
     * @return
     */
    @Override
    public DataReuslt otherPay(Map<String, Object> body) {
        String totalMoney = body.get("totalMoney").toString();
        String payType = body.get("payType").toString();
        // 手输入的姓名
        String xm = body.get("xm").toString();
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        AccountReqDTO accountReqDTO = accountMapper.getOne(rightUser.getAccountId());
        List<Map<String, Object>> drvs = (List<Map<String, Object>>) body.get("drvs");
        BusinessPaymentReqDTO businessPayment = new BusinessPaymentReqDTO();
        businessPayment.setPayType(payType);
        businessPayment.setDrvs(drvs);
        businessPayment.setTotalMoney(new BigDecimal(totalMoney));
        // 现金支付
        List<FeeInvoice> invoiceReqDTOS = new ArrayList<>();
        List<FeeInvoiceDetail> details = new ArrayList<>();
//        if ("1".equals(payType)) {
            //添加主表数据
            //材料总价格
            double sumMaterialCost = 0;
            for (Map map : drvs) {
                String projectId = map.get("projectId").toString();
                String sumPrice = materialMapper.getSumMaterial(projectId);
                if (sumPrice == null) {
                    sumMaterialCost += 0;
                } else {
                    sumMaterialCost += Double.parseDouble(sumPrice);
                }
            }

            FeeInvoice feeInvoice = getInvoice(accountReqDTO, businessPayment, rightUser, xm, sumMaterialCost);
            feeInvoice.setLsh("");
            feeInvoice.setCxhp("");
            feeInvoice.setYwlx("");
            feeInvoice.setYwyy("");
            feeInvoice.setYwlb("3");
            invoiceReqDTOS.add(feeInvoice);
            double cost = sumMaterialCost;
            drvs.forEach(map -> {
                String classId = map.get("classId").toString();
                String projectId = map.get("projectId").toString();
                String projectName = map.get("projectName").toString();
                String quantity = map.get("quantity").toString();
                String unitPrice = map.get("unitPrice").toString();
                String sumPrice = materialMapper.getSumMaterial(projectId);
                if (sumPrice == null) {
                    sumPrice = "0";
                }
                ProjectClassReqDTO projectClassReqDTO = projectClassMapper.getOne(classId);
                FeeInvoiceDetail feeInvoiceDetail = getFeeInvoiceDetail(accountReqDTO, feeInvoice.getId());
                feeInvoiceDetail.setProjectId(projectId);
                feeInvoiceDetail.setProjectName(projectName);
                feeInvoiceDetail.setQuantity(quantity);
                feeInvoiceDetail.setClassId(classId);
                feeInvoiceDetail.setProvinceRate(String.valueOf(projectClassReqDTO.getProvinceRate()));
                feeInvoiceDetail.setUnitPrice(new BigDecimal(unitPrice));
                feeInvoiceDetail.setCityRate(String.valueOf(projectClassReqDTO.getCityRate()));
                feeInvoiceDetail.setMaterialCost(new BigDecimal(sumPrice));
                feeInvoiceDetail.setFinanceCode(projectClassReqDTO.getFinanceCode());
                feeInvoiceDetail.setPayWay("01");
                details.add(feeInvoiceDetail);
            });
            bookkeeping(invoiceReqDTOS, details, accountReqDTO);
            return DataReuslt.success("支付成功");
//        } else { // pos支付
//            return DataReuslt.fail(ResultCode.PAY_NOT_POS.code(),ResultCode.PAY_NOT_POS.message());
//        }
    }


    /**
     * 记账 发表主表
     *
     * @param invoiceReqDTOS
     * @param details
     */
    @Transactional
    public void bookkeeping(List<FeeInvoice> invoiceReqDTOS, List<FeeInvoiceDetail> details, AccountReqDTO account) {
        invoiceMapper.batchInsertInvoices(invoiceReqDTOS);
        detailMapper.batchInsertDetails(details);
        AccountReqDTO accountReqDTO = new AccountReqDTO();
        // 每次票加1
        String fId = String.valueOf(Integer.parseInt(account.getFictitiousId()) + 1);
        accountReqDTO.setAccountId(account.getAccountId());
        accountReqDTO.setFictitiousId(fId);
        accountMapper.updateParam(accountReqDTO);
    }

    /**
     * 封裝发票主表实体
     *
     * @param accountReqDTO
     * @param business
     * @param user
     * @param xm
     * @param cost
     * @return
     */
    public FeeInvoice getInvoice(AccountReqDTO accountReqDTO, BusinessPaymentReqDTO business, RightUser user, String xm, double cost) {
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
        feeInvoice.setDtimes(accountReqDTO.getWorkDate());
        feeInvoice.setReceiveAccount(accountReqDTO.getReceiveAccount()!=null?accountReqDTO.getReceiveAccount():"");
        feeInvoice.setCheckNumber(accountReqDTO.getFictitiousId());
        feeInvoice.setPosInfo("");
        feeInvoice.setTotalMoney(business.getTotalMoney());
        feeInvoice.setTotalPure(business.getTotalMoney().subtract(new BigDecimal(cost)));
        feeInvoice.setReceiver(user.getName());
        feeInvoice.setReceiverId(user.getNo());
        feeInvoice.setDates(accountReqDTO.getWorkDate());
        feeInvoice.setStatus("1");
        feeInvoice.setTypes("1");
        feeInvoice.setSfzmhm("");
        return feeInvoice;
    }

    /**
     * 封装发票明细实体
     *
     * @param accountReqDTO
     * @param invoiceId
     * @return
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
}
