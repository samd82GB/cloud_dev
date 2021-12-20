package com.geekbrains.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket srv =new ServerSocket(8189);
        System.out.println("Сервер включен");
        while (true) {
            Socket socket = srv.accept();
            Handler handler = new Handler(socket);
            new Thread (handler).start();

        }



    }

}
