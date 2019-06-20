package faulty.server.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FaultyService {
    public String healthyBehaviour() throws InterruptedException {
        // Business Logic goes here
        Thread.sleep(100);
        return "SUCCESS";
    }

    public String delayedBehaviour(long delay) throws InterruptedException {
        // Some resource expensive business logic
        Thread.sleep(delay);
        return "DELAYED RESPONSE";
    }

    public String crashBehaviour(boolean crash) throws IOException {
        // Business logic that might result in an error
        if (crash)
            throw new IOException("You have crashed the system");
        else
            return "We didn't crash";
    }
}
