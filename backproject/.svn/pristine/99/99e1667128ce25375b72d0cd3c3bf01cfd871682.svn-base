package com.egintra.feeService.sys.account.service;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.utils.DataReuslt;

import java.util.List;

public interface AccountService {

    /**
     * 查看列表
     *
     * @return 结果
     */
    public DataReuslt getAccountList(AccountReqDTO accountReqDTO);

    /**
     * 添加
     *
     * @param accountReqDTO
     */
    public DataReuslt addAccount(AccountReqDTO accountReqDTO);

    /**
     * 编辑
     *
     * @param accountReqDTO
     */
    public DataReuslt editAccount(List<AccountReqDTO> accountReqDTO);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    public DataReuslt getOne(String id);

    /**
     * 删除
     *
     * @param id
     */
    public DataReuslt deleteById(String id);

    /**
     * 根据单位ID查询账套信息
     *
     * @param accountReqDTO 单位ID
     * @return 查询结果
     */
    public DataReuslt queryAccountsByDepart(AccountReqDTO accountReqDTO);
}
