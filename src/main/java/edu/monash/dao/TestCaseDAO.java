package edu.monash.dao;

import edu.monash.entity.TestCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseDAO {

    TestCase selectByUniqueId(@Param("uniqueId") int uniqueId);

    List<TestCase> selectList(@Param("size") int size);

    List<TestCase> selectAvaliableTestCasesByDeviceId(List<String> deviceIds);

    List<TestCase> selectFromStartToEnd(@Param("startId")int startId,
                                        @Param("endId")int endId);
}
