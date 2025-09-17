package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.net.*;
public class ClientHandler implements Runnable {
    private Socket socket;
    private String clientId;
    private BufferedReader reader;
    private PrintWriter writer;
    private Server server;
    private String username;
    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.clientId = "Client-"+socket.getPort();

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (
                IOException e) {
            System.err.println("Failed to get streams for " + clientId + ": " + e.getMessage());
        }
    }
    public void sendMessage(String message) {
        writer.println(message);
    }

    private void sendHelpMessage() {
        sendMessage("=== CHAT COMMANDS ===\n" +
                "/join <room>  - Join a room (Lobby, Gaming, Study, Random, VIP)\n" +
                "/leave        - Leave current room\n" +
                "/rooms        - List all rooms\n" +
                "/who          - Show users in current room\n" +
                "/help         - Show this help\n" +
                "/quit         - Leave the chat");
    }


    @Override
    public void run() {

        try {
            server.addClient(this);

            writer.println("Welcome! Please enter your username: ");
            username = reader.readLine();

            server.broadcastMessage("["+clientId+"]: "+ username+" has joined the server!", this);
            sendHelpMessage();


            while (true) {
                String message = reader.readLine();
                server.broadcastMessage("Message from [" + this.clientId + "] " + this.username + ": " + message, this);
            }
        } catch (IOException e) {}
    }
    public String getUsername() {
        return username;
    }
}
