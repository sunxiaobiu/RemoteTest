package edu.monash.dao;

import edu.monash.entity.DeviceGroup;
import edu.monash.entity.DeviceInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoDAO {

    DeviceInfo selectById(@Param("deviceId")String deviceId);

    int insert(DeviceInfo deviceInfo);

    List<DeviceGroup> selectListByGroup();

    List<DeviceInfo> selectBySdkBrandDeviceName(@Param("sdkVersion")String sdkVersion,
                                                @Param("releaseVersion")String releaseVersion,
                                                @Param("deviceModel")String deviceModel,
                                                @Param("brand")String brand);

    int update(DeviceInfo deviceInfo);

}
