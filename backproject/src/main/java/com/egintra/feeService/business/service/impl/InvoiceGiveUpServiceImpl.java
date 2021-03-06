package com.egintra.feeService.business.service.impl;

import com.egintra.common.dto.DepartmentReqDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpQueryReqDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpQueryRespDTO;
import com.egintra.common.dto.business.FeeInvoiceGiveUpReqDTO;
import com.egintra.common.enums.MarkEnum;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.repository.entity.FeeInvoiceGiveUp;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.FeeInvoiceDetailMapper;
import com.egintra.common.repository.mapper.FeeInvoiceGiveUpMapper;
import com.egintra.common.repository.mapper.InvoiceMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.common.utils.bank.BankAutographUtils;
import com.egintra.feeService.business.service.InvoiceGiveUpService;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.department.service.DepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 发票作废实现类
 *
 * @author liushihao
 * @date 2021/9/8
 */
@Service
public class InvoiceGiveUpServiceImpl implements InvoiceGiveUpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceGiveUpServiceImpl.class);

    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private FeeInvoiceDetailMapper feeInvoiceDetailMapper;
    @Resource
    private FeeInvoiceGiveUpMapper feeInvoiceGiveUpMapper;
    @Resource
    private DepartmentService departmentService;

    /**
     * 发票查询
     *
     * @param feeInvoiceGiveUpQueryReqDTO 参数
     * @return 结果
     */
    @Override
    public DataReuslt queryInvoices(FeeInvoiceGiveUpQueryReqDTO feeInvoiceGiveUpQueryReqDTO) {
        // 初始化分页page
        Page page = PageHelper.startPage(feeInvoiceGiveUpQueryReqDTO.getPage(), feeInvoiceGiveUpQueryReqDTO.getSize());

        // 查询发票信息
        List<FeeInvoiceGiveUpQueryRespDTO> feeInvoiceList = invoiceMapper.queryInvoiceList(feeInvoiceGiveUpQueryReqDTO);

        // 查询单位信息，对发票查询结果中的单位ID转换为单位名称
        DepartmentReqDTO department = new DepartmentReqDTO();
        DataReuslt departmentLists = departmentService.getDepartmentLists(department);
        // 如果单位信息及发票信息都不为空的情况下转换
        if (!CollectionUtils.isEmpty((List<DepartmentReqDTO>) departmentLists.getData())
                && !CollectionUtils.isEmpty(feeInvoiceList)) {
            // 单位信息List集合
            List<DepartmentReqDTO> departmentList = (List<DepartmentReqDTO>) departmentLists.getData();
            // 单位信息Map<id,name>集合
            Map<String, String> departmentMap = departmentList.stream().collect(
                    Collectors.toMap(DepartmentReqDTO::getUnitRegion, DepartmentReqDTO::getUnitName));
            // 循环发票信息，判断匹配则替换
            for (FeeInvoiceGiveUpQueryRespDTO dto : feeInvoiceList) {
                if (departmentMap.containsKey(dto.getDepartment())) {
                    dto.setDepartment(departmentMap.get(dto.getDepartment()));
                }
            }
        }

        // 分页
        PageResult<FeeInvoiceGiveUpQueryRespDTO> pagingData = PageHelperUtils.getPagingData(feeInvoiceList, page);

        // 返回查询结果
        return DataReuslt.success(pagingData);
    }

    /**
     * 发票作废
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @Override
    @Transactional
    public DataReuslt voidInvoices(FeeInvoiceGiveUpReqDTO reqDTO) {

        // 现根据发票ID查询发票主表信息；确认发票信息，不存在报错提示
        FeeInvoiceGiveUpQueryRespDTO feeInvoice = invoiceMapper.queryInvoiceById(reqDTO);
        if (null == feeInvoice) {
            // 作废时未查询到发票信息，作废失败
            return DataReuslt.fail("作废失败");
        }

        // 创建发票作废保存数据
        FeeInvoiceGiveUp feeInvoiceGiveUp = new FeeInvoiceGiveUp();
        BeanUtils.copyProperties(feeInvoice, feeInvoiceGiveUp);
        // 设置其他保存参数
        feeInvoiceGiveUp.setTotalMoney(new BigDecimal(feeInvoice.getTotalMoney()));
        String ip = "";
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            ip = ip4.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        feeInvoiceGiveUp.setIp(ip);
        feeInvoiceGiveUp.setCause(reqDTO.getCause());
        feeInvoiceGiveUp.setGiveupdates(DateUtils.getCurrentDate());
        feeInvoiceGiveUp.setUserid(UserContext.getUserCode());
        feeInvoiceGiveUp.setUsername(UserContext.getUserName());
        feeInvoiceGiveUp.setPaperId("");

        // 更新发票主表和明细表状态为“已作废”
        int updateResult = invoiceMapper.updateStatusById(reqDTO.getId(), MarkEnum.NO.getCode());
        LOGGER.info("作废发票时更新发票主表数据{}条", updateResult);
        int updateDetailResult = feeInvoiceDetailMapper.updateStatusById(reqDTO.getId(), MarkEnum.NO.getCode());
        LOGGER.info("作废发票时更新发票明细表数据{}条", updateDetailResult);

        // 保存发票作废信息到发票作废表
        feeInvoiceGiveUpMapper.insertInvoiceGiveUpData(feeInvoiceGiveUp);

        // 返回结果
        return DataReuslt.success("作废成功");
    }
}
