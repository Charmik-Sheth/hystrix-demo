package helloworld.client;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {
    private HelloWorldClient helloWorldClient;

    public HelloWorldCommand(HelloWorldClient client) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloWorldCommand"));
        helloWorldClient = client;
    }

    @Override
    protected String run() throws Exception {
        return helloWorldClient.helloworld();
    }

    @Override
    protected String getFallback() {
        // if remote service failed/timed-out/circuit-tripped due
        // too many failures, we come here.
        // Fallback logic goes here.
    }
}
