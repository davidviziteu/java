package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    static
    private Connection dbconn = null;

    static public Connection getConn() throws SQLException {
        if(dbconn == null)
            dbconn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab10","postgres","david");
        return dbconn;
    }

    private JDBC(){}
}
