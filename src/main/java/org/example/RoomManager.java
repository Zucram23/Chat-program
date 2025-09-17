package org.example;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {

private List<Room> rooms;

public RoomManager() {
    this.rooms = new ArrayList<Room>();
    rooms.add(new Room("Lavet en masse lort", 5));
    rooms.add(new Room("Putin er en legend", 5));
    rooms.add(new Room("Rummet for legender", 5));
    rooms.add(new Room("nazi rummet", 1));
}

public List<Room> getRooms() {
    return rooms;
}

public Room findRoomByName(String roomName) {
    for(Room room : rooms) {
        if (room.getRoomName().equalsIgnoreCase(roomName)) {
            return room;
        }
    }
    return null;
}
public boolean joinRoom(ClientHandler client, String roomName) {
    Room room = findRoomByName(roomName);
    if (room == null){
        return false;
    }
    leaveAllRooms(client);

    return room.addClient(client);
}

public boolean leaveRoom(ClientHandler client){
return true;
}

public void leaveAllRooms(ClientHandler client){

}

}
