package com.yakushkin.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777);
             var socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            String clientRequest = inputStream.readUTF();
            while (!"stop".equals(clientRequest)) {
                System.out.println("Client request: " + clientRequest);
                String serverResponse = scanner.nextLine();
                outputStream.writeUTF("Server response: " + serverResponse);
                clientRequest = inputStream.readUTF();
            }
        }
    }
}
