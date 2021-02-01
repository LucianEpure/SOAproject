package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@EntityScan("employee")
@EnableJpaRepositories("employee")
@PropertySource("classpath:db-config.properties")
public class EmployeesConfiguration {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeesConfiguration(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @PostConstruct
    private void initialize(){
        Employee employee1 = new Employee();
        employee1.setEmployeeName("Employee 1");
        employee1.setSalary(4500);
        employeeRepository.save(employee1);


        Employee employee2 = new Employee();
        employee2.setEmployeeName("Employee 2");
        employee2.setSalary(10000);
        employeeRepository.save(employee2);


        Employee employee3 = new Employee();
        employee3.setEmployeeName("Employee 3");
        employee3.setSalary(9000);
        employeeRepository.save(employee3);


        Employee employee4 = new Employee();
        employee4.setEmployeeName("Employee 4");
        employee4.setSalary(1000);
        employeeRepository.save(employee4);
    }
}
