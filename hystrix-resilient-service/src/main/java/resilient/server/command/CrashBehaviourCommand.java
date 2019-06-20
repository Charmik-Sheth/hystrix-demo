package resilient.server.command;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resilient.server.client.FaultyServiceClient;

public class CrashBehaviourCommand extends HystrixCommand<String> {
    private static final Logger logger = LogManager.getLogger(CrashBehaviourCommand.class);
    private FaultyServiceClient faultyServiceClient;
    private boolean crash;

    public CrashBehaviourCommand(FaultyServiceClient client, boolean cr) {
        super(HystrixCommandGroupKey.Factory.asKey(CrashBehaviourCommand.class.getSimpleName()));
        AbstractConfiguration config = ConfigurationManager.getConfigInstance();
        System.out.println("requestVolumeThreshold: " + config.getProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold"));
        faultyServiceClient = client;
        crash = cr;
    }

    @Override
    protected String run() throws Exception {
        // Some resource heavy business logic. We should not do this heavy computation
        // if the request to remote service is going to fail anyways.
        Thread.sleep(200);
        return faultyServiceClient.crashBehaviour(crash);
    }

    @Override
    protected String getFallback() {
        // We can have different fallback strategies depending on the type of exception
        logger.warn("In Fallback due to {}", this.getExecutionException().toString());
        return "Returning from Fallback for 'crash'";
    }
}
