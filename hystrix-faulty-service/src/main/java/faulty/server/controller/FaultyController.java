package faulty.server.controller;

import faulty.server.service.FaultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FaultyController {

    public static final Logger logger = LogManager.getLogger(FaultyController.class);

    @Autowired
    FaultyService faultyService;

    @GetMapping("healthy")
    public String healthyBehaviour() throws InterruptedException {
        logger.info("Endpoint 'healthy' called");
        return faultyService.healthyBehaviour();
    }

    @GetMapping("delay/{delay}")
    public String delayedBehaviour(@PathVariable long delay) throws InterruptedException {
        logger.info(String.format("Endpoint 'delay/%d' called", delay));
        return faultyService.delayedBehaviour(delay);
    }

    @GetMapping("crash/{crash}")
    public String crashBehaviour(@PathVariable boolean crash) throws IOException {
        logger.info(String.format("Endpoint 'crash/%b' called", crash));
        return faultyService.crashBehaviour(crash);
    }
}
