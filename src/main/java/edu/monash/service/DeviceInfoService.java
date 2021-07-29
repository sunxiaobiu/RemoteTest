package edu.monash.service;

import edu.monash.entity.DeviceGroup;
import edu.monash.entity.DeviceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceInfoService {

    DeviceInfo findDeviceInfoById(String deviceId);

    int insertDeviceInfo(DeviceInfo deviceInfo);

    List<DeviceGroup> selectListByGroup();

    List<DeviceInfo> selectBySdkBrandDeviceName(String sdkVersion, String releaseVersion, String deviceModel, String brand);

    DeviceInfo updateDeviceInfo(DeviceInfo deviceInfo);
}
