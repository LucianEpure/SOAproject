package room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    private  RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository) { this.roomRepository = roomRepository;}

    @RequestMapping("/rooms")
    public List<Room> retrieveRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    @RequestMapping("/rooms/{roomNumber}")
    public Room getRoomByNumber(@PathVariable("roomNumber") String roomNumber) {
        Room room = roomRepository.findByRoomNumber(Integer.parseInt(roomNumber));
        System.out.println(room.getRoomFloor());
        return room;
    }
}
