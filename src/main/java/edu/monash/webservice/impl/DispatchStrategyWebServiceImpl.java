package edu.monash.webservice.impl;

import com.alibaba.fastjson.JSONObject;
import edu.monash.entity.DispatchStrategy;
import edu.monash.entity.TestCase;
import edu.monash.entity.TestRunner;
import edu.monash.service.DispatchStrategyService;
import edu.monash.service.TestRunnerService;
import edu.monash.util.Regex;
import edu.monash.webservice.DispatchStrategyWebService;
import edu.monash.webservice.TestCaseWebService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DispatchStrategyWebServiceImpl implements DispatchStrategyWebService {

    private static final Logger logger = LoggerFactory.getLogger(DispatchStrategyWebServiceImpl.class);

    @Autowired
    private DispatchStrategyService dispatchStrategyService;

    @Autowired
    private TestRunnerService testRunnerService;

    @Autowired
    private TestCaseWebService testCaseWebService;

    @Override
    public DispatchStrategy addDispatchStrategy(String dispatchStrategyRequest) {
        /**
         * round1 strategy
         */
//        JSONObject dispatchStrategyJson = (JSONObject)JSONObject.parse(dispatchStrategyRequest);
//        DispatchStrategy dispatchStrategy = DispatchStrategy.convert2DispatchStrategy(dispatchStrategyJson);
//        DispatchStrategy existDispatchStrategy = dispatchStrategyService.selectByDeviceIdAndBatchSize(dispatchStrategy.getDeviceId(), dispatchStrategy.getBatchSize());
//        if(existDispatchStrategy == null){
//            dispatchStrategy.setStartId(1);
//            dispatchStrategy.setEndId(dispatchStrategy.getBatchSize());
//            dispatchStrategy.setId(dispatchStrategyService.insertDispatchStrategy(dispatchStrategy));
//            return dispatchStrategy;
//        }else{
//            int startId = existDispatchStrategy.getStartId();
//            int endId = existDispatchStrategy.getEndId();
//            int batchSize = dispatchStrategy.getBatchSize();
//            dispatchStrategy.setStartId(startId + batchSize);
//            dispatchStrategy.setEndId(endId + batchSize);
//            return dispatchStrategyService.updateDispatchStrategy(dispatchStrategy);
//        }

        /**
         * round2 strategy
         */
        //get the latest executed test case
        JSONObject dispatchStrategyJson = (JSONObject)JSONObject.parse(dispatchStrategyRequest);
        DispatchStrategy dispatchStrategy = DispatchStrategy.convert2DispatchStrategy(dispatchStrategyJson);

        logger.info("[addDispatchStrategy] addDispatchStrategy:" + dispatchStrategy.toString());

        DispatchStrategy existDispatchStrategy = dispatchStrategyService.selectByDeviceIdAndBatchSize(dispatchStrategy.getDeviceId(), dispatchStrategy.getBatchSize());
        if(existDispatchStrategy == null){
            dispatchStrategy.setStartId(1);
            dispatchStrategy.setEndId(dispatchStrategy.getBatchSize());
            dispatchStrategy.setId(dispatchStrategyService.insertDispatchStrategy(dispatchStrategy));
            return dispatchStrategy;
        }else{
            int startId;
            int endId;
            List<TestRunner> executedTestCases = testRunnerService.selectListByDeviceIdAndDispatchStrategy(dispatchStrategy.getDeviceId(), dispatchStrategy.getBatchSize());
            if (CollectionUtils.isNotEmpty(executedTestCases)) {
                TestRunner latestExecutedTest = executedTestCases.get(executedTestCases.size() - 1);
                String latestExecutedTestName = Regex.getSubUtilSimple(latestExecutedTest.getTestCaseId(), "(^.*\\.)").replace(".", "");
                TestCase testCase = testCaseWebService.getTestCaseByName(latestExecutedTestName);
                startId = testCase.getId();
                endId = startId + dispatchStrategy.getBatchSize();
                dispatchStrategy.setStartId(startId + 1);
                dispatchStrategy.setEndId(endId);
                logger.info("[updateDispatchStrategy] updateDispatchStrategy:" + dispatchStrategy.toString());
                return dispatchStrategyService.updateDispatchStrategy(dispatchStrategy);
            }else{
                dispatchStrategy.setStartId(1);
                dispatchStrategy.setEndId(dispatchStrategy.getBatchSize());
                return dispatchStrategyService.updateDispatchStrategy(dispatchStrategy);
            }
        }
    }

    @Override
    public DispatchStrategy selectLatestBatch(String deviceId, int batchSize) {
       return dispatchStrategyService.selectByDeviceIdAndBatchSize(deviceId, batchSize);
    }
}
