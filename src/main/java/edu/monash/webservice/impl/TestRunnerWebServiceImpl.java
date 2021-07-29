package edu.monash.webservice.impl;

import com.alibaba.fastjson.JSONObject;
import edu.monash.entity.TestRunner;
import edu.monash.service.TestRunnerService;
import edu.monash.webservice.TestRunnerWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestRunnerWebServiceImpl implements TestRunnerWebService {
    private static final Logger logger = LoggerFactory.getLogger(TestRunnerWebServiceImpl.class);

    @Autowired
    private TestRunnerService testRunnerService;

    @Override
    public TestRunner addRecord(String testCaseRecord, String deviceId) {
        JSONObject testRunnerJson = (JSONObject)JSONObject.parse(testCaseRecord);
        TestRunner testRunner = TestRunner.convert2TestRunner(testRunnerJson);
        testRunner.setDeviceId(deviceId);

        int testRunnerId = testRunnerService.insertTestRunner(testRunner);
        return testRunnerService.selectById(testRunnerId);
    }
}
