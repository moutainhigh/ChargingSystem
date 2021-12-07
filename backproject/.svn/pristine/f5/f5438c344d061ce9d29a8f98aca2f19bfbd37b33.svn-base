package com.egintra.feeService.job.pay.task;

import com.egintra.feeService.job.pay.service.OrderUnlockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单号状态查询模块
 *
 * @author zyt
 * @date 2021/8/11
 */
@Service
public class OrderUnlockTask {


    @Resource
    private OrderUnlockService orderUnlockService;

    /**
     * 订单号状态查询状态
     *
     * @return 结果
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void bookingUnlockExecute() {

        // 订单号状态查询
        orderUnlockService.orderUnlock();
    }
}
