package edu.monash.webservice;

import edu.monash.entity.DispatchStrategy;
import org.springframework.stereotype.Service;

@Service
public interface DispatchStrategyWebService {

    public DispatchStrategy addDispatchStrategy(String deviceInfoRequest);

    public DispatchStrategy selectLatestBatch(String deviceId, int batchSize);

}
