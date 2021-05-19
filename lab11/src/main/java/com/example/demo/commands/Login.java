package com.example.demo.commands;
import com.example.demo.models.Users;
import db.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    private static Login instance = null;

    private Login() {
    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }


    public Users login(String username) {
        try {
            Connection con = JDBC.getConn();
            PreparedStatement stmt = con.prepareStatement("select * from users where name = ?");
            stmt.setString(1, username);
            ResultSet r = stmt.executeQuery();
            r.next();
            if (r.getRow() == 0 ) {
                System.out.println(username + " not in the db rows: "+ r.getRow());
                return null;
            } else {
                System.out.println(username + " logged in");
                return new Users(r.getInt(1), r.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
