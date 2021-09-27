package edu.monash.dao;

import edu.monash.entity.TestRunner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRunnerDAO {

    int insert(TestRunner testRunner);

    List<TestRunner> selectListByDeviceId(String deviceId);

    TestRunner selectById(int id);

    List<TestRunner> selectListByDeviceIdAndDispatchStrategy(@Param("deviceId")String deviceId,
                                                             @Param("dispatchStrategy")int dispatchStrategy);
}
