package edu.monash.dao;

import edu.monash.entity.DeviceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoDAO {

    DeviceInfo selectById(int deviceId);

    int insert(DeviceInfo deviceInfo);

    List<DeviceInfo> selectListByGroup();

    DeviceInfo selectBySdkBrandDeviceName(String sdkVersion, String releaseVersion, String deviceModel, String brand);

}
