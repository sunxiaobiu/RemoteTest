package edu.monash.dao;

import edu.monash.entity.DeviceInfo;
import edu.monash.entity.DispatchStrategy;
import edu.monash.entity.TestRunner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispatchStrategyDAO {

    int insert(DispatchStrategy dispatchStrategy);

    DispatchStrategy selectByDeviceIdAndBatchSize(@Param("deviceId")String deviceId,
                                                  @Param("batchSize")int batchSize);

    int update(DispatchStrategy dispatchStrategy);
}
