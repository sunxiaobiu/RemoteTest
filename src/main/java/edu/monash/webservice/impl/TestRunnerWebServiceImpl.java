package edu.monash.webservice.impl;

import com.alibaba.fastjson.JSONObject;
import edu.monash.entity.TestRunner;
import edu.monash.service.TestRunnerService;
import edu.monash.util.Regex;
import edu.monash.webservice.TestRunnerWebService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestRunnerWebServiceImpl implements TestRunnerWebService {
    private static final Logger logger = LoggerFactory.getLogger(TestRunnerWebServiceImpl.class);

    @Autowired
    private TestRunnerService testRunnerService;

    @Override
    public TestRunner addRecord(String testCaseRecord, String deviceId) {
        JSONObject testRunnerJson = (JSONObject) JSONObject.parse(testCaseRecord);
        TestRunner testRunner = TestRunner.convert2TestRunner(testRunnerJson);
        testRunner.setDeviceId(deviceId);

        int testRunnerId = testRunnerService.insertTestRunner(testRunner);
        return testRunnerService.selectById(testRunnerId);
    }

    @Override
    public List<String> getExecutedTestsByDeviceId(String deviceId) {
        List<TestRunner> executedTestCases = testRunnerService.selectListByDeviceId(deviceId);

        List<String> testCaseIdList = new ArrayList<>();
        if (CollectionUtils.isEmpty(executedTestCases)) {
            return testCaseIdList;
        }

        for (TestRunner testRunner : executedTestCases) {
            testCaseIdList.add(Regex.getSubUtilSimple(testRunner.getTestCaseId(), "(.*?\\.)"));
        }
        return testCaseIdList;
    }

}
