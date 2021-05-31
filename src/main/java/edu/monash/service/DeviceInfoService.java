package edu.monash.service;

import edu.monash.entity.DeviceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceInfoService {

    DeviceInfo findDeviceInfoById(int deviceId);

    int insertDeviceInfo(DeviceInfo deviceInfo);

    List<DeviceInfo> selectListByGroup();

    DeviceInfo selectBySdkBrandDeviceName(String sdkVersion, String releaseVersion, String deviceModel, String brand);

}
