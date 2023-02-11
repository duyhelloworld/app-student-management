package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.GetConnect;
import logging.Logging;
import model.User;

public class CrudUserService implements CrudUser, Runnable {
    String tableName = "User", dbNAme = "`App_Console`", tableUser = dbNAme + "." + tableName;
    String viewAllQuery = "SELECT * FROM " + tableUser + ";";
    String viewOnlyQuery = "SELECT * FROM " + tableUser + " WHERE id = ?;";
    String createQuery = "INSERT INTO " + tableUser + " VALUES(?, ?, ?, ?, ?, ?, ?);";
    String deleteQuery = "DELETE FROM " + tableUser + " WHERE id = ?;";

    public String updateQuery(String column) {
        return "UPDATE " + tableUser + " SET " + column + " = ? WHERE id = ?;";
    }

    static Logging logger = new Logging();

    public static PreparedStatement getStm() {
        try {
            PreparedStatement prStm = GetConnect.getConnection().prepareStatement("");
            return prStm;
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
            return null;
        }
    }

    @Override
    public void create(String username, String fullName, Calendar dob, String address, String numberPhone) {
        PreparedStatement stm = CrudUserService.getStm();
        if (stm != null) {
            // stm.clearParameters();
            // stm.
        }
    }

    @Override
    public User getUser(long id) {
        
        return null;
    }

    @Override
    public List<User> getUsers(long[] id) {
        
        return null;
    }

    @Override
    public boolean find(long id) {
        
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean update(long id, String username) {
        return false;
    }

    @Override
    public boolean update(long id, String username, String fullName) {
        return false;
    }

    @Override
    public boolean update(long id, String username, String fullName, String address) {
        return false;
    }

    @Override
    public boolean update(long id, String username, String fullName, String address, String nummberPhone) {
        return false;
    }

    @Override
    public boolean update(long id, boolean sex) {
        return false;
    }

    @Override
    public boolean update(long id, Date dob) {
        return false;
    }

    @Override
    public boolean find(String username) {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public boolean delete(User user) {
       
        return false;
    }

    @Override
    public boolean delete(long id) {
       
        return false;
    }

    @Override
    public void run() {
       
        
    }
    
}
