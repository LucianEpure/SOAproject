package employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public Employee findById(int id);

    public Employee findBySalary(int salary);

}
