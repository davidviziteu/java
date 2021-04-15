package compulsory;


import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DataBase {
    Connection db = null;
    static DataBase instance = null;

    static public DataBase getInstance(){
        if(instance == null)
            instance = new DataBase();
        return instance;
    }

    DataBase(){
        try{
            db = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "STUDENT", "STUDENT");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void test(String name){
        try {
            Statement stmt = db.createStatement( );
            String sql = "SELECT * FROM PROFESORI";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
