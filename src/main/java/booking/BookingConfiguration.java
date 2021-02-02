package booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;

@Configuration
@ComponentScan
@EntityScan("booking")
@EnableJpaRepositories("booking")
@PropertySource("classpath:db-config.properties")

public class BookingConfiguration {

    private BookingRepository bookingRepository;
    @Autowired
    public BookingConfiguration(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @PostConstruct
    private void initialize(){
        Booking booking1 = new Booking();
        booking1.setClientName("client1");
        booking1.setCost(500);
        booking1.setNoOfDays(14);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setClientName("client2");
        booking2.setCost(600);
        booking2.setNoOfDays(7);
        bookingRepository.save(booking2);

    }
}
