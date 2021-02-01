package client;

import com.fasterxml.jackson.annotation.JsonRootName;

//DTO object
@JsonRootName("Room")
public class Room {

    private int id;

    private int roomNumber;
    private String roomType;
    private int roomFloor;
    private boolean smokingRoom;
    private boolean isAvailable;

    public Room() {
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public boolean isSmokingRoom() {
        return smokingRoom;
    }

    public void setSmokingRoom(boolean smokingRoom) {
        this.smokingRoom = smokingRoom;
    }
}
