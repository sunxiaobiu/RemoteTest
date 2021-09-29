package edu.monash.webservice.impl;

import com.alibaba.fastjson.JSONObject;
import edu.monash.entity.DispatchStrategy;
import edu.monash.service.DispatchStrategyService;
import edu.monash.webservice.DispatchStrategyWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchStrategyServiceImpl implements DispatchStrategyWebService {

    private static final Logger logger = LoggerFactory.getLogger(DispatchStrategyServiceImpl.class);

    @Autowired
    private DispatchStrategyService dispatchStrategyService;

    @Override
    public DispatchStrategy addDispatchStrategy(String dispatchStrategyRequest) {
        JSONObject dispatchStrategyJson = (JSONObject)JSONObject.parse(dispatchStrategyRequest);
        DispatchStrategy dispatchStrategy = DispatchStrategy.convert2DispatchStrategy(dispatchStrategyJson);
        DispatchStrategy existDispatchStrategy = dispatchStrategyService.selectByDeviceIdAndBatchSize(dispatchStrategy.getDeviceId(), dispatchStrategy.getBatchSize());
        if(existDispatchStrategy == null){
            dispatchStrategy.setStartId(1);
            dispatchStrategy.setEndId(dispatchStrategy.getBatchSize());
            dispatchStrategy.setId(dispatchStrategyService.insertDispatchStrategy(dispatchStrategy));
            return dispatchStrategy;
        }else{
            int startId = dispatchStrategy.getStartId();
            int endId = dispatchStrategy.getEndId();
            int batchSize = dispatchStrategy.getBatchSize();
            dispatchStrategy.setStartId(startId + batchSize);
            dispatchStrategy.setEndId(endId + batchSize);
            return dispatchStrategyService.updateDispatchStrategy(dispatchStrategy);
        }
    }

    @Override
    public DispatchStrategy selectLatestBatch(String deviceId, int batchSize) {
       return dispatchStrategyService.selectByDeviceIdAndBatchSize(deviceId, batchSize);
    }
}
