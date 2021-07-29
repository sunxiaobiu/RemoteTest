package edu.monash.service;

import edu.monash.entity.TestCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestCaseService {

    TestCase findTestCaseByUniqueId(int uniqueId);

    List<TestCase> selectList(int size);

    List<TestCase> selectAvaliableTestCasesByDeviceId(List<String> deviceIds);
}
