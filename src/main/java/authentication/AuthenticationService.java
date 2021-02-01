package authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import room.RoomConfiguration;
import room.RoomService;
import serviceFinder.ServiceFinderServer;


@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AuthenticationConfiguration.class)
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        if (System.getProperty(ServiceFinderServer.SERVICE_FINDER_HOSTNAME) == null)
            System.setProperty(ServiceFinderServer.SERVICE_FINDER_HOSTNAME, "localhost");
        System.setProperty("spring.config.name", "authentication-service");

        SpringApplication.run(AuthenticationService.class, args);
    }
}
