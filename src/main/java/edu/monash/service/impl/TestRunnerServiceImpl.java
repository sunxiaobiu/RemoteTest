package edu.monash.service.impl;

import edu.monash.dao.TestRunnerDAO;
import edu.monash.entity.TestRunner;
import edu.monash.service.TestRunnerService;
import edu.monash.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestRunnerServiceImpl implements TestRunnerService {

    @Autowired
    private TestRunnerDAO testRunnerDAO;

    @Override
    public int insertTestRunner(TestRunner testRunner) {
        ExceptionUtil.runtimeExpWithNullCheck(testRunner.getTestCaseId(), "[DAO.insertTestRunner] testCaseId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunner.getDeviceId(), "[DAO.insertTestRunner] deviceId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunner.isSuccess(), "[DAO.insertTestRunner] isSuccess shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunner.getResult(), "[DAO.insertTestRunner] result shouldn't be null!");
        return testRunnerDAO.insert(testRunner);
    }

    @Override
    public List<TestRunner> selectListByDeviceId(String deviceId) {
        ExceptionUtil.runtimeExpWithNullCheck(deviceId, "[DAO.selectListByDeviceId] deviceId shouldn't be null!");
        return testRunnerDAO.selectListByDeviceId(deviceId);
    }

    @Override
    public TestRunner selectById(int id) {
        ExceptionUtil.runtimeExpWithNullCheck(id, "[DAO.selectById] id shouldn't be null!");
        return testRunnerDAO.selectById(id);
    }
}
