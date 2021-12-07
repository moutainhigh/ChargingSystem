package com.egintra.feeService.job.pay.service.impl;

import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.job.pay.service.StatementDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 银行对账单下载定时任务
 *
 * @author liushihao
 * @date 2021/8/11
 */
@Service
public class StatementDownloadServiceImpl implements StatementDownloadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementDownloadServiceImpl.class);

    /**
     * 银行对账单下载
     *
     * @return 结果
     */
    public DataReuslt statementDownload(){

        LOGGER.info("银行对账单下载任务开始");

        LOGGER.info("print2");
        LOGGER.info(String.valueOf(System.currentTimeMillis()));

        LOGGER.info("银行对账单下载任务结束");

        return DataReuslt.success();
    }
}
