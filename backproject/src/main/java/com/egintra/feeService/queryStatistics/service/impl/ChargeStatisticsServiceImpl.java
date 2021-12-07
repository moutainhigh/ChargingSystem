package com.egintra.feeService.queryStatistics.service.impl;

import com.egintra.common.dto.pay.FeeInvoiceDetailRespDTO;
import com.egintra.common.dto.queryStatistics.QueryStatisticsReqDTO;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.FeeInvoiceDetailMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.common.utils.StringUtil;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.queryStatistics.service.ChargeStatisticsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

/**
 * 查询统计-收费统计实现
 *
 * @author liushihao
 * @date 2021/9/16
 */
@Service
public class ChargeStatisticsServiceImpl implements ChargeStatisticsService {

    @Resource
    private FeeInvoiceDetailMapper feeInvoiceDetailMapper;

    /**
     * 收费统计-查询
     *
     * @param queryReqDTO 查询条件
     * @return 结果
     */
    @Override
    public DataReuslt queryStatistics(QueryStatisticsReqDTO queryReqDTO) {

        // 如果查询条件设置了时间范围，修改前端传入的"起止日期"
        if (!StringUtil.isEmpty(queryReqDTO.getStartDate())) {
            String startDate = MessageFormat.format("{0}{1}", queryReqDTO.getStartDate(), " 00:00:00");
            queryReqDTO.setStartDate(startDate);
        }
        if (!StringUtil.isEmpty(queryReqDTO.getEndDate())) {
            String endDate = MessageFormat.format("{0}{1}", queryReqDTO.getEndDate(), " 00:00:00");
            queryReqDTO.setEndDate(endDate);
        }

        // 查询收费统计
        Page page = PageHelper.startPage(queryReqDTO.getPage(), queryReqDTO.getSize());
        List<FeeInvoiceDetailRespDTO> statisticsList = feeInvoiceDetailMapper.queryChargeStatistics(queryReqDTO);
        // 分页
        PageResult<FeeInvoiceDetailRespDTO> pagingData = PageHelperUtils.getPagingData(statisticsList, page);

        // 返回结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 收费统计-查询
     *
     * @param queryReqDTO 查询条件
     * @return 结果
     */
    @Override
    public DataReuslt exportStatistics(QueryStatisticsReqDTO queryReqDTO) {

        // 查询收费统计
        List<FeeInvoiceDetailRespDTO> statisticsList = feeInvoiceDetailMapper.queryChargeStatistics(queryReqDTO);

        // 导出时添加“合计”列:查询有结果添加
        if (!CollectionUtils.isEmpty(statisticsList)) {
            FeeInvoiceDetailRespDTO detailRespDTO = new FeeInvoiceDetailRespDTO();
            detailRespDTO.setDepartment("合计");
            // 计算“总金额”
            BigDecimal totalMoney = statisticsList.stream().map(FeeInvoiceDetailRespDTO::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            detailRespDTO.setTotalAmount(totalMoney);
            // 计算总数量
            BigDecimal allQuantity = statisticsList.stream().map(FeeInvoiceDetailRespDTO::getAllQuantity)
                    .map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
            detailRespDTO.setAllQuantity(String.valueOf(allQuantity));

            statisticsList.add(detailRespDTO);
        }

        // 返回结果
        return DataReuslt.success(statisticsList);
    }
}
