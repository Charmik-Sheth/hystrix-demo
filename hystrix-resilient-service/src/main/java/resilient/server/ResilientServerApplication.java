package resilient.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
//@EnableCircuitBreaker
public class ResilientServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResilientServerApplication.class, args);
    }
}
