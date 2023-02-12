package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import logging.Logging;

public abstract class GetConnect {
    public static Connection getConnection() {
        Connection conn = null;
        String usr = "duyaiti";
        String pss = "12345678";
        String url = "jdbc:mysql://localhost:3306/" + "App_Console";
        Logging logger = new Logging();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usr, pss);
            if (conn != null) {
                Logging.writeLog(logger.successLog(usr, pss));
            }
        } catch (ClassNotFoundException e) {
            Logging.writeLog(logger.getError(e));
        } catch (SQLException ex) {
            Logging.writeLog(logger.getError(ex));
        }
        return conn;
    }
    
    public boolean isConnected(Connection conn) {
        return conn != null;
    }

}
