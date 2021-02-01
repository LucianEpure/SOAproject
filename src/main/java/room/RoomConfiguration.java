package room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@EntityScan("room")
@EnableJpaRepositories("room")
@PropertySource("classpath:db-config.properties")
public class RoomConfiguration {

    private RoomRepository roomRepository;

    @Autowired
    public RoomConfiguration(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    private void initialize(){
        Room room1= new Room();
        room1.setRoomFloor(1);
        room1.setRoomNumber(12);
        room1.setRoomType("Suite");
        room1.setSmokingRoom(true);
        roomRepository.save(room1);


        Room room2= new Room();
        room2.setRoomFloor(2);
        room2.setRoomNumber(22);
        room2.setRoomType("Single");
        room2.setSmokingRoom(false);
        roomRepository.save(room2);


        Room room3= new Room();
        room3.setRoomFloor(3);
        room3.setRoomNumber(31);
        room3.setRoomType("Suite");
        room3.setSmokingRoom(false);
        roomRepository.save(room3);


        Room room4= new Room();
        room4.setRoomFloor(4);
        room4.setRoomNumber(45);
        room4.setRoomType("Single");
        room4.setSmokingRoom(false);
        roomRepository.save(room4);
    }
}
