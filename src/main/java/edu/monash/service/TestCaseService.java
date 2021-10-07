package edu.monash.service;

import edu.monash.entity.TestCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestCaseService {

    TestCase findTestCaseByUniqueId(String uniqueId);

    List<TestCase> selectList(int size);

    List<TestCase> selectAvaliableTestCasesByDeviceId(List<String> deviceIds);

    List<TestCase> selectFromStartToEnd(int startId, int endId);
}
