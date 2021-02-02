package booking;

import employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.List;

@RestController
public class BookingController {

    private BookingRepository bookingRepository;

    @Autowired
    public  BookingController(BookingRepository bookingRepository) {this.bookingRepository = bookingRepository;}

    @RequestMapping("/bookings")
    public List<Booking> retrieveEmployees() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings;
    }

    @RequestMapping("/bookings/{bookingNumber}")
    public Booking getBookingByNumber(@PathVariable("bookingNumber") String bookingNumber) {
        Booking booking = bookingRepository.findById(Integer.parseInt(bookingNumber));
        return booking;
    }
}
