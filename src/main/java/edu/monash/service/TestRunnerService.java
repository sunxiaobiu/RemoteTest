package edu.monash.service;

import edu.monash.entity.TestRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestRunnerService {

    int insertTestRunner(TestRunner testRunner);

    List<TestRunner> selectListByDeviceId(String deviceId);

    TestRunner selectById(int id);

}
