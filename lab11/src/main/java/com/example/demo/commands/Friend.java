
package com.example.demo.commands;

import db.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Friend {

    private static Friend singletonInstance = new Friend();

    private Friend() {
    }

    public static Friend getInstance() {
        if (singletonInstance == null) {
            new Friend();
        }
        return singletonInstance;
    }

    public boolean newFriendship(int friend1, int friend2) throws SQLException {
        try {
            Connection con = JDBC.getConn();
            PreparedStatement stmt = con.prepareStatement("insert into friends(user_id, friend_with_id) values (?, ?)");
            stmt.setInt(1, friend1);
            stmt.setInt(2, friend2);
            stmt.executeUpdate();
        } catch (java.sql.SQLException e){
            throw e;
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return true;
    }
}
