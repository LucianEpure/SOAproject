package booking;

import employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository  extends JpaRepository<Booking, Integer> {

    public Booking findById(int id);
}
