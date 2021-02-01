package serviceFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceFinderServer {
    public static final String SERVICE_FINDER_HOSTNAME = "service.finder.hostname";

    public static void main(String[] args) {
        // Tell Boot to look for registration-server.yml
        System.setProperty("spring.config.name", "service-finder");
        SpringApplication.run(ServiceFinderServer.class, args);
    }
}
