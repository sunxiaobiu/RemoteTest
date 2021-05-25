package edu.monash.service;

import edu.monash.entity.DeviceInfo;
import org.springframework.stereotype.Service;

@Service
public interface DeviceInfoService {

    DeviceInfo findDeviceInfoById(int deviceId);

    int insertDeviceInfo(DeviceInfo deviceInfo);
}
