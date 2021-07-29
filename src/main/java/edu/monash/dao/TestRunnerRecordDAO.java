package edu.monash.dao;

import edu.monash.entity.TestRunnerRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRunnerRecordDAO {

    int insert(TestRunnerRecord testRunnerRecord);
}
