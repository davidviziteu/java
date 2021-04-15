package compulsory;


import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static public void main(String[] args) {
        var db = DataBase.getInstance();
        db.test("aa");
    }

}
