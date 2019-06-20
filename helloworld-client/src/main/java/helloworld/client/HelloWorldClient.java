package helloworld.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "HelloWorldService", url = "localhost:8080")
public interface HelloWorldClient {

    @GetMapping("/hello")
    String helloworld();
}
