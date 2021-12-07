package com.egintra.feeService.job.pay.service.impl;

import com.egintra.common.dto.pay.PayReqDTO;
import com.egintra.common.dto.pay.PayinfoReqDTO;
import com.egintra.common.repository.mapper.PayInfoMapper;
import com.egintra.common.repository.mapper.PayMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.job.pay.service.OrderUnlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单号状态查询模块
 *
 * @author zyt
 * @date 2021/8/11
 */
@Service
public class OrderUnlockServiceImpl implements OrderUnlockService {


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderUnlockServiceImpl.class);

    @Resource
    private PayInfoMapper payInfoMapper;

    @Resource
    private PayMapper payMapper;

    /**
     * 查看订单状态
     * @return
     */
    @Override
    public DataReuslt orderUnlock() {
        LOGGER.info("订单号查询状态开始了");
        //查询当天未支付成功的订单号
        List<PayinfoReqDTO> payinfoReqDTOS = payInfoMapper.getPayInfoList();
        payinfoReqDTOS.forEach(payinfoReqDTO -> {
            String orderId = payinfoReqDTO.getOrderid();
            //调用调用银行订单查询接口，查询订单状态 调用这个 IFOPF010(商户编号,交易类型,终端类型)
            String status = "1";
            // 返回的状态是已支付
            if ("1".equals(status)) {
                //更新支付状态
                PayinfoReqDTO payinfo = new PayinfoReqDTO();
                payinfo.setOrderid(orderId);
                payinfo.setZfbj("1");
                payInfoMapper.updateStatus(payinfo);
                PayReqDTO payReqDTO = new PayReqDTO();
                payReqDTO.setOrderId(orderId);
                payReqDTO.setZfbj("1");
                payMapper.updateStatus(payReqDTO);
            }
        });
        LOGGER.info("订单号查询状态结束了");
        return DataReuslt.success("修改成功");
    }
}
