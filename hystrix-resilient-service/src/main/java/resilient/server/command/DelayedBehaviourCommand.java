package resilient.server.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resilient.server.client.FaultyServiceClient;

public class DelayedBehaviourCommand extends HystrixCommand<String> {
    private static final Logger logger = LogManager.getLogger(DelayedBehaviourCommand.class);
    private FaultyServiceClient faultyServiceClient;
    private long delay;

    public DelayedBehaviourCommand(FaultyServiceClient client, long del) {
        super(HystrixCommandGroupKey.Factory.asKey(DelayedBehaviourCommand.class.getSimpleName()));
        faultyServiceClient = client;
        delay = del;
    }

    @Override
    protected String run() throws Exception {
        return faultyServiceClient.delayedBehaviour(delay);
    }

    @Override
    protected String getFallback() {
        // We can have different fallback strategies depending on the type of exception
        logger.warn("In Fallback due to {}", this.getExecutionException().toString());
        return "Returning from Fallback for 'delay'";
    }
}
