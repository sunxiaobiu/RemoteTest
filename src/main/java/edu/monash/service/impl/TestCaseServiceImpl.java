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
        ExceptionUtil.runtimeExpWithNullCheck(size, "[DAO.selectList] deviceId shouldn't be null!");
        return testCaseDAO.selectList(size);
    }
}
