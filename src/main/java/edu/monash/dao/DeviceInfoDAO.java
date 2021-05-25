package edu.monash.dao;

import edu.monash.entity.DeviceInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInfoDAO {

    DeviceInfo selectById(int deviceId);

    int insert(DeviceInfo deviceInfo);

}
