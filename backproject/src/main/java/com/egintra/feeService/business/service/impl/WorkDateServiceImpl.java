package com.egintra.feeService.business.service.impl;

import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.dto.RightUserDTO;
import com.egintra.common.dto.business.WorkDateReqDTO;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.AccountMapper;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.utils.DataReuslt;
import com.egintra.common.utils.DateUtils;
import com.egintra.feeService.business.service.WorkDateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WorkDateServiceImpl implements WorkDateService {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private LoginMapper loginMapper;

    /**
     * 获取当前工作时间
     *
     * @return
     */
    @Override
    public DataReuslt getWorkDate(WorkDateReqDTO workDateReqDTO) {
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(workDateReqDTO.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        if (rightUser != null) {
            AccountReqDTO accountReqDTO = accountMapper.getOne(rightUser.getAccountId());
            return DataReuslt.success(accountReqDTO.getWorkDate());
        }
        return DataReuslt.success();
    }

    /**
     * 修改当前工作日期
     *
     * @param workDateReqDTO
     * @return
     */
    @Override
    public DataReuslt updateWorkDate(WorkDateReqDTO workDateReqDTO) {
        String date = workDateReqDTO.getWorkDate();
        RightUserDTO rightUserDTO = new RightUserDTO();
        rightUserDTO.setId(workDateReqDTO.getUserId());
        RightUser rightUser = loginMapper.getUserById(rightUserDTO);
        if (rightUser != null) {
            AccountReqDTO account = accountMapper.getOne(rightUser.getAccountId());
            AccountReqDTO accountReqDTO = new AccountReqDTO();
            accountReqDTO.setAccountId(account.getAccountId());
            accountReqDTO.setWorkDate(DateUtils.parseDates(date));
            accountMapper.updateAccountById(accountReqDTO);
        }
        return DataReuslt.success("修改成功");
    }
}
