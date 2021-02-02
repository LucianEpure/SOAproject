package client;

import client.dto.Booking;
import client.dto.Employee;
import client.dto.Room;
import client.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @RequestMapping("/login")
    public String displayLogin(Model model){
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping("/menu")
    public String displayMenu(Model model){
        model.addAttribute(new User());
        return "menu";
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

    @RequestMapping("/employees/{employeeNumber}")
    public String getEmployeeByNumber(Model model, @PathVariable("employeeNumber") String employeeNumber) {
        Employee employee = clientService.findEmployeeByNumber(employeeNumber);

        model.addAttribute("employee", employee);
        return "employee";
    }

    @RequestMapping("/bookings/{bookingNumber}")
    public String getBookingByNumber(Model model, @PathVariable("bookingNumber") String bookingNumber) {
        Booking booking = clientService.findBookingByNumber(bookingNumber);

        model.addAttribute("booking", booking);
        return "booking";
    }

    @RequestMapping("/employees/deleteEmployee/{employeeNumber}")
    public String deleteEmployee(@PathVariable("employeeNumber") String employeeNumber) {
        clientService.deleteEmployee(employeeNumber);
        return "redirect:/menu";
    }

    @RequestMapping("/bookings/deleteBooking/{bookingNumber}")
    public String deleteBooking(@PathVariable("bookingNumber") String bookingNumber) {
        clientService.deleteBooking(bookingNumber);
        return "redirect:/menu";
    }

    @RequestMapping("/rooms/deleteRoom/{roomNumber}")
    public String deleteRoom(@PathVariable("roomNumber") String roomNumber) {
        clientService.deleteRoom(roomNumber);
        return "redirect:/menu";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        System.out.println(user.getUsername());
        boolean successful = clientService.loginUser(user);
        if(successful)
            return "redirect:/menu";
        else
            return "redirect:/menu";
            //return "redirect:/login";
    }

}
