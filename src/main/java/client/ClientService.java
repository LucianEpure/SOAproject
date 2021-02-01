package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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

    public ClientService(String employeeServiceUrl, String roomServiceUrl, String bookingServiceUrl){
        {
            this.employeeServiceUrl = employeeServiceUrl.startsWith("http") ?
                    employeeServiceUrl : "http://" + employeeServiceUrl;
            this.roomServiceUrl = roomServiceUrl.startsWith("http") ?
                    roomServiceUrl : "http://" + roomServiceUrl;
            this.bookingServiceUrl = bookingServiceUrl.startsWith("http") ?
                    bookingServiceUrl : "http://" + bookingServiceUrl;
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
            System.out.println("URL is" + this.bookingServiceUrl);
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
}
