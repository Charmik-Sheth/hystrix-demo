package nonresilient.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "FaultyService", url = "localhost:8080")
public interface FaultyServiceClient {
    @GetMapping("/healthy")
    String healthyBehaviour();

    @GetMapping("/delay/{delay}")
    String delayedBehaviour(@PathVariable long delay);

    @GetMapping("/crash/{crash}")
    String crashBehaviour(@PathVariable boolean crash);
}
