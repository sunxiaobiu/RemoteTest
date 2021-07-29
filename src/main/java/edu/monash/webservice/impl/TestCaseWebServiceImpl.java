package edu.monash.webservice.impl;

import edu.monash.entity.DeviceGroup;
import edu.monash.entity.DeviceInfo;
import edu.monash.entity.TestCase;
import edu.monash.service.DeviceInfoService;
import edu.monash.service.TestCaseService;
import edu.monash.webservice.DeviceWebService;
import edu.monash.webservice.TestCaseWebService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestCaseWebServiceImpl implements TestCaseWebService {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseWebServiceImpl.class);

    @Autowired
    private DeviceWebService deviceWebService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private TestCaseService testCaseService;

    @Override
    public List<TestCase> getAvaliableTestCasesByDeviceId(String deviceId) {
        //obtain deviceIDs which contain teh same system properties with deviceId's
        DeviceInfo deviceInfo = deviceInfoService.findDeviceInfoById(deviceId);
        List<DeviceInfo> deviceGroup = deviceInfoService.selectBySdkBrandDeviceName(deviceInfo.getSdkVersion(), deviceInfo.getReleaseVersion(), deviceInfo.getDeviceModel(), deviceInfo.getBrand());
        List<String> deviceGroupIds = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(deviceGroup)){
            deviceGroupIds = deviceGroup.stream().map(deviceGroupItem->{
                return deviceGroupItem.getDeviceId();
            }).collect(Collectors.toList());
        }

        //obtain test cases which are not deleted and not executed on "deviceIDs"
        List<TestCase> testCaseList = testCaseService.selectAvaliableTestCasesByDeviceId(deviceGroupIds);
        return testCaseList;
    }

    @Override
    public List<TestCase> dispatchTestCases(List<TestCase> avaliabletestCaseList, DeviceInfo deviceInfo) {
        List<TestCase> dispatchTestCases = new ArrayList<>();
        HashMap<DeviceGroup, List<DeviceInfo>> deviceGroupListHashMap =  deviceWebService.getDistinctDeviceGroups();
        DeviceGroup thisDevice = new DeviceGroup(deviceInfo.getSdkVersion(), deviceInfo.getReleaseVersion(), deviceInfo.getDeviceModel(), deviceInfo.getBrand());
        List<DeviceInfo> avaliableDevices = deviceGroupListHashMap.get(thisDevice);
        int indexOfDevice = avaliableDevices.indexOf(deviceInfo);
        for(int i = 1; i <= avaliabletestCaseList.size(); i++){
            if(i % (indexOfDevice + 1) == 0){
                dispatchTestCases.add(avaliabletestCaseList.get(i -1));
            }
        }
        return dispatchTestCases;
    }
}
