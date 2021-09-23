package edu.monash.webservice;

import edu.monash.entity.TestRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestRunnerWebService {

    public TestRunner addRecord(String testCaseRecord, String deviceId);

    public List<String> getExecutedTestsByDeviceId(String deviceId);

}
