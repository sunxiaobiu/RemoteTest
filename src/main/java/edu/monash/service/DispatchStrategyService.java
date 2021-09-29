package edu.monash.service;

import edu.monash.entity.DeviceInfo;
import edu.monash.entity.DispatchStrategy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface DispatchStrategyService {

    int insertDispatchStrategy(DispatchStrategy dispatchStrategy);

    DispatchStrategy selectByDeviceIdAndBatchSize(String deviceId, int batchSize);

    DispatchStrategy updateDispatchStrategy(DispatchStrategy dispatchStrategy);
}
