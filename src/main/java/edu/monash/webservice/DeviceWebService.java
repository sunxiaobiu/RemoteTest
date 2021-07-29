package edu.monash.webservice;

import edu.monash.entity.DeviceGroup;
import edu.monash.entity.DeviceInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface DeviceWebService {

    public HashMap<DeviceGroup, List<DeviceInfo>> getDistinctDeviceGroups();

    public DeviceInfo addDevice(String deviceInfoRequest);

}
