package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.Room;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping("/employees")
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();

            return employees;
    }
    @RequestMapping("/employees/{employeeNumber}")
    public Employee getEmployeeByNumber(@PathVariable("employeeNumber") String employeeNumber) {
        Employee employee = employeeRepository.findById(Integer.parseInt(employeeNumber));
        return employee;
    }
    @RequestMapping(value = "/employees/deleteEmployee/{employeeNumber}")
    public String deleteEmployee(@PathVariable("employeeNumber") String employeeNumber) {
        employeeRepository.deleteById(Integer.parseInt(employeeNumber));
        return "menu";
    }

}
