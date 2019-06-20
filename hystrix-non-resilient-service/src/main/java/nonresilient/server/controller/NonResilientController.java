package nonresilient.server.controller;

import nonresilient.server.service.NonResilientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NonResilientController {
    @Autowired
    NonResilientService nonResilientService;

    @GetMapping("healthy")
    public String healthyBehaviour() {
        return nonResilientService.healthyBehaviour();
    }

    @GetMapping("delay/{delay}")
    public String delayedBehaviour(@PathVariable long delay) {
        return nonResilientService.delayedBehaviour(delay);
    }

    @GetMapping("crash/{crash}")
    public String crashBehaviour(@PathVariable boolean crash) throws InterruptedException {
        return nonResilientService.crashBehaviour(crash);
    }
}
