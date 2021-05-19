package com.example.demo.commands;
import db.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Read {
    private static Read instance = null;

    private Read() {
    }

    public static Read getInstance() {
        if (instance == null) {
            instance = new Read();
        }
        return instance;
    }

    public String readMessages(int userId) {
        List<String> view = new ArrayList<>();
        try {
            Connection con = JDBC.getConn();
            String sql = "select m.sent from messages m join friends f on f.user_id=m.user_id where f.friend_with_id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet i = stmt.executeQuery();
            while (i.next()) {
                view.add(i.getString("sent"));
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return view.toString();
    }
}
