package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @RequestMapping("/employees")
    public String displayEmployeesMenu(Model model) {
        List<Employee> employees = clientService.retreiveEmployees();
            model.addAttribute("employees",employees);
        return "employees";
    }

    @RequestMapping("/rooms")
    public String displayRoomMenu(Model model) {
        List<Room> rooms = clientService.retreiveRooms();
        model.addAttribute("rooms",rooms);
        return "rooms";
    }

    @RequestMapping("/bookings")
    public String displayBookingsMenu(Model model) {
        List<Booking> bookings = clientService.retreiveBookings();
        model.addAttribute("bookings",bookings);
        return "bookings";
    }

    @RequestMapping("/rooms/{roomNumber}")
    public String getRoomByNumber(Model model, @PathVariable("roomNumber") String roomNumber) {
        System.out.println(roomNumber);
       Room room = clientService.findRoomByNumber(roomNumber);

        model.addAttribute("room", room);
        return "room";
    }

}
