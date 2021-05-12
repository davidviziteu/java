package commands;

import db.JDBC;
import models.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register {

    private static Register singletonInstance = new Register();

    private Register() {
    }

    public static Register getInstance() {
        if (singletonInstance == null) {
            new Register();
        }
        return singletonInstance;
    }

    public boolean register(String username) {
        try {
            Connection con = JDBC.getConn();
            PreparedStatement stmt = con.prepareStatement("insert into users(id, name) values (nextval('users_id_seq'), ?)");
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return true;
    }
}
