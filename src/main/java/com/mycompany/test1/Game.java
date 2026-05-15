package com.mycompany.test1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Game {
    // Main method to start the server
    public static void main(String[] args) throws IOException {
        // Start the server socket on port 1414
        ServerSocket server = new ServerSocket(1414);
        System.out.println("Game Server Started... Port: 1414");
        
        // Infinite loop to continuously accept new player pairs
        while (true) {
            // Wait for the first player to connect
            Socket s1 = server.accept();
            System.out.println("Player 1 Connected.");
            
            // Wait for the second player``` to connect
            Socket s2 = server.accept();
            System.out.println("Player 2 Connected. Match Starting...");
            
            // Once both players are ready, start the Match class as a Thread
            Thread t = new Thread(new Match(s1, s2));
            t.start();
        }
    }
}