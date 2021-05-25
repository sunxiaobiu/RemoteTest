package edu.monash.service.impl;

import edu.monash.dao.DeviceInfoDAO;
import edu.monash.service.DeviceInfoService;
import edu.monash.entity.DeviceInfo;
import edu.monash.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeviceInfoServiceImpl implements DeviceInfoService {
    @Autowired
    private DeviceInfoDAO deviceInfoDAO;

    @Override
    public DeviceInfo findDeviceInfoById(int deviceId) {
        ExceptionUtil.runtimeExpWithNullCheck(deviceId, "[DAO.findDeviceInfoById] deviceId shouldn't be null!");
        return deviceInfoDAO.selectById(deviceId);
    }

    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo) {
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getDeviceId(), "[DAO.insertDeviceInfo] deviceId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getSdkVersion(), "[DAO.insertDeviceInfo] sdkVersion shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getReleaseVersion(), "[DAO.insertDeviceInfo] releaseVersion shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getDeviceModel(), "[DAO.insertDeviceInfo] deviceModel shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getBrand(), "[DAO.insertDeviceInfo] brand shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getHost(), "[DAO.insertDeviceInfo] host shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getDeviceName(), "[DAO.insertDeviceInfo] deviceName shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getHardwareName(), "[DAO.insertDeviceInfo] hardwareName shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getLanguage(), "[DAO.insertDeviceInfo] language shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(deviceInfo.getScreenSize(), "[DAO.insertDeviceInfo] screenSize shouldn't be null!");
        return deviceInfoDAO.insert(deviceInfo);
    }


}
