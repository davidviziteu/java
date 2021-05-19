package com.example.demo.requests;

import com.example.demo.commands.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class RequestHandler {

    String command;
    private List<String> tokens;

    public RequestHandler(String command) {
        this.command = command;
        tokenizer(command);
    }

    public String execute(int id) {
        if (tokens.get(0).equals("register")) {
            if (Register.getInstance().register(tokens.get(1))) {
                return "registered";
            }
            return "could not register";
        }
        if (tokens.get(0).equals("login")) {
            if (Login.getInstance().login(tokens.get(1)) != null){
                return "logged in";
            }
            return "log in failed";

        }
        if (tokens.get(0).equals("message")) {
            String message ="";
            for(int i=1; i<tokens.size(); i++){
                message += tokens.get(i);
            }
           if(Message.getInstance().Send(id, message)) {
            return "message sent";
           }else {
               return "message could not be sent";
           }
        }
        if (tokens.get(0).equals("read")) {
            return Read.getInstance().readMessages(1);
        }
        if (tokens.get(0).equals("friend")) {
            try {
                Friend.getInstance().newFriendship(id, Integer.parseInt(tokens.get(1)));
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            return "added friend";
        }
        return "unknown command";
    }

    private List<String> tokenizer(String request) {
        StringTokenizer t = new StringTokenizer(request, " ");
        int i = 0;
        tokens = new LinkedList<String>();
        while (t.hasMoreTokens()) {
            tokens.add(t.nextToken(" "));
            i++;
        }
        return tokens;
    }


}
