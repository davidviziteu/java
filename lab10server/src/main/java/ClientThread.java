import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request;
            String response = "";
            while(!response.contains("exit") || !response.contains("stop")) {
                request = in.readLine();
                response = "";
                if (request.contains("stop")) {
                    response = "Server stopped!";
                } else {
                    response = "Server received the request : " + request;
                }
                out.println(response);
                out.flush();
            }
        }
        catch (IOException e) {
            System.out.println("System error ... " + e);
        } catch (NullPointerException ignored){
            //broken pipe
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        System.out.println("client exited");
    }
}
