package nonresilient.server.service;

import nonresilient.server.client.FaultyServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonResilientService {
    @Autowired
    FaultyServiceClient faultyServiceClient;

    public String healthyBehaviour() {
        // Business Logic goes here
        return faultyServiceClient.healthyBehaviour();
    }

    public String delayedBehaviour(long delay) {
        // Business Logic Goes here
        return faultyServiceClient.delayedBehaviour(delay);
    }

    public String crashBehaviour(boolean crash) throws InterruptedException {
        // Some business logic that ideally should not be performed if the remote service call
        // is going to fail anyways.
        Thread.sleep(200);
        return faultyServiceClient.crashBehaviour(crash);
    }
}
