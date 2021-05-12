package commands;

import db.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private static Message instance = new Message();

    private Message() {
    }

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
        }
        return instance;
    }

    public boolean Send(int userId, String message) {
        List<String> view = new ArrayList<>();
        try {
            Connection con = JDBC.getConn();
            PreparedStatement stmt = con.prepareStatement("insert into messages(user_id, sent) values (?, ?)");
            stmt.setInt(1, userId);
            stmt.setString(2, message);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return true;
    }
}
