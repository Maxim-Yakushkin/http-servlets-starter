package com.yakushkin.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        //        TCP порты
        // http - 80
        // https - 443
        final InetAddress inetAddress = Inet4Address.getByName("google.com");
        try (Socket socket = new Socket(inetAddress, 80);
             final var outputStream = new DataOutputStream(socket.getOutputStream());
             final var inputStream = new DataInputStream(socket.getInputStream())) {

            outputStream.writeUTF("Hello World!"); // отправка запроса в google
            final byte[] response = inputStream.readAllBytes(); // чтение ответа

            System.out.println(response.length);
            System.out.println(new String(response, StandardCharsets.UTF_8));
        }
    }
}
