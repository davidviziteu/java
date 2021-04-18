package compulsory;


import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DataBase {
    static Connection db = null;
    static DataBase instance = null;

    static public Connection getInstance(){
        if(db == null)
            new DataBase();
        return db;
    }

    DataBase(){
        try{
            db = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "c##STUDENT", "STUDENT");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void test(String name){
        try {
            Statement stmt = db.createStatement( );
            String sql = "SELECT * FROM STUDENTI";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString(2).toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
