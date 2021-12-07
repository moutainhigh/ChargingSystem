package com.egintra.feeService.sys.account.controller;

import com.egintra.common.annotation.ResponseResult;
import com.egintra.common.dto.AccountReqDTO;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.utils.DataReuslt;
import com.egintra.feeService.sys.account.service.AccountService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账套设置接口
 *
 * @author zyt
 * @date 2021/08/04
 */
@RestController
@RequestMapping("/account")
@ResponseResult
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 分页 查看列表
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getAccountList")
    public DataReuslt getAccountList(@RequestBody AccountReqDTO accountReqDTO) {

        return accountService.getAccountList(accountReqDTO);
    }

    /**
     * 新增账套
     *
     * @return 结果
     */
    @RequestMapping(value = "/addAccount")
    public DataReuslt addDict(@RequestBody AccountReqDTO accountReqDTO) {

        return accountService.addAccount(accountReqDTO);
    }

    /**
     * 编辑账套
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/editAccount")
    public DataReuslt editDict(@RequestBody List<AccountReqDTO> accountReqDTOS) {
        return accountService.editAccount(accountReqDTOS);
    }

    /**
     * 查看账套详情
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/getOne")
    public DataReuslt getOne(@RequestBody AccountReqDTO accountReqDTO) {

        return accountService.getOne(accountReqDTO.getAccountId());
    }

    /**
     * 删除账套
     *
     * @return 结果
     */
    @SysToken
    @RequestMapping(value = "/deleteById")
    public DataReuslt deleteById(@RequestBody AccountReqDTO accountReqDTO) {

        return accountService.deleteById(accountReqDTO.getAccountId());
    }

    /**
     * 根据单位ID查询账套信息
     *
     * @param accountReqDTO 单位ID
     * @return 查询结果
     */
    @SysToken
    @RequestMapping(value = "/queryAccountsByDepart")
    public DataReuslt queryAccountsByDepart(@RequestBody AccountReqDTO accountReqDTO) {

        return accountService.queryAccountsByDepart(accountReqDTO);
    }
}
