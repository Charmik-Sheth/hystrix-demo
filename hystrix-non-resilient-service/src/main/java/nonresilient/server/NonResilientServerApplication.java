package nonresilient.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NonResilientServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NonResilientServerApplication.class, args);
    }
}
