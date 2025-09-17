package org.example;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<ClientHandler> clients = new ArrayList<>();

    public void addClient(ClientHandler client) {
        clients.add(client);
    }
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message, ClientHandler sender){
        System.out.println("Broadcasting message: " + message);
        for (ClientHandler client: clients){
       // if (client!=sender){
            client.sendMessage(message);
      //  }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();

        try {
            ServerSocket serverSocket = new ServerSocket(5001);
            System.out.println("Listening on port 5001");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, server);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


}
