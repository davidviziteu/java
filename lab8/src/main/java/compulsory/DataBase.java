package compulsory;


import java.sql.*;

public class DataBase {
    static Connection db = null;

    static public Connection getInstance() {
        if (db != null) {
            return db;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:D:\\BAZADEDATEORACLE\\SQLITE\\lab9.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return db;

    }

    private DataBase(){}

    public void test(String name) {
        try {
            Statement stmt = db.createStatement();
            String sql = "SELECT * FROM STUDENTI";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString(2).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
