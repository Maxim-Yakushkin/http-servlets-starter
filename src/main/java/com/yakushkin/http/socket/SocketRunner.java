package com.yakushkin.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        //        TCP порты
        // http - 80
        // https - 443
        final InetAddress inetAddress = Inet4Address.getByName("localhost");
        try (Socket socket = new Socket(inetAddress, 7777);
             final var outputStream = new DataOutputStream(socket.getOutputStream());
             final var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {

            while (scanner.hasNextLine()) {
                var clientRequest = scanner.nextLine();
                outputStream.writeUTF(clientRequest); // отправка запроса на наш сервер SocketServerRunner с портом 7777
                System.out.println("Response from Server: " + inputStream.readUTF());
            }
        }
    }
}
