package com.egintra.feeService.reportForm.service.impl;

import com.egintra.common.dto.PubSimplenumberRespDTO;
import com.egintra.common.dto.reportForm.NetIncomeReportRespDTO;
import com.egintra.common.dto.reportForm.PayTypeReqDTO;
import com.egintra.common.dto.reportForm.QueryReportReqDTO;
import com.egintra.common.repository.mapper.ReportMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.reportForm.service.NetIncomeReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 净收入报表实现类
 *
 * @author liushihao
 * @date 2021/11/10
 */
@Service
public class NetIncomeReportServiceImpl implements NetIncomeReportService {

    @Resource
    private ReportMapper reportMapper;

    /**
     * 查询报表信息
     *
     * @param reqDTO 请求对象
     * @return 结果
     */
    @Override
    public DataReuslt queryReportInfo(QueryReportReqDTO reqDTO) {

        // 净收入报表信息查询：查询各项目净收入信息
        List<NetIncomeReportRespDTO> netIncomeReportInfos = reportMapper.queryNetIncomReportInfo(reqDTO);
        if (CollectionUtils.isEmpty(netIncomeReportInfos)) {
            // 如果查询结果为空直接返回空数据集
            return DataReuslt.success(netIncomeReportInfos);
        }

        // 构造“总计”行
        // 计算本期收入-省财政“总计”
        BigDecimal curIncomeProFin = netIncomeReportInfos.stream().map(NetIncomeReportRespDTO::getCurIncomeProFin)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算本期收入-市地财政“总计”
        BigDecimal curIncomeCityFin = netIncomeReportInfos.stream().map(NetIncomeReportRespDTO::getCurIncomeCityFin)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算本期收入小计“总计”
        BigDecimal curTotal = netIncomeReportInfos.stream().map(NetIncomeReportRespDTO::getCurIncomeSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算累计收入-省财政“总计”
        BigDecimal cuIncomeProFin = netIncomeReportInfos.stream().map(NetIncomeReportRespDTO::getCuIncomeProFin)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算累计收入-市地财政“总计”
        BigDecimal cuIncomeCityFin = netIncomeReportInfos.stream().map(NetIncomeReportRespDTO::getCuIncomeCityFin)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 计算累计收入小计“总计”
        BigDecimal cuTotal = netIncomeReportInfos.stream().map(NetIncomeReportRespDTO::getCuIncomeSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        NetIncomeReportRespDTO addTotalDto = new NetIncomeReportRespDTO();
        addTotalDto.setItemName("总计");
        addTotalDto.setCurIncomeProFin(curIncomeProFin);
        addTotalDto.setCurIncomeCityFin(curIncomeCityFin);
        addTotalDto.setCurIncomeSubtotal(curTotal);
        addTotalDto.setCuIncomeProFin(cuIncomeProFin);
        addTotalDto.setCuIncomeCityFin(cuIncomeCityFin);
        addTotalDto.setCuIncomeSubtotal(cuTotal);
        netIncomeReportInfos.add(addTotalDto);

        // 查询本期期止各支付方式金额信息：作为补充报表信息
        List<PayTypeReqDTO> curAllPayWayMoneyList = reportMapper.getNetIncomeAllPayWayMoneys(reqDTO);

        // 查询累积期止各支付方式金额信息：作为补充报表信息
        QueryReportReqDTO cuReqDTO = new QueryReportReqDTO();
        BeanUtils.copyProperties(reqDTO, cuReqDTO);
        cuReqDTO.setLjStartTime(reqDTO.getBqStartTime());
        cuReqDTO.setLjEndTime(reqDTO.getBqEndTime());
        List<PayTypeReqDTO> cuAllPayWayMoneyList = reportMapper.getNetIncomeAllPayWayMoneys(cuReqDTO);
        Map<String, String> cuMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(cuAllPayWayMoneyList)) {
            cuMap = cuAllPayWayMoneyList.stream().collect(
                    Collectors.toMap(PayTypeReqDTO::getName, PayTypeReqDTO::getNvl));
        }

        // 补充报表
        NetIncomeReportRespDTO addDto;
        for (PayTypeReqDTO dto : curAllPayWayMoneyList) {
            addDto = new NetIncomeReportRespDTO();
            // 项目名称（金额方式）
            addDto.setItemName(dto.getName());
            // 本期金额
            addDto.setCurIncomeSubtotal(new BigDecimal(dto.getNvl()));
            // 如果存在累计金额，设置累计金额
            if (cuMap.size() > 0 && cuMap.containsKey(dto.getName())) {
                addDto.setCuIncomeSubtotal(new BigDecimal(cuMap.get(dto.getName())));
            }

            // 各金额方式补充
            netIncomeReportInfos.add(addDto);
        }

        // 返回结果
        return DataReuslt.success(netIncomeReportInfos);
    }
}
