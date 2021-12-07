package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.reportForm.ConsumeMaterialRespDTO;
import com.egintra.common.dto.reportForm.NetIncomeReportRespDTO;
import com.egintra.common.dto.reportForm.PayTypeReqDTO;
import com.egintra.common.dto.reportForm.QueryReportReqDTO;
import com.egintra.common.dto.reportForm.ReportReqDTO;
import com.egintra.common.repository.entity.FeeInvoice;

import java.util.List;

/**
 * 报表Mapper
 *
 * @author liushihao
 * @date 2021/9/8
 */
public interface ReportMapper extends BaseMapper<FeeInvoice> {

    /**
     * 报表信息查询
     *
     * @param reportReqDTO
     * @return
     */
    List<ReportReqDTO> queryReport(ReportReqDTO reportReqDTO);

    /**
     * 获取支付方式列表
     *
     * @param reportReqDTO
     * @return
     */
    List<PayTypeReqDTO> getPayTypeList(ReportReqDTO reportReqDTO);

    /**
     * 净收入报表信息查询
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    List<NetIncomeReportRespDTO> queryNetIncomReportInfo(QueryReportReqDTO reqDTO);

    /**
     * 净收入报表信息获取支付方式金额
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    List<PayTypeReqDTO> getNetIncomeAllPayWayMoneys(QueryReportReqDTO reqDTO);

    /**
     * 本期材料消耗明细表信息查询
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    List<ConsumeMaterialRespDTO> queryCurConsumeMaterialInfos(QueryReportReqDTO reqDTO);

    /**
     * 累计材料消耗明细表信息查询
     *
     * @param reqDTO 查询条件
     * @return 查询结果
     */
    List<ConsumeMaterialRespDTO> queryCuConsumeMaterialInfos(QueryReportReqDTO reqDTO);
	
    /**
     * 报表信息查询(省/市)
     *
     * @param reportReqDTO
     * @return
     */
    List<ReportReqDTO> queryPCReport(ReportReqDTO reportReqDTO);


    /**
     * 获取支付方式列表（省/市）
     *
     * @param reportReqDTO
     * @return
     */
    List<PayTypeReqDTO> getPayPCTypeList(ReportReqDTO reportReqDTO);
}
