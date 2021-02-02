package client;

import client.dto.Booking;
import client.dto.Employee;
import client.dto.Room;
import client.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String employeeServiceUrl;
    protected String roomServiceUrl;
    protected String bookingServiceUrl;
    protected String authenticationServiceUrl;

    public ClientService(String employeeServiceUrl, String roomServiceUrl, String bookingServiceUrl, String authenticationServiceUrl){
        {
            this.employeeServiceUrl = employeeServiceUrl.startsWith("http") ?
                    employeeServiceUrl : "http://" + employeeServiceUrl;
            this.roomServiceUrl = roomServiceUrl.startsWith("http") ?
                    roomServiceUrl : "http://" + roomServiceUrl;
            this.bookingServiceUrl = bookingServiceUrl.startsWith("http") ?
                    bookingServiceUrl : "http://" + bookingServiceUrl;
            this.authenticationServiceUrl = authenticationServiceUrl.startsWith("http") ?
                    authenticationServiceUrl : "http://" + authenticationServiceUrl;
        }

    }

    public List<Employee> retreiveEmployees(){
        Employee[] employees = null;

        try {
            System.out.println("URL is" + this.employeeServiceUrl);
            employees = restTemplate.getForObject(employeeServiceUrl + "/employees", Employee[].class);
        } catch (HttpClientErrorException e) { // 404
           System.out.println("FAILED HERE");
        }

        if (employees == null || employees.length == 0)
            return null;
        else
            return Arrays.asList(employees);
    }

    public List<Room> retreiveRooms(){
        Room[] rooms = null;

        try {
            System.out.println("URL is" + this.roomServiceUrl);
            rooms = restTemplate.getForObject(roomServiceUrl + "/rooms", Room[].class);
        } catch (HttpClientErrorException e) { // 404
            System.out.println("FAILED HERE");
        }

        if (rooms == null || rooms.length == 0)
            return null;
        else
            return Arrays.asList(rooms);
    }

    public List<Booking> retreiveBookings(){
        Booking[] bookings = null;

        try {
            bookings = restTemplate.getForObject(bookingServiceUrl + "/bookings", Booking[].class);
        } catch (HttpClientErrorException e) { // 404
            System.out.println("FAILED HERE");
        }

        if (bookings == null || bookings.length == 0)
            return null;
        else
            return Arrays.asList(bookings);
    }

    public Room findRoomByNumber(String roomNumber){
        try {
            return restTemplate.getForObject(roomServiceUrl + "/rooms/{roomNumber}", Room.class, roomNumber);
        } catch (Exception e) {
            return null;
        }
    }

    public Employee findEmployeeByNumber(String employeeNumber){
        try {
            return restTemplate.getForObject(employeeServiceUrl + "/employees/{employeeNumber}", Employee.class, employeeNumber);
        } catch (Exception e) {
            return null;
        }
    }

    public Booking findBookingByNumber(String bookingNumber){
        try {
            return restTemplate.getForObject(bookingServiceUrl + "/bookings/{bookingNumber}", Booking.class, bookingNumber);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteEmployee(String employeeNumber){
        try {
            restTemplate.getForObject(employeeServiceUrl + "/employees/deleteEmployee/{employeeNumber}", Employee.class, employeeNumber);
        } catch (Exception e) {
        }
    }

    public void deleteBooking(String bookingNumber){
        try {
            restTemplate.getForObject(bookingServiceUrl + "/booking/deleteBooking/{bookingNumber}", Booking.class, bookingNumber);
        } catch (Exception e) {
        }
    }

    public void deleteRoom(String roomNumber){
        try {
            restTemplate.getForObject(roomServiceUrl + "/rooms/deleteRoom/{roomNumber}", Room.class, roomNumber);
        } catch (Exception e) {
        }
    }

    public boolean loginUser(User user){
        try {
            System.out.println("URL is" + this.authenticationServiceUrl);
            ResponseEntity<String> result = restTemplate.postForEntity(authenticationServiceUrl + "/login", user, String.class);
            System.out.println(result.getBody());
            System.out.println("sent");
            if(result.getBody().equals("true"))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
