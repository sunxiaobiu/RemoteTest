package edu.monash.service;

import edu.monash.entity.TestRunnerRecord;
import org.springframework.stereotype.Service;

@Service
public interface TestRunnerRecordService {

    int insertTestRunnerRecord(TestRunnerRecord testRunnerRecord);

}
