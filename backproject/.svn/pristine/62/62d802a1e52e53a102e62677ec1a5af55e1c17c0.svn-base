package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.AccountReqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper extends BaseMapper<AccountReqDTO> {

    /**
     * 查看列表
     *
     * @return 查询结果
     */
    List<AccountReqDTO> getAccountList(AccountReqDTO accountReqDTO);

    /**
     * 添加
     *
     * @param accountReqDTO
     */
    void addAccount(AccountReqDTO accountReqDTO);

    /**
     * 编辑
     *
     * @param list
     */
    void editAccount(@Param("list") List<AccountReqDTO> list);

    /**
     * 修改账套时间
     *
     * @param accountReqDTO
     */
    void updateAccountById(AccountReqDTO accountReqDTO);

    /**
     * 修改虚拟发票号
     * @param accountReqDTO
     */
    void  updateParam(AccountReqDTO accountReqDTO);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    AccountReqDTO getOne(String id);

    /**
     * 查看名称是否存在
     *
     * @param accountReqDTO
     * @return
     */
    AccountReqDTO findByName(AccountReqDTO accountReqDTO);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据单位查询账套信息
     *
     * @param departmentId
     * @return
     */
    List<AccountReqDTO> getAccountListByDeptId(String departmentId);
}
