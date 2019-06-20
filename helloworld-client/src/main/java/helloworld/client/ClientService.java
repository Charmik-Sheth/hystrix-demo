package helloworld.client;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientService {
    @Autowired
    HelloWorldClient helloWorldClient;

    public String helloworld() {
        return new HelloWorldCommand(helloWorldClient).execute();
    }
}
