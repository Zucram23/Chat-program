package org.example;

import java.util.ArrayList;
import java.util.List;

public class Room {
    String roomName;
    List<ClientHandler> clientHandlers;


public Room(String roomName) {
    this.roomName = roomName;
    this.clientHandlers = new ArrayList<ClientHandler>();
}

}
