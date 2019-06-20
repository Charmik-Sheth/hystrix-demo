package resilient.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import resilient.server.service.ResilientService;

@RestController
public class ResilientController {
    @Autowired
    ResilientService resilientService;

    @GetMapping("healthy")
    public String healthyBehaviour() {
        return resilientService.healthyBehaviour();
    }

    @GetMapping("delay/{delay}")
    public String delayedBehaviour(@PathVariable long delay) {
        return resilientService.delayedBehaviour(delay);
    }

    @GetMapping("crash/{crash}")
    public String crashBehviour(@PathVariable boolean crash) {
        return resilientService.crashBehaviour(crash);
    }
}
