package com.egintra.feeService.reportForm.service.impl;

import com.egintra.common.dto.reportForm.ConsumeMaterialRespDTO;
import com.egintra.common.dto.reportForm.QueryReportReqDTO;
import com.egintra.common.repository.mapper.ReportMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.reportForm.service.ConsumeMaterialDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 材料消耗明细报表实现类
 *
 * @author liushihao
 * @date 2021/11/10
 */
@Service
public class ConsumeMaterialDetailServiceImpl implements ConsumeMaterialDetailService {

    @Resource
    private ReportMapper reportMapper;

    /**
     * 查询材料消耗明细报表信息
     *
     * @param reqDTO 请求对象
     * @return 结果
     */
    @Override
    public DataReuslt queryConsumeMaterial(QueryReportReqDTO reqDTO) {
        // 先查询本期期止材料消耗明细报表信息
        List<ConsumeMaterialRespDTO> curConsumeMaterialInfos = reportMapper.queryCurConsumeMaterialInfos(reqDTO);

        // 再查询累计期止材料消耗明细报表信息
        QueryReportReqDTO cuReqDTO = new QueryReportReqDTO();
        BeanUtils.copyProperties(reqDTO, cuReqDTO);
        cuReqDTO.setLjStartTime(reqDTO.getBqStartTime());
        cuReqDTO.setLjEndTime(reqDTO.getBqEndTime());
        List<ConsumeMaterialRespDTO> cuConsumeMaterialInfos = reportMapper.queryCuConsumeMaterialInfos(cuReqDTO);
        Map<String, ConsumeMaterialRespDTO> cuMap = cuConsumeMaterialInfos.stream().collect(
                Collectors.toMap(v -> v.getMaterialId(), v -> v));

        if (!CollectionUtils.isEmpty(curConsumeMaterialInfos)) {
            // 将累计期止查询结果补充进本期查询期止结果内，构成报表数据集
            for (ConsumeMaterialRespDTO dto : curConsumeMaterialInfos) {
                dto.setCuNum(cuMap.get(dto.getMaterialId()).getCuNum());
                dto.setCuJe(cuMap.get(dto.getMaterialId()).getCuJe());
            }

            // 构造“总计”行
            // 本期数量“总计”
            int curNumTotal = curConsumeMaterialInfos.stream().map(ConsumeMaterialRespDTO::getCurNum)
                    .mapToInt(Integer::parseInt).sum();
            // 本期金额“总计”
            BigDecimal curJeTotal = curConsumeMaterialInfos.stream().map(ConsumeMaterialRespDTO::getCurJe)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            // 累计数量“总计”
            int cuNumTotal = curConsumeMaterialInfos.stream().map(ConsumeMaterialRespDTO::getCuNum)
                    .mapToInt(Integer::parseInt).sum();
            // 累计金额“总计”
            BigDecimal cuJeTotal = curConsumeMaterialInfos.stream().map(ConsumeMaterialRespDTO::getCuJe)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            ConsumeMaterialRespDTO addTotalDto = new ConsumeMaterialRespDTO();
            addTotalDto.setMaterialId("总计");
            addTotalDto.setCurNum(String.valueOf(curNumTotal));
            addTotalDto.setCurJe(curJeTotal);
            addTotalDto.setCuNum(String.valueOf(cuNumTotal));
            addTotalDto.setCuJe(cuJeTotal);

            curConsumeMaterialInfos.add(addTotalDto);
        }

        // 返回结果
        return DataReuslt.success(curConsumeMaterialInfos);
    }
}
