package com.geekbrains.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Handler implements Runnable {
    private final DataInputStream is;
    private final DataOutputStream os;

    public Handler(Socket socket) throws Exception{
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        System.out.println("Клиент подлключен ...");
    }

    @Override
    public void run() {
        try {
            while (true) {
                String command = is.readUTF().trim();
                System.out.println("Принято сообщение от клиента: "+command);
                if (command.equals("getList")) {
                    os.writeUTF("List: .....");
                } else if (command.equals("putFile")) {
                    os.writeUTF("Введите имя файла");
                    String fileName = is.readUTF();
                    os.writeUTF("Введите размер файла");
                    long size = is.readLong();
                    os.writeUTF("Загружен файл: "+fileName+" размером "+size);
                } else if (command.equals("getFile")) {
                    os.writeUTF("Введите имя файла");
                    String fileName = is.readUTF();
                    os.writeUTF("Fle "+fileName);
                }
                os.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
