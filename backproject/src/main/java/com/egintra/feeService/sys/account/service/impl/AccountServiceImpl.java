package com.egintra.feeService.sys.account.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.common.utils.PageHelperUtils;
import com.egintra.common.utils.StringUtil;
import com.egintra.feeService.config.PageResult;
import com.egintra.feeService.sys.account.service.AccountService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 查看 列表 （分页）
     *
     * @param accountReqDTO
     * @return
     */
    @Override
    public DataReuslt getAccountList(AccountReqDTO accountReqDTO) {
        Page ps = PageHelper.startPage(accountReqDTO.getPage(), accountReqDTO.getSize());
        List<AccountReqDTO> accountList = accountMapper.getAccountList(accountReqDTO);
        PageResult<AccountReqDTO> result = PageHelperUtils.getPagingData(accountList, ps);

        return DataReuslt.success(result);
    }

    /**
     * 添加单位
     *
     * @param accountReqDTO
     * @return
     */
    @Override
    @Transactional
    public DataReuslt addAccount(AccountReqDTO accountReqDTO) {
        AccountReqDTO account = accountMapper.getOne(accountReqDTO.getAccountId());
        if (account != null) {
            return DataReuslt.fail("1", "账套号已经存在");
        }
        Date date = DateUtils.getCurDateString();
        accountReqDTO.setWorkDate(date);
        accountMapper.addAccount(accountReqDTO);

        return DataReuslt.success(accountReqDTO);
    }

    /**
     * 编辑账套
     *
     * @param dictReqDTOs
     * @return
     */
    @Override
    @Transactional
    public DataReuslt editAccount(List<AccountReqDTO> dictReqDTOs) {
        accountMapper.editAccount(dictReqDTOs);

        return DataReuslt.success(dictReqDTOs);
    }

    /**
     * 删除账套
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt deleteById(String id) {
        accountMapper.deleteById(id);

        return DataReuslt.success("删除成功");
    }

    /**
     * 查看账套详情
     *
     * @param id
     * @return
     */
    @Override
    public DataReuslt getOne(String id) {
        AccountReqDTO accountReqDTO = accountMapper.getOne(id);
        return DataReuslt.success(accountReqDTO);
    }

    /**
     * 根据单位ID查询账套信息
     *
     * @param accountReqDTO 单位信息
     * @return 查询结果
     */
    @Override
    public DataReuslt queryAccountsByDepart(AccountReqDTO accountReqDTO) {

        // 如果参数为空报错
        if (StringUtil.isEmpty(accountReqDTO.getDepartmentId())) {
            return DataReuslt.fail("", "");
        }

        // 根据单位ID查询账套信息
        String departmentId = accountReqDTO.getDepartmentId();
        List<AccountReqDTO> accounts = accountMapper.getAccountListByDeptId(departmentId);

        // 返回信息
        return DataReuslt.success(accounts);
    }
}

