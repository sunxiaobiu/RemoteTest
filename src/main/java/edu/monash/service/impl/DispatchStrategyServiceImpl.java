package edu.monash.service.impl;

import edu.monash.dao.DispatchStrategyDAO;
import edu.monash.entity.DeviceGroup;
import edu.monash.entity.DeviceInfo;
import edu.monash.entity.DispatchStrategy;
import edu.monash.entity.TestRunner;
import edu.monash.service.DispatchStrategyService;
import edu.monash.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DispatchStrategyServiceImpl implements DispatchStrategyService {

    @Autowired
    private DispatchStrategyDAO dispatchStrategyDAO;

    @Override
    public int insertDispatchStrategy(DispatchStrategy dispatchStrategy) {
        ExceptionUtil.runtimeExpWithNullCheck(dispatchStrategy.getDeviceId(), "[DAO.insertDispatchStrategy] deviceId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(dispatchStrategy.getStartId(), "[DAO.insertDispatchStrategy] getStartId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(dispatchStrategy.getEndId(), "[DAO.insertDispatchStrategy] getEndId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(dispatchStrategy.getBatchSize(), "[DAO.insertDispatchStrategy] getBatchSize shouldn't be null!");
        return dispatchStrategyDAO.insert(dispatchStrategy);
    }

    @Override
    public DispatchStrategy selectByDeviceIdAndBatchSize(String deviceId, int batchSize) {
        ExceptionUtil.runtimeExpWithNullCheck(deviceId, "[DAO.selectByDeviceIdAndBatchSize] deviceId shouldn't be null!");
        ExceptionUtil.runtimeExpWithNullCheck(batchSize, "[DAO.selectByDeviceIdAndBatchSize] batchSize shouldn't be null!");
        return dispatchStrategyDAO.selectByDeviceIdAndBatchSize(deviceId, batchSize);
    }

    @Override
    public DispatchStrategy updateDispatchStrategy(DispatchStrategy dispatchStrategy) {
        ExceptionUtil.runtimeExpWithNullCheck(dispatchStrategy.getDeviceId(), "[DAO.updateDispatchStrategy] deviceId should not be null!");
        ExceptionUtil.runtimeExpWithNullCheck(dispatchStrategy.getBatchSize(), "[DAO.updateDispatchStrategy] getBatchSize should not be null!");
        dispatchStrategyDAO.update(dispatchStrategy);
        return  dispatchStrategyDAO.selectByDeviceIdAndBatchSize(dispatchStrategy.getDeviceId(), dispatchStrategy.getBatchSize());
    }

}
