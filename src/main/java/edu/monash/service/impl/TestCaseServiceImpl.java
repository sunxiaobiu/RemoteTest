package edu.monash.service.impl;

import edu.monash.dao.TestCaseDAO;
import edu.monash.entity.TestCase;
import edu.monash.service.TestCaseService;
import edu.monash.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseDAO testCaseDAO;

    @Override
    public TestCase findTestCaseByUniqueId(int uniqueId) {
        ExceptionUtil.runtimeExpWithNullCheck(uniqueId, "[DAO.findTestCaseByUniqueId] deviceId shouldn't be null!");
        return testCaseDAO.selectByUniqueId(uniqueId);
    }

    @Override
    public List<TestCase> selectList(int size) {
        ExceptionUtil.runtimeExpWithNullCheck(size, "[DAO.selectList] size shouldn't be null!");
        return testCaseDAO.selectList(size);
    }

    @Override
    public List<TestCase> selectAvaliableTestCasesByDeviceId(List<String> deviceIds) {
        ExceptionUtil.runtimeExpWithNullCheck(deviceIds, "[DAO.selectAvaliableTestCasesByDeviceId] deviceIds shouldn't be null!");
        return testCaseDAO.selectAvaliableTestCasesByDeviceId(deviceIds);
    }

    @Override
    public List<TestCase> selectFromStartToEnd(int startId, int endId) {
        ExceptionUtil.runtimeExpWithNullCheck(startId, "[DAO.selectFromStartToEnd] startId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(endId, "[DAO.selectFromStartToEnd] endId shouldn't be null!");
        return testCaseDAO.selectFromStartToEnd(startId, endId);
    }

}
