package edu.monash.webservice;

import edu.monash.entity.DeviceInfo;
import edu.monash.entity.DispatchStrategy;
import edu.monash.entity.TestCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestCaseWebService {

    public List<TestCase> getAvaliableTestCasesByDeviceId(String deviceId);

    public List<TestCase> dispatchTestCases(List<TestCase> avaliabletestCaseList, DeviceInfo deviceInfo);

    public List<TestCase> getNotYetExecutedTestCases(String deviceId, int dispatchStrategy);

    public List<TestCase> getTestsFromStartId2EndId(int startId, int endId);

    public List<String> getBatchTests(String deviceId, DispatchStrategy dispatchStrategy);

    public TestCase getTestCaseByName(String uniqueId);

}
