package com.egintra.common.repository.mapper;

import com.egintra.common.dto.business.UploadinfoReqDTO;

public interface UploadinfoMapper {

    /**
     * 添加日志记录信息
     *
     * @param uploadinfoReqDTO
     */
    void saveUploadinfo(UploadinfoReqDTO uploadinfoReqDTO);
}
