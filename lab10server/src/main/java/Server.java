import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    static final
    public int PORT = 8888;

    static
    public void stopServer() {
        running = false;
    }

    static
    protected boolean running = true;

    public Server() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(5000);
            System.out.println("Wating for clients...");
            while (running) {
                try{
                    Socket socket = serverSocket.accept();
                    new ClientThread(socket).start();
                }
                catch (SocketTimeoutException ignored){
                }
            }
            System.out.println("server stopped");
        }
        catch (IOException e) {
            System.err.println("Oooops ... " + e);
        }
        finally {
            serverSocket.close();
        }

    }
}
