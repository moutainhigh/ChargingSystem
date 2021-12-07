package com.egintra.feeService.business.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.DrvPreasignReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.business.DUnlockReqDTO;
import com.egintra.common.dto.business.SysDateReqDTO;
import com.egintra.common.dto.business.UploadinfoReqDTO;
import com.egintra.common.dto.finance.FeeDrvjkMeterialRespDTO;
import com.egintra.common.dto.finance.FeeDrvjkReqDTO;
import com.egintra.common.dto.pay.PayExaminationFeeReqDTO;
import com.egintra.common.exception.ResultCode;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.repository.mapper.BusinessMapper;
import com.egintra.common.repository.mapper.DrvPreasignMapper;
import com.egintra.common.repository.mapper.FeeInvoiceDetailMapper;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.repository.mapper.UploadinfoMapper;
import com.egintra.common.repository.mapper.VehicleOnlinePayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.StringUtil;
import com.egintra.feeService.business.service.BusinessService;
import com.egintra.feeService.business.service.ExamPayService;
import com.egintra.feeService.pay.service.DrvPreasignService;
import com.egintra.feeService.pay.service.FeeInterfaceTwoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 考试缴费实现
 *
 * @author liushihao
 * @date 2021/9/10
 */
@Service
public class ExamPayServiceImpl implements ExamPayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamPayServiceImpl.class);

    @Resource
    private BusinessMapper businessMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private VehicleOnlinePayMapper vehicleOnlinePayMapper;
    @Resource
    private UploadinfoMapper uploadinfoMapper;
    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private FeeInvoiceDetailMapper feeInvoiceDetailMapper;
    @Resource
    private DrvPreasignMapper drvPreasignMapper;
    @Resource
    private DrvPreasignService drvPreasignService;
    @Resource
    private FeeInterfaceTwoService feeInterfaceTwoService;
    @Resource
    private BusinessService businessService;

    /**
     * 查询考试缴费信息
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    @Override
    @Transactional
    public DataReuslt queryExamPayInfo(PayExaminationFeeReqDTO reqDTO) {

        // 判断号码是否为空，为空则报错提示
        if (StringUtil.isEmpty(reqDTO.getNumber())) {
            return DataReuslt.fail(ResultCode.PLEASE_INPUT_LSH_OR_SFZMHM.code(),
                    ResultCode.PLEASE_INPUT_LSH_OR_SFZMHM.message());
        }
        // 如果号码长度等于13为流水号，否则为身份证明号码
        String number = reqDTO.getNumber();
        if (13 == number.length()) {
            reqDTO.setLsh(number);
        } else {
            reqDTO.setSfzmhm(number);
        }

        // 获取系统时间和账套工作日期，比较账套工作日期是否大于或者等于系统日期，否则更新账套工作日期，是则进行业务操作
        // 获取当前系统日期
        SysDateReqDTO sysDate = businessMapper.getSysDate();
        String strDay = sysDate.getStrDay().replace(".", "-");
        Date sysCurDate = DateUtils.parseDates(strDay);
        // 根据用户信息获取账套设置时间
        RightUserDTO rightUserDTO = new RightUserDTO();
        // 获取用户信息
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        if (null == rightUser) {

        }
        String accoutId = rightUser.getAccountId();
        AccountReqDTO accountReqDTO = accountMapper.getOne(accoutId);
        if (null == accountReqDTO) {

        }
        Date accWorkDate = accountReqDTO.getWorkDate();
        // 账套时间小于系统时间 更新账套时间
        if (accWorkDate.compareTo(sysCurDate) < 0) {
            AccountReqDTO account = new AccountReqDTO();
            account.setWorkDate(sysCurDate);
            account.setAccountId(accoutId);
            accountMapper.updateAccountById(account);
        }

        // 判断是否欠费（根据考试费类型）
        DrvPreasignReqDTO dto = new DrvPreasignReqDTO();
        BeanUtils.copyProperties(reqDTO, dto);
        DataReuslt<Integer> checkReuslt = drvPreasignService.checkExamPay(dto);
        if (!ResultCode.SUCCESS.code().equals(checkReuslt.getCode())) {
            return DataReuslt.fail(checkReuslt.getCode(), checkReuslt.getMessage());
        }
        // 欠费次数
        Integer arrearsNum = checkReuslt.getData();
        // 如果欠费次数小于1，即不欠费，则提示“当前无可缴费项目”
        if (arrearsNum < 1) {
            return DataReuslt.fail(ResultCode.CUR_HAVE_NO_ARREARS_RECORD.code(),
                    ResultCode.CUR_HAVE_NO_ARREARS_RECORD.message());
        }

        // 获取考试科目收费参数数据
        List<FeeDrvjkMeterialRespDTO> examItems = this.getExamItems(reqDTO);
        if (CollectionUtils.isEmpty(examItems)) {
            return DataReuslt.fail(ResultCode.BUSINESS_DOES_NOT_EXIST.code(),
                    ResultCode.BUSINESS_DOES_NOT_EXIST.message());
        }

        return DataReuslt.success(examItems);
    }

    /**
     * 获取考试科目收费参数数据
     *
     * @param reqDTO 参数
     * @return 结果
     */
    public List<FeeDrvjkMeterialRespDTO> getExamItems(PayExaminationFeeReqDTO reqDTO) {
        // 先根据流水号或身份证明号码查询考试预约信息
        DrvPreasignReqDTO drvPreasignReqDTO = new DrvPreasignReqDTO();
        BeanUtils.copyProperties(reqDTO, drvPreasignReqDTO);
        DrvPreasignReqDTO drvPreasignParam = drvPreasignMapper.getDrvPreasignParam(drvPreasignReqDTO);

        // 取得获取数据方式，目前默认下发库方式："datafrom"值为"1"
        // (直接)获取考试科目收费参数数据
        FeeDrvjkReqDTO businessInfo = this.getBusinessInfo(
                reqDTO.getKskm(), reqDTO.getKsflb());
        businessInfo.setZjcx(drvPreasignParam.getKscx());
        DataReuslt dataReuslt = feeInterfaceTwoService.driverLicensePaymentInfo(businessInfo);
        List<FeeDrvjkMeterialRespDTO> examItems = (List<FeeDrvjkMeterialRespDTO>) dataReuslt.getData();
        LOGGER.info("考试科目收费参数数据：{}", examItems);

        return examItems;
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
        // 科目一和考试费1
        if ("1".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm1");
            param.setYwyy("exm1");
        }
        // 科目一和补考费2
        if ("1".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm1");
            param.setYwyy("mexm1");
        }
        // 科目二和考试费1
        if ("2".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm2");
            param.setYwyy("exm2");
        }
        // 科目二和补考费2
        if ("2".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm2");
            param.setYwyy("mexm2");
        }
        // 科目三和补考费1
        if ("3".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm3");
            param.setYwyy("exm3");
        }
        // 科目三和补考费2
        if ("3".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm3");
            param.setYwyy("mexm3");
        }
        // 科目四和补考费1
        if ("4".equals(kskm) && "1".equals(ksflx)) {
            param.setYwlx("exm4");
            param.setYwyy("exm4");
        } // 科目四和补考费2
        if ("4".equals(kskm) && "2".equals(ksflx)) {
            param.setYwlx("mexm4");
            param.setYwyy("mexm4");
        }

        return param;
    }

    /**
     * 确认支付
     *
     * @param reqDTO 参数
     * @return 查询结果
     */
    @Override
    public DataReuslt examPay(PayExaminationFeeReqDTO reqDTO) {
        // 1.数据校验
        if ("03".equals(reqDTO.getZffs())) {
            return DataReuslt.fail("", "当前暂不支持POS支付");
        }
        // 如果号码长度等于13为流水号，否则为身份证明号码
        String number = reqDTO.getNumber();
        if (13 == number.length()) {
            reqDTO.setLsh(number);
        } else reqDTO.setSfzmhm(number);

        // 2.获取基础数据：用户信息及账套信息
        // 获取用户信息
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        if (null == rightUser) {

        }
        // 获取账套信息:根据用户信息
        String accoutId = rightUser.getAccountId();
        AccountReqDTO accountReqDTO = accountMapper.getOne(accoutId);
        if (null == accountReqDTO) {

        }

        // 3.记账
        // 获取收费项目
        List<FeeDrvjkMeterialRespDTO> examItems = this.getExamItems(reqDTO);
        // 封装记账所需对象：发票明细表数据
        FeeInvoice feeInvoice = this.getFeeInvoice(rightUser, accountReqDTO, reqDTO, examItems);
        // 封装记账所需对象：发票明细表数据
        List<FeeInvoiceDetail> details = this.getFeeInvoiceDetail(feeInvoice, examItems);
        // 记账（发票主表和明细表）并更新账套中发票号（递增加一）
        this.bookkeeping(feeInvoice, details, accountReqDTO);

        // 4.解锁，调用02C84接口：根据每条明细数量循环调平台接口, 解锁正常，缴费完成；解锁失败，记录日志
        Date currentDate = DateUtils.getCurrentDate();
        // 循环所有缴费项目解锁
        for (FeeDrvjkMeterialRespDTO examItem : examItems) {
            // 获取当前缴费项目的数量
            Integer quantity = Integer.valueOf(examItem.getQuantity());
            DUnlockReqDTO dUnlock;
            // 循环缴费数量调用平台解锁
            for (int i = 1; i <= quantity; i++) {
                dUnlock = new DUnlockReqDTO();
                dUnlock.setLsh(examItem.getLsh());
                if ("1".equals(reqDTO.getKsflb())) {
                    String sfxm = businessService.getFeeCodeCk(examItem.getProjectId());
                    dUnlock.setSfxm(sfxm);
                } else {
                    String sfxm = businessService.getFeeCodeBk(examItem.getProjectId());
                    dUnlock.setSfxm(sfxm);
                }
                dUnlock.setJe(examItem.getUnitPrice());
                dUnlock.setSfr("");
                dUnlock.setSfsj(currentDate);
                dUnlock.setBj("1");
                // 解锁
                Map<String, String> unlockResult = businessService.driverLicenseUnlock(dUnlock);
                // 判断解锁结果，非1失败，即记录错误日志
                if (!"1".equals(unlockResult.get("head.code"))) {
                    // 记录错误日志
                    this.saveUnlockFailLog(rightUser, accountReqDTO, unlockResult, examItem);
                }
            }
        }

        // 5.支付成功，记账成功则缴费成功
        return DataReuslt.success();
    }

    /**
     * 封装发票主数据
     *
     * @param rightUser     用户信息
     * @param accountReqDTO 账套信息
     * @param reqDTO        请求参数
     * @param examItems     收费项目明细
     * @return 结果
     */
    public FeeInvoice getFeeInvoice(RightUser rightUser, AccountReqDTO accountReqDTO, PayExaminationFeeReqDTO reqDTO,
                                    List<FeeDrvjkMeterialRespDTO> examItems) {
        FeeInvoice feeInvoice = new FeeInvoice();
        // 获取序列号作为记账时主表ID
        String id = vehicleOnlinePayMapper.getTmpId();
        feeInvoice.setInvoiceId(id);
        feeInvoice.setDepartment(rightUser.getDepartId());
        feeInvoice.setAccountId(rightUser.getAccountId());
        feeInvoice.setInvoiceId(accountReqDTO.getFictitiousId());
        feeInvoice.setPaperId("");
        feeInvoice.setPayUnit(rightUser.getName());
        feeInvoice.setPayWay(reqDTO.getZffs());
        feeInvoice.setPosid("");
        feeInvoice.setDtimes(DateUtils.getCurDateString());
        feeInvoice.setReceiveAccount(accountReqDTO.getReceiveAccount());
        feeInvoice.setCheckNumber("");
        feeInvoice.setPosInfo("");
        feeInvoice.setTotalMoney(this.calTotalMoney(examItems));
        feeInvoice.setTotalPure(this.calNetIncome(examItems));
        feeInvoice.setReceiver(rightUser.getName());
        feeInvoice.setReceiverId(rightUser.getNo());
        feeInvoice.setDates(accountReqDTO.getWorkDate());
        feeInvoice.setStatus("1");
        feeInvoice.setTypes("1");
        feeInvoice.setSfzmhm("");

        return feeInvoice;
    }

    /**
     * 封装发票明细数据
     *
     * @param feeInvoice 发票主数据
     * @param examItems  收费项目明细
     * @return 结果
     */
    public List<FeeInvoiceDetail> getFeeInvoiceDetail(FeeInvoice feeInvoice, List<FeeDrvjkMeterialRespDTO> examItems) {
        List<FeeInvoiceDetail> detailList = new ArrayList<>();
        FeeInvoiceDetail detail;
        for (FeeDrvjkMeterialRespDTO examItem : examItems) {
            detail = new FeeInvoiceDetail();
            // 获取序列号作为记账时明细表ID
            String tmpDetailId = vehicleOnlinePayMapper.getTmpDetailId();
            detail.setId(tmpDetailId);
            detail.setFeeInvoiceId(feeInvoice.getId());
            detail.setDepartment(feeInvoice.getDepartment());
            detail.setAccountId(feeInvoice.getAccountId());
            detail.setInvoiceId(feeInvoice.getInvoiceId());
            detail.setPaperId(feeInvoice.getPaperId());
            detail.setDates(feeInvoice.getDates());
            // 发票状态 1：正常 0：作废 2：负数发票。此处默认为“1”
            detail.setStatus("1");
            detail.setProjectId(examItem.getProjectId());
            detail.setProjectName(examItem.getProjectName());
            detail.setUnitPrice(examItem.getUnitPrice());
            detail.setQuantity(examItem.getQuantity());
            detail.setClassId(examItem.getClassId());
            detail.setProvinceRate(examItem.getProvinceRate());
            detail.setCityRate(examItem.getCityRate());
            detail.setMaterialCost(examItem.getCost());
            detail.setFinanceCode(examItem.getFinanceCode());
            detail.setPayWay("01");

            detailList.add(detail);
        }

        return detailList;
    }

    /**
     * 计算总金额
     *
     * @param examItems 收费项目明细
     * @return 总金额
     */
    public BigDecimal calTotalMoney(List<FeeDrvjkMeterialRespDTO> examItems) {
        BigDecimal totalMoney = new BigDecimal("0.00");
        for (FeeDrvjkMeterialRespDTO examItem : examItems) {
            BigDecimal curMoney = examItem.getUnitPrice().multiply(
                    new BigDecimal(examItem.getQuantity()));
            totalMoney.add(curMoney);
        }

        return totalMoney;
    }

    /**
     * 计算纯收入：总金额-材料费
     *
     * @param examItems 收费项目明细
     * @return 纯收入
     */
    public BigDecimal calNetIncome(List<FeeDrvjkMeterialRespDTO> examItems) {
        BigDecimal totalMoney = new BigDecimal("0.00");
        for (FeeDrvjkMeterialRespDTO examItem : examItems) {
            BigDecimal curMoney = examItem.getUnitPrice().multiply(
                    new BigDecimal(examItem.getQuantity()));
            totalMoney.add(curMoney);
        }

        BigDecimal netIncome = totalMoney.subtract(examItems.get(0).getCost());

        return netIncome;
    }

    /**
     * 记账并更新账套中发票号
     *
     * @param feeInvoice  发票主数据
     * @param details     发票明细数据集合
     * @param accountInfo 账套信息
     */
    @Transactional
    public void bookkeeping(FeeInvoice feeInvoice, List<FeeInvoiceDetail> details, AccountReqDTO accountInfo) {
        // 记账：保存发票主表信息
        List<FeeInvoice> feeInvoices = new ArrayList<>();
        feeInvoices.add(feeInvoice);
        int insertInvoices = invoiceMapper.batchInsertInvoices(feeInvoices);
        LOGGER.info("记账保存发票主表信息条数：{}", insertInvoices);
        // 记账：保存发票明细表信息
        int insertDetails = feeInvoiceDetailMapper.batchInsertDetails(details);
        LOGGER.info("记账保存发票明细表信息条数：{}", insertInvoices);
        // 设置账套信息，更新账套发票号
        AccountReqDTO accountReqDTO = new AccountReqDTO();
        accountReqDTO.setAccountId(accountInfo.getAccountId());
        // 每次票加1
        String fId = String.valueOf(Integer.parseInt(accountInfo.getFictitiousId()) + 1);
        accountReqDTO.setFictitiousId(fId);
        accountMapper.updateParam(accountReqDTO);
    }

    /**
     * 解锁失败时录入日志
     *
     * @param rightUser     用户信息
     * @param accountReqDTO 账套信息
     * @param unlockResult  解锁结果
     * @param examItem      缴费项目信息
     */
    @Transactional
    public void saveUnlockFailLog(RightUser rightUser, AccountReqDTO accountReqDTO, Map<String, String> unlockResult,
                                  FeeDrvjkMeterialRespDTO examItem) {
        UploadinfoReqDTO uploadinfoReqDTO = new UploadinfoReqDTO();
        uploadinfoReqDTO.setId(UUID.randomUUID().toString());
        uploadinfoReqDTO.setDepartId(rightUser.getDepartId());
        uploadinfoReqDTO.setAccountId(rightUser.getAccountId());
        uploadinfoReqDTO.setInvoiceId(accountReqDTO.getFictitiousId());
        // 业务类别考试费缴费默认为“2”
        uploadinfoReqDTO.setYwlb("2");
        uploadinfoReqDTO.setLsh(examItem.getLsh());
        uploadinfoReqDTO.setSfxm(examItem.getProjectId());
        uploadinfoReqDTO.setTotalMoney(examItem.getUnitPrice());
        uploadinfoReqDTO.setUserId(rightUser.getNo());
        String ip = "";
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            ip = ip4.getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.info("解锁失败时录入日志获取IP地址异常");
            e.printStackTrace();
        }
        uploadinfoReqDTO.setUserIp(ip);
        uploadinfoReqDTO.setDate(new Date());
        uploadinfoReqDTO.setCode(unlockResult.get("head.code"));
        uploadinfoReqDTO.setMsg(unlockResult.get("head.message"));
        uploadinfoReqDTO.setGxsj(new Date());
        uploadinfoMapper.saveUploadinfo(uploadinfoReqDTO);
    }
}
