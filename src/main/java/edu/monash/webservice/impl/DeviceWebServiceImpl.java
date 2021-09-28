package edu.monash.webservice.impl;

import com.alibaba.fastjson.JSONObject;
import edu.monash.entity.DeviceGroup;
import edu.monash.entity.DeviceInfo;
import edu.monash.service.DeviceInfoService;
import edu.monash.webservice.DeviceWebService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;

@Component
public class DeviceWebServiceImpl implements DeviceWebService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceWebServiceImpl.class);

    @Autowired
    private DeviceInfoService deviceInfoService;

    public HashMap<DeviceGroup, List<DeviceInfo>> getDistinctDeviceGroups(){
        HashMap<DeviceGroup, List<DeviceInfo>> res = new HashMap<>();
        List<DeviceGroup> deviceGroups = deviceInfoService.selectListByGroup();
        if(CollectionUtils.isEmpty(deviceGroups)){
            logger.error("No avaliable device groups!");
        }

        for(DeviceGroup deviceGroup : deviceGroups){
            List<DeviceInfo> deviceInfoList = deviceInfoService.selectBySdkBrandDeviceName(deviceGroup.getSdkVersion(), deviceGroup.getReleaseVersion(), deviceGroup.getDeviceModel(), deviceGroup.getBrand());
            res.put(deviceGroup, deviceInfoList);
        }

        return res;
    }

    @Override
    public DeviceInfo addDevice(String deviceInfoRequest) {
        JSONObject deviceInfoJson = (JSONObject)JSONObject.parse(deviceInfoRequest);
        DeviceInfo deviceInfo = DeviceInfo.convert2DeviceInfo(deviceInfoJson);
        DeviceInfo existDevice = deviceInfoService.findDeviceInfoById(deviceInfo.getDeviceId());
        if(existDevice == null){
            deviceInfo.setId(deviceInfoService.insertDeviceInfo(deviceInfo));
            return deviceInfo;
        }else{
            if(!deviceInfo.equals(existDevice)){
                return deviceInfoService.updateDeviceInfo(deviceInfo);
            }
            return existDevice;
        }
    }

    @Override
    public DeviceInfo getDeviceById(String deviceId) {
        return deviceInfoService.findDeviceInfoById(deviceId);
    }

    @Override
    public void updateDispatchStrategy(String deviceInfoRequest) {
        JSONObject deviceInfoJson = (JSONObject)JSONObject.parse(deviceInfoRequest);
        DeviceInfo deviceInfo = DeviceInfo.convert2DeviceInfo(deviceInfoJson);
        deviceInfoService.updateDeviceInfo(deviceInfo);
    }


}
