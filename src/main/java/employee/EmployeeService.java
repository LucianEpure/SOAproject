package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import serviceFinder.ServiceFinderServer;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(EmployeesConfiguration.class)
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        if (System.getProperty(ServiceFinderServer.SERVICE_FINDER_HOSTNAME) == null)
            System.setProperty(ServiceFinderServer.SERVICE_FINDER_HOSTNAME, "localhost");
        System.setProperty("spring.config.name", "employee-service");

        SpringApplication.run(EmployeeService.class, args);
    }
}
