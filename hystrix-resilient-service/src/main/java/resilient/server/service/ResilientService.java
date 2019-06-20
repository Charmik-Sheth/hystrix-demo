package resilient.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resilient.server.client.FaultyServiceClient;
import resilient.server.command.CrashBehaviourCommand;
import resilient.server.command.DelayedBehaviourCommand;
import resilient.server.command.HealthyBehaviourCommand;

@Service
public class ResilientService {

    @Autowired
    FaultyServiceClient faultyServiceClient;

    public String healthyBehaviour() {
        // Business logic that must be performed regardless of remote service's state goes here.
        return new HealthyBehaviourCommand(faultyServiceClient).execute();
    }

    public String delayedBehaviour(long delay) {
        // Business logic that must be performed regardless of remote service's state goes here.
        return new DelayedBehaviourCommand(faultyServiceClient, delay).execute();
    }

    public String crashBehaviour(boolean crash) {
        // Business logic that must be performed regardless of remote service's state goes here.
        return new CrashBehaviourCommand(faultyServiceClient, crash).execute();
    }
}
