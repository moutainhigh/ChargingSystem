package com.egintra.common.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.egintra.common.dto.VehicleReqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VehicleMapper extends BaseMapper<VehicleReqDTO> {

    /**
     * 查看列表
     *
     * @return 查询结果
     */
    List<VehicleReqDTO> getVehicleList(VehicleReqDTO vehicleReqDTO);


    /**
     * 添加
     *
     * @param vehjkReqDTOS
     */
    void addVehicle(@Param("list") List<VehicleReqDTO> vehjkReqDTOS);


    /**
     * 详情
     *
     * @param vehicleReqDTO
     * @return
     */
    VehicleReqDTO getOne(VehicleReqDTO vehicleReqDTO);

    /**
     * 名称是否重复
     *
     * @param name
     * @return
     */
    VehicleReqDTO findByName(String name);


    /**
     * 编辑
     *
     * @param vehicleReqDTs
     */
    void editVehicleReqDTO(@Param("list") List<VehicleReqDTO> vehicleReqDTs);


    /**
     * 删除
     *
     * @param vehicleReqDTO
     */
    void deleteById(VehicleReqDTO vehicleReqDTO);
}
