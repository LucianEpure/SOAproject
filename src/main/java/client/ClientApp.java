package client;

import ch.qos.logback.core.net.server.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import serviceFinder.ServiceFinderServer;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, //
        DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters=false)
public class ClientApp {

    public static final String EMPLOYEE_SERVICE_URL
            = "http://employee-service";

    public static final String ROOM_SERVICE_URL
            = "http://room-service";

    public static final String BOOKING_SERVICE_URL
            = "http://booking-service";

    public static void main(String[] args) {

        if (System.getProperty(ServiceFinderServer.SERVICE_FINDER_HOSTNAME) == null)
            System.setProperty(ServiceFinderServer.SERVICE_FINDER_HOSTNAME, "localhost");

        // Will configure using web-server.yml
        System.setProperty("spring.config.name", "client-app");
        SpringApplication.run(ClientApp.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ClientService clientService() {
        return new ClientService(EMPLOYEE_SERVICE_URL,ROOM_SERVICE_URL, BOOKING_SERVICE_URL);
    }

    @Bean
    public ClientController clientController () {
        return new ClientController((clientService()));
    }


}
