import commands.*;
import models.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
    }

    private Users user = null;

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            socket.setSoTimeout(5000 * 10);
            String request;
            String response = "";
            while (!response.contains("exit") || !response.contains("stop")) {
                request = in.readLine();
                response = "";
                if (request.contains("stop")) {
                    response = "Stopping the server. not accepting any new connections";
                    Server.stopServer();
                    out.println(response);
                    out.flush();
                    return;
                } else if (request.contains("login")) {
                    out.println("please enter your username");
                    out.flush();
                    request = in.readLine();
                    this.user = Login.getInstance().login(request);
                    if (user == null) {
                        out.println("no such name in db. try again");
                        out.flush();
                        continue;
                    }
                    out.println("ok");
                    out.flush();
                } else if (request.contains("read")) {
                    if (notLoggedIn(out)) continue;

                    String msgs = Read.getInstance().readMessages(user.getId());
                    out.println(msgs);
                    out.flush();
                } else if (request.contains("friend")) {
                    System.out.println("friend command");
                    if (notLoggedIn(out)) continue;
                    out.println("enter friend name:");
                    out.flush();
                    request = in.readLine();
                    Users newFriend = Login.getInstance().login(request);
                    if(newFriend == null)
                        out.println("no such name in db. try again");
                    else try {
                        Friend.getInstance().newFriendship(user.getId(), newFriend.getId());
                        out.println("friend added");
                    } catch (SQLException e) {
                        out.println("no such id in db");
                    }
                    out.flush();
                } else if(request.contains("register")){
                    out.println("enter your name:");
                    out.flush();
                    request = in.readLine();
                    Users existingUser = Login.getInstance().login(request);
                    if(existingUser != null) {
                        out.println("name already in db. try again");
                        out.flush();
                        continue;
                    }
                    Register.getInstance().register(request);
                    out.println("registered");
                    out.flush();
                } else if(request.contains("message")){
                    if (notLoggedIn(out)) continue;
                    out.println("enter your message for all your friends:");
                    out.flush();
                    request = in.readLine();
                    Message.getInstance().Send(user.getId(), request);
                    out.println("message recoded");
                    out.flush();
                } else if(request.contains("exit")){
                    return;
                }
                else {
                    out.println("unknown command");
                    out.flush();
                    continue;
                }
            }
        } catch (java.net.SocketTimeoutException e) {
            // clientul nu a raspuns dupa nush cate minute, deci oprim conexiunea cu acesta
            // se executa block ul finally
            System.out.println("client kicked due to inactivity");
            return;
        } catch (IOException e) {
            System.out.println("System error " + e);
        } catch (NullPointerException ignored) {
            //broken pipe
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("client exited");
    }

    private boolean notLoggedIn(PrintWriter out) {
        if (user == null) {
            out.println("you must be logged in");
            out.flush();
            return true;
        }
        return false;
    }
}
