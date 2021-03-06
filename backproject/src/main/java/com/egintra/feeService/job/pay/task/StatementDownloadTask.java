package com.egintra.feeService.job.pay.task;

import com.egintra.feeService.job.pay.service.StatementDownloadService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 银行对账单下载定时任务
 *
 * @author liushihao
 * @date 2021/8/11
 */
@Service
public class StatementDownloadTask {

    @Resource
    private StatementDownloadService statementDownloadService;

    /**
     * 银行对账单下载
     *
     * @return 结果
     */
    @Scheduled(cron = "*/300 * * * * ?")
    public void statementDownloadExecute() {

        // 执行记账、解锁
        statementDownloadService.statementDownload();
    }

}
