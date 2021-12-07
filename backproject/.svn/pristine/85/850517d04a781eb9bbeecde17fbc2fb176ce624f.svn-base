package com.egintra.feeService.queryStatistics.service.impl;

import com.egintra.common.dto.InvoiceDetailReqDTO;
import com.egintra.common.dto.InvoiceReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.repository.entity.FeeInvoice;
import com.egintra.common.repository.entity.FeeInvoiceDetail;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.DepartmentMapper;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.queryStatistics.service.QueryInvoiceService;
import com.egintra.feeService.queryStatistics.service.SpecialBusinessService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 查询发票实现类
 *
 * @author zhangyunting
 * @date 2021/9/16
 */
@Service
public class SpecialBusinessImpl implements SpecialBusinessService {

    @Resource
    private InvoiceMapper invoiceMapper;

    @Resource
    private LoginMapper loginMapper;


    /**
     * 查询发票明细列表
     *
     * @param invoiceReqDTO
     * @return
     */
    @Override
    public DataReuslt queryInvoceDetailList(InvoiceDetailReqDTO invoiceReqDTO) {
        Page ps = PageHelper.startPage(invoiceReqDTO.getPage(), invoiceReqDTO.getSize());
        List<InvoiceDetailReqDTO> invoiceDetailReqDTOS = invoiceMapper.getFeeInvoiceDetailList(invoiceReqDTO);
        invoiceDetailReqDTOS.forEach(invoiceDetailReqDTO -> {
            invoiceDetailReqDTO.setChargeDate(DateUtils.formatDates(invoiceDetailReqDTO.getDates()));
        });
        PageResult<InvoiceDetailReqDTO> result = PageHelperUtils.getPagingData(invoiceDetailReqDTOS, ps);
        return DataReuslt.success(result);
    }

    /**
     * 获取当前用户的等级
     *
     * @return
     */
    @Override
    public DataReuslt getUserLevel() {
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(UserContext.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        return DataReuslt.success(rightUser);
    }

}
