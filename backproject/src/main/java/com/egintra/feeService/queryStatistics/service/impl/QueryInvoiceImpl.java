package com.egintra.feeService.queryStatistics.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.DepartmentReqDTO;
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
public class QueryInvoiceImpl implements QueryInvoiceService {

    @Resource
    private InvoiceMapper invoiceMapper;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 查询发票列表
     *
     * @param invoiceReqDTO
     * @return
     */
    @Override
    public DataReuslt queryInvoce(InvoiceReqDTO invoiceReqDTO) {
        Page ps = PageHelper.startPage(invoiceReqDTO.getPage(), invoiceReqDTO.getSize());
        List<FeeInvoice> feeInvoiceList = invoiceMapper.getInvoiceList(invoiceReqDTO);
        feeInvoiceList.forEach(feeInvoice -> {
            feeInvoice.setChargeDate(DateUtils.formatDates(feeInvoice.getDates()));
        });
        PageResult<FeeInvoice> result = PageHelperUtils.getPagingData(feeInvoiceList, ps);
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

    /**
     * 查看发票详情
     *
     * @param feeInvoiceDetail
     * @return
     */
    @Override
    public DataReuslt getInvoiceDetailList(FeeInvoiceDetail feeInvoiceDetail) {
        List<FeeInvoiceDetail> list = invoiceMapper.getInvoiceDetailListById(feeInvoiceDetail.getFeeInvoiceId());
        return DataReuslt.success(list);
    }
}
