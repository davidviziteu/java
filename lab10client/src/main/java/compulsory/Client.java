package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static boolean running = true;

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        String consoleText;
        String serverResponse;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (running) {
                consoleText = consoleIn.readLine();
                if (consoleText.contains("exit"))
                    running = false;
                socketOut.println(consoleText);
                serverResponse = socketIn.readLine();
                if (consoleText.contains("stop")){
                    running = false;
                }
                System.out.println(serverResponse);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
        System.out.println("exited");
    }
}