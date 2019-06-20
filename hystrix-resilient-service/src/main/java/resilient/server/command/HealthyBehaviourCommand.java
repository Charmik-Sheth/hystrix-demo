package resilient.server.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resilient.server.client.FaultyServiceClient;

public class HealthyBehaviourCommand extends HystrixCommand<String> {
    private static final Logger logger = LogManager.getLogger(HealthyBehaviourCommand.class);
    private FaultyServiceClient faultyServiceClient;

    public HealthyBehaviourCommand(FaultyServiceClient client) {
        super(HystrixCommandGroupKey.Factory.asKey(HealthyBehaviourCommand.class.getSimpleName()));
        faultyServiceClient = client;
    }

    @Override
    protected String run() throws Exception {
        return faultyServiceClient.healthyBehaviour();
    }

    @Override
    protected String getFallback() {
        // We can have different fallback strategies depending on the type of exception
        logger.warn("In Fallback due to {}", this.getExecutionException().toString());
        return "Returning from Fallback for 'healthy'";
    }
}
