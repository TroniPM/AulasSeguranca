package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int contador = 0;

    public static synchronized int getContador() {
        int n = contador;
        increment();
        return n;
    }

    public static void increment() {
        contador++;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Servidor inicializado, aguardando cliente ...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
