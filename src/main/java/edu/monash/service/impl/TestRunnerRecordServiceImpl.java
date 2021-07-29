package edu.monash.service.impl;

import edu.monash.dao.TestCaseDAO;
import edu.monash.dao.TestRunnerRecordDAO;
import edu.monash.entity.DeviceInfo;
import edu.monash.entity.TestRunnerRecord;
import edu.monash.service.TestRunnerRecordService;
import edu.monash.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestRunnerRecordServiceImpl implements TestRunnerRecordService {

    @Autowired
    private TestRunnerRecordDAO testRunnerRecordDAO;

    @Override
    public int insertTestRunnerRecord(TestRunnerRecord testRunnerRecord) {
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getTestCaseId(), "[DAO.insertTestRunnerRecord] testCaseId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getDeviceId(), "[DAO.insertTestRunnerRecord] deviceId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getSdkVersion(), "[DAO.insertTestRunnerRecord] sdkVersion shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getReleaseVersion(), "[DAO.insertTestRunnerRecord] releaseVersion shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getDeviceModel(), "[DAO.insertTestRunnerRecord] deviceModel shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getBrand(), "[DAO.insertTestRunnerRecord] brand shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getHost(), "[DAO.insertTestRunnerRecord] host shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getDeviceName(), "[DAO.insertTestRunnerRecord] deviceName shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getHardwareName(), "[DAO.insertTestRunnerRecord] hardwareName shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getLanguage(), "[DAO.insertTestRunnerRecord] language shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.getScreenSize(), "[DAO.insertTestRunnerRecord] screenSize shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(testRunnerRecord.isSuccess(), "[DAO.insertTestRunnerRecord] isSuccess shouldn't be null!");
        testRunnerRecordDAO.insert(testRunnerRecord);
        return testRunnerRecord.getId();
    }

}
