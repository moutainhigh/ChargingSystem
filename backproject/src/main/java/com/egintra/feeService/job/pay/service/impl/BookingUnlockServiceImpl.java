package com.egintra.feeService.job.pay.service.impl;

import com.egintra.common.dto.pay.CarUnlockReqDTO;
import com.egintra.common.dto.pay.DriveUnlockReqDTO;
import com.egintra.common.dto.pay.FeeInvoiceDetailTmpRespDTO;
import com.egintra.common.dto.pay.FeeInvoiceTmpRespDTO;
import com.egintra.common.dto.pay.FeeOperlogSaveReqDTO;
import com.egintra.common.dto.pay.FeePayinfoRespDTO;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.repository.mapper.FeeInvoiceDetailMapper;
import com.egintra.common.repository.mapper.FeeOperlogMapper;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.repository.mapper.InvoiceTmpDetailMapper;
import com.egintra.common.repository.mapper.InvoiceTmpMapper;
import com.egintra.common.repository.mapper.PayInfoMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.XmlFileUtils;
import com.egintra.feeService.job.pay.service.BookingUnlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付业务中的记账、解锁业务服务
 *
 * @author liushihao
 * @date 2021/8/11
 */
@Service
public class BookingUnlockServiceImpl implements BookingUnlockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingUnlockServiceImpl.class);

    @Resource
    private PayInfoMapper payInfoMapper;
    @Resource
    private InvoiceTmpMapper invoiceTmpMapper;
    @Resource
    private InvoiceTmpDetailMapper invoiceTmpDetailMapper;
    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private FeeInvoiceDetailMapper feeInvoiceDetailMapper;
    @Resource
    private FeeOperlogMapper feeOperlogMapper;

    /**
     * 记账、解锁业务服务
     *
     * @return 结果
     */
    @Override
    @Transactional
    public DataReuslt bookingUnlock() {
        LOGGER.info("记账、解锁任务开始");
        LOGGER.info("记账开始");
        // 查询payinfo表，获取支付成功且未记账数据
        String zfbj = "1";
        String jzbj = "0";
        List<FeePayinfoRespDTO> feePayinfos = payInfoMapper.queryUnpaidInfos(zfbj, jzbj, "");
        if (CollectionUtils.isEmpty(feePayinfos)) {
            LOGGER.info("记账、解锁任务结束:查询无需要记账的数据");
            return DataReuslt.success();
        }

        List<FeeInvoice> allFeeInvoices = new ArrayList<>();
        List<FeeInvoiceDetail> allFeeInvoiceDetails = new ArrayList<>();
        FeeInvoice feeInvoice;
        FeeInvoiceDetail feeInvoiceDetail;
        // 循环遍历支付信息，根据orderid获取临时记账发票主表和临时明细表，并转移到发票主表和明细表
        for (FeePayinfoRespDTO feePayinfo : feePayinfos) {
            // 根据orderid获取临时记账发票主表数据
            String orderId = feePayinfo.getOrderid();
            List<FeeInvoiceTmpRespDTO> feeInvoiceTmps = invoiceTmpMapper.queryFeeInvoiceTmps(orderId);
            if (CollectionUtils.isEmpty(feeInvoiceTmps)) {
                LOGGER.info("记账、解锁任务结束查询临时记账发票主表数据为空，orderId：{}", orderId);
            } else {
                List<String> tmpIds = feeInvoiceTmps.stream().map(FeeInvoiceTmpRespDTO::getId)
                        .distinct().collect(Collectors.toList());

                // 根据临时记账发票主表数据ID获取临时记账发票明细表数据
                List<FeeInvoiceDetailTmpRespDTO> feeInvoiceDetailTmps = invoiceTmpDetailMapper.feeInvoiceDetailTmp(tmpIds);

                // 将临时记账发票主表数据和临时记账发票明细表数据放到插入发票主表的数据集合内
                List<FeeInvoice> feeInvoices = new ArrayList<>();
                List<FeeInvoiceDetail> feeInvoiceDetails = new ArrayList<>();
                for (FeeInvoiceTmpRespDTO feeInvoiceTmp : feeInvoiceTmps) {
                    feeInvoice = new FeeInvoice();
                    BeanUtils.copyProperties(feeInvoiceTmp, feeInvoice);
                    feeInvoices.add(feeInvoice);
                }
                for (FeeInvoiceDetailTmpRespDTO feeInvoiceDetailTmp : feeInvoiceDetailTmps) {
                    feeInvoiceDetail = new FeeInvoiceDetail();
                    BeanUtils.copyProperties(feeInvoiceDetailTmp, feeInvoiceDetail);
                    feeInvoiceDetails.add(feeInvoiceDetail);
                }

                // 汇总所有
                allFeeInvoices.addAll(feeInvoices);
                allFeeInvoiceDetails.addAll(feeInvoiceDetails);
            }
        }

        // 将临时账中的数据转移到发票主表和明细表中
        invoiceMapper.batchInsertInvoices(allFeeInvoices);
        feeInvoiceDetailMapper.batchInsertDetails(allFeeInvoiceDetails);

        // 将临时账中的数据转移到发票主表和明细表后更新记账标记为“已记帐”
        List<String> orderIds = feePayinfos.stream().map(FeePayinfoRespDTO::getOrderid).distinct()
                .collect(Collectors.toList());
        int bookingUpdates = payInfoMapper.batchUpdateBookingMark(orderIds, "1", "");

        // 循环订单号（orderId）记录记账日志到fee_operlog
        List<FeeOperlogSaveReqDTO> insertBookings = new ArrayList<>();
        FeeOperlogSaveReqDTO saveDTO;
        Date currentDate = DateUtils.getCurrentDate();
        for (String orderId : orderIds) {
            saveDTO = new FeeOperlogSaveReqDTO();
            saveDTO.setOrderid(orderId);
            saveDTO.setCzlb("1");
            saveDTO.setZt("1");
            saveDTO.setMsg("");
            saveDTO.setDates(currentDate);
            insertBookings.add(saveDTO);
        }
        int bookingInserts = feeOperlogMapper.batchInsertFeeOperlogs(insertBookings);
        LOGGER.info("记账结束");

        LOGGER.info("解锁开始");
        // 查询payinfo表，获取支付成功且已记账、未解锁数据
        String jsbj = "0";
        jzbj = "1";
        List<FeePayinfoRespDTO> locks = payInfoMapper.queryUnpaidInfos(zfbj, jzbj, jsbj);
        if (CollectionUtils.isEmpty(feePayinfos)) {
            LOGGER.info("记账、解锁任务结束:查询无需要解锁的数据");
            return DataReuslt.success();
        }
        List<FeePayinfoRespDTO> carInfos = locks.stream().filter(p -> "1".equals(p.getYwlb()))
                .collect(Collectors.toList());
        List<FeePayinfoRespDTO> driveInfos = locks.stream().filter(p -> "2".equals(p.getYwlb()))
                .collect(Collectors.toList());
        List<FeePayinfoRespDTO> pushSuccessList = new ArrayList<>();
        // 如果机动车业务存在未解锁的，调用平台01c74，并进行解锁机动车业务
        if (!CollectionUtils.isEmpty(carInfos)) {
            CarUnlockReqDTO dto;
            for (FeePayinfoRespDTO carInfo : carInfos) {
                dto = new CarUnlockReqDTO();
                dto.setLsh(carInfo.getLsh());
                dto.setBz(String.valueOf(carInfo.getJe()));
                dto.setHpzl("");
                dto.setHphm("");
                String xmlBody = "";
                try {
                    xmlBody = XmlFileUtils.writeXmlDoc(dto);
                } catch (Exception e) {
                    LOGGER.info("生成机动车业务xml格式数据失败");
                }
                String xml = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), xmlBody, XmlFileUtils.getXmlFileFoot());
                // 开始调平台，并获取返回结果解析返回结果

                String code = "";
                if ("1".equals(code)) {
                    pushSuccessList.add(carInfo);
                }
            }
        }
        // 如果驾驶证业务存在未解锁的，调用平台02c84，并进行解锁驾驶证业务
        if (!CollectionUtils.isEmpty(driveInfos)) {
            DriveUnlockReqDTO dto;
            for (FeePayinfoRespDTO driveInfo : driveInfos) {
                // 特数情况：根据数量（quantity）循环调平台
                 dto = new DriveUnlockReqDTO();
                dto.setLsh(driveInfo.getLsh());
                dto.setJe(driveInfo.getJe());
                // 1是写入缴费信息;2是删除
                dto.setBj("1");
                dto.setSfr("");
                dto.setSfsj("");
                dto.setSfxm("");
                String xmlBody = "";
                try {
                    xmlBody = XmlFileUtils.writeXmlDoc(dto);
                } catch (Exception e) {
                    LOGGER.info("生成驾驶证业务xml格式数据失败");
                }
                String xml = String.format("%s%s%s", XmlFileUtils.getXmlFileHead(), xmlBody, XmlFileUtils.getXmlFileFoot());
               // 开始调平台，并获取返回结果解析返回结果

                String code = "";
                if ("1".equals(code)) {
                    pushSuccessList.add(driveInfo);
                }
            }
        }

        // 推平台成功后更新解锁标记为“已解锁”
        List<String> unlockOrderIds = pushSuccessList.stream().map(FeePayinfoRespDTO::getOrderid).distinct()
                .collect(Collectors.toList());
        int unlockUpdates = payInfoMapper.batchUpdateBookingMark(unlockOrderIds, "", "1");

        // 循环订单号（orderId）记录解锁日志到fee_operlog
        List<String> lockedOrderIds = locks.stream().map(FeePayinfoRespDTO::getOrderid).distinct()
                .collect(Collectors.toList());
        List<FeeOperlogSaveReqDTO> insertUnlocks = new ArrayList<>();
        FeeOperlogSaveReqDTO saveUnlocksDTO;
        currentDate = DateUtils.getCurrentDate();
        for (String orderId : lockedOrderIds) {
            saveUnlocksDTO = new FeeOperlogSaveReqDTO();
            saveUnlocksDTO.setOrderid(orderId);
            saveUnlocksDTO.setCzlb("2");
            saveUnlocksDTO.setZt("0");
            if (unlockOrderIds.contains(orderId)) {
                saveUnlocksDTO.setZt("1");
            }
            saveUnlocksDTO.setMsg("");
            saveUnlocksDTO.setDates(currentDate);
            insertUnlocks.add(saveUnlocksDTO);
        }
        int unlockInserts = feeOperlogMapper.batchInsertFeeOperlogs(insertUnlocks);

        LOGGER.info("解锁结束");
        LOGGER.info("记账、解锁任务结束");

        return DataReuslt.success();
    }
}
