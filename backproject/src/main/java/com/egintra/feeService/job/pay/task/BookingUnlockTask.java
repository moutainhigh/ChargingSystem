package com.egintra.feeService.job.pay.task;

import com.egintra.feeService.job.pay.service.BookingUnlockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 支付业务中的记账、解锁业务服务
 *
 * @author liushihao
 * @date 2021/8/11
 */
@Service
public class BookingUnlockTask {

    @Resource
    private BookingUnlockService bookingUnlockService;

    /**
     * 记账、解锁业务服务
     *
     * @return 结果
     */
    @Scheduled(cron = "0 0 6/1 * * ?")
    public void bookingUnlockExecute() {

        // 执行记账、解锁
        bookingUnlockService.bookingUnlock();
    }
}
