package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.InvoiceDetailTmpReqDTO;
import com.egintra.common.dto.pay.FeeInvoiceDetailTmpRespDTO;
import com.egintra.common.repository.entity.FeeInvoiceDetailTmp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 临时记账发票明细表mapper接口
 *
 * @author liushihao
 * @date 2021/8/11
 */
public interface InvoiceTmpDetailMapper extends BaseMapper<FeeInvoiceDetailTmp> {

    /**
     * 添加临时记账
     *
     * @param invoiceDetailTmp 详情
     * @return 结果
     */
   int addInvoiceTmpDetail(InvoiceDetailTmpReqDTO invoiceDetailTmp);

    /**
     * 根据临时记账发票主表ID查询临时记账发票明细表
     *
     * @param mainIds 临时记账发票主表ID集合
     * @return 查询结果
     */
    List<FeeInvoiceDetailTmpRespDTO> feeInvoiceDetailTmp(@Param("list") List<String> mainIds);

    /**
     * 根据orderId查询临时记账发票明细表
     *
     * @param orderid orderid
     * @return 查询结果
     */
    List<FeeInvoiceDetailTmpRespDTO> queryDetailTmpsByOrderId(@Param("orderid") String orderid);
}
