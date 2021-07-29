package edu.monash.webservice;

import edu.monash.entity.TestRunner;
import org.springframework.stereotype.Service;

@Service
public interface TestRunnerWebService {

    public TestRunner addRecord(String testCaseRecord, String deviceId);
}
