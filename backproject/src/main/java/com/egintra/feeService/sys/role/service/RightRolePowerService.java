package com.egintra.feeService.sys.role.service;

import com.egintra.common.dto.RightFuncrightReqDTO;
import com.egintra.common.utils.DataReuslt;

public interface RightRolePowerService {
    /**
     * 保存功能权限
     *
     * @param rightFuncrightReqDTO 请求对象
     * @return 保存结果
     */
    public DataReuslt saveRoleConfigure(RightFuncrightReqDTO rightFuncrightReqDTO);

    /**
     * 查询角色功能权限数据，返回功能ID集合
     *
     * @param rightFuncrightReqDTO 入参
     * @return 返回结果
     */
    public DataReuslt queryRolePowers(RightFuncrightReqDTO rightFuncrightReqDTO);
}
