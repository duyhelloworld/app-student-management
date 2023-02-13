package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.GetConnect;
import exceptions.MultiUserSameIdException;
import exceptions.SomeMistakeInAListIdException;
import logging.Logging;
import model.User;
import repos.ListUser;

public class CrudUserService implements CrudUser {
    String tableName = "`User`", dbNAme = "`App_Console`", tableUser = dbNAme + "." + tableName;
    String viewAllQuery = "SELECT * FROM " + tableUser + ";";
    String viewRangeQuery = "SELECT * FROM " + tableUser + " WHERE id IN (?, ?);";
    String viewOnlyQuery = "SELECT * FROM " + tableUser + " WHERE id = ?;";
    String createQuery = "INSERT INTO " + tableUser + " VALUES( DEFAULT, ?, ?, ?, ?, ?, ?);";
    String deleteQuery = "DELETE FROM " + tableUser + " WHERE id = ?;";

    List<User> list = ListUser.getInstance();
    Calendar _dob = Calendar.getInstance();

    public String updateQuery(String column) {
        return "UPDATE " + tableUser + " SET " + column + " = ? WHERE id = ?;";
    }

    static Logging logger = new Logging();
    static Connection conn = GetConnect.getConnection();

    @Override
    public void create(String username, String fullName, boolean sex, String dob, String address, String numberPhone) {
        try {
            PreparedStatement stm = conn.prepareStatement(createQuery);
            if (stm == null) {
                return;
            }
            stm.setString(1, username);
            stm.setString(2, fullName);
            stm.setInt(3, (sex ? 1 : 0));
            stm.setString(4, dob);
            stm.setString(5, address);
            stm.setString(6, numberPhone);

            if (!find(username)) {
                int numberOfRecordChanged = stm.executeUpdate();
                Logging.writeLog(logger.sqlSuccess(createQuery, true, numberOfRecordChanged));
            } else {
                Logging.writeLog(logger.sqlSuccess(createQuery, false, 0));
            }
        }
        catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        }
    }

    @Override
    public User getUser(long id) {
        try {
            PreparedStatement stm = conn.prepareStatement(viewOnlyQuery);
            stm.setLong(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.getRow() > 1) {
                throw new MultiUserSameIdException("Some users has same id in DB.\n");
            } else {
                rs.next();
                
                Date dob = rs.getDate("dob");
                Logging.writeLog(dob.toString());
                _dob.setTime(dob);

                return new User(rs.getLong("id"), rs.getString("user_name"), rs.getString("full_name"), _dob,
                        rs.getString("address"), rs.getString("number_phone"));
            }
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        } catch (MultiUserSameIdException e) {
            Logging.writeLog(logger.getError(e));
        }
        return null;
    }

    @Override
    public List<User> getUsers(long start, long end) {
        try {
            PreparedStatement stm = conn.prepareStatement(viewRangeQuery);
            stm.setLong(1, start);
            stm.setLong(2, end);
            ResultSet rs = stm.executeQuery();
            if (rs.getRow() == (end - start)) {
                throw new SomeMistakeInAListIdException("Some users has mistake ids from " + start + " to " + end + " in DB.\n");
            } else {
                
                while (rs.next()) {
                    Date dob = rs.getDate("dob");
                    _dob.setTime(dob);
                    list.add(new User(rs.getLong("id"), rs.getString("user_name"), rs.getString("full_name"), _dob,
                            rs.getString("address"), rs.getString("number_phone")));
                }
                Logging.writeLog(logger.view(start, end));
                return list;
            }
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        } catch (SomeMistakeInAListIdException e) {
            Logging.writeLog(logger.getError(e));
        }
        return null;
    }

    @Override
    public boolean find(long id) {
        if (getUser(id) != null) {
            Logging.writeLog(logger.view(id, id));
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        if (!find(user.getId())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean update(long id, String username) {
        if (!find(id)) {
            return false;
        } else {
            try {
                PreparedStatement stm = conn.prepareStatement(updateQuery("username"));
                stm.setString(1, username);
                stm.setLong(2, id);
                if (stm.executeUpdate() == 1) {
                    Logging.writeLog(logger.update("user_name", id));
                    return true;
                }
            } catch (SQLException e) {
                Logging.writeLog(logger.getError(e));
            }
            return false;
        }
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
}
