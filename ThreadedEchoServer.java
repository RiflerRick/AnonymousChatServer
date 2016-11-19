import java.io.*;
import java.util.*;
import java.net.*;

public class ThreadedEchoServer {

    static final int PORT = 1337;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            new EchoThread(socket).start();
        }
    }
}