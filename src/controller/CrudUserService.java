package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import dao.GetConnect;
import exceptions.MultiUserSameIdException;
import exceptions.SomeMistakeInAListIdException;
import logging.Logging;
import model.User;
import repos.Users;

public class CrudUserService implements CrudUser {
    String tableName = "`User`", dbNAme = "`App_Console`", tableUser = dbNAme + "." + tableName;

    String viewAllQuery = "SELECT * FROM " + tableUser + ";";
    String viewRangeQuery = "SELECT * FROM " + tableUser + " WHERE id IN (?, ?);";
    String viewOnlyQuery = "SELECT * FROM " + tableUser + " WHERE id = ?;";

    String createQuery = "INSERT INTO " + tableUser + " VALUES( DEFAULT, ?, ?, ?, ?, ?, ?);";
    String deleteOnlyQuery = "DELETE FROM " + tableUser + " WHERE id = ?;";

    
    TreeMap<String, User> treeUser = Users.getInstance();
    Calendar _dob = Calendar.getInstance();
    
    public String updateQuery(String column) {
        return "UPDATE " + tableUser + " SET " + column + " = ? WHERE id = ?;";
    }
    
    static Logging logger = new Logging();
    static Connection conn = GetConnect.getConnection();
    
    public String deleteRangeQuery(List<Long> listIds) {
        return "DELETE FROM " + tableUser + " WHERE id IN (" + logger.getRangeId(listIds) +");";
    }
    
    @Override
    public void save(String username, String fullName, boolean sex, String dob, String address, String numberPhone) {
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
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        }
    }

    @Override
    public User getUser(Long id) {
        try {
            PreparedStatement stm = conn.prepareStatement(viewOnlyQuery);
            stm.setLong(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.getRow() > 1) {
                throw new MultiUserSameIdException("Some users has same id in DB.\n");
            } else {
                if (rs.next()) {
                    Date dob = rs.getDate("dob");
                    Logging.writeLog(dob.toString());
                    _dob.setTime(dob);

                    return new User(rs.getLong("id"), rs.getString("user_name"), rs.getString("full_name"), _dob,
                            rs.getString("address"), rs.getString("number_phone"));
                }
                return null;
            }
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        } catch (MultiUserSameIdException e) {
            Logging.writeLog(logger.getError(e));
        }
        return null;
    }

    @Override
    public TreeMap<String, User> getUsers(long start, long end) {
        try {
            PreparedStatement stm = conn.prepareStatement(viewRangeQuery);
            stm.setLong(1, start);
            stm.setLong(2, end);
            ResultSet rs = stm.executeQuery();
            if (rs.getRow() == (end - start)) {
                throw new SomeMistakeInAListIdException(
                        "Some users has mistake ids from " + start + " to " + end + " in DB.\n");
            } else {

                while (rs.next()) {
                    Date dob = rs.getDate("dob");
                    _dob.setTime(dob);
                    treeUser.put(
                            rs.getString("user_name"),
                            new User(rs.getLong("id"),
                                    rs.getString("full_name"),
                                    _dob,
                                    rs.getString("address"),
                                    rs.getString("number_phone")));
                }
                Logging.writeLog(logger.view(start, end));
                return treeUser;
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

    boolean updateOneColumn(long id, String columnName, String value) {
        if (!find(id)) {
            return false;
        } else {
            try {
                PreparedStatement stm = conn.prepareStatement(updateQuery(columnName));
                stm.setString(1, value);
                stm.setLong(2, id);
                if (stm.executeUpdate() == 1) {
                    Logging.writeLog(logger.update(columnName, id));
                    return true;
                }
            } catch (SQLException e) {
                Logging.writeLog(logger.getError(e));
            }
            return false;
        }
    }

    @Override
    public boolean update(long id, String username) {
        return updateOneColumn(id, "user_name", username);
    }

    @Override
    public boolean update(long id, String username, String fullName) {
        return updateOneColumn(id, "full_name", fullName);
    }

    @Override
    public boolean update(long id, String username, String fullName, String address) {
        return updateOneColumn(id, "address", address);
    }

    @Override
    public boolean update(long id, String username, String fullName, String address, String nummberPhone) {
        return updateOneColumn(id, "number_phone", nummberPhone);
    }

    @Override
    public boolean update(long id, boolean sex) {
        if (!find(id)) {
            return false;
        } else {
            try {
                PreparedStatement stm = conn.prepareStatement(updateQuery("sex"));
                stm.setBoolean(1, sex);
                stm.setLong(2, id);
                if (stm.executeUpdate() == 1) {
                    Logging.writeLog(logger.update("sex", id));
                    return true;
                }
            } catch (SQLException e) {
                Logging.writeLog(logger.getError(e));
            }
            return false;
        }
    }

    @Override
    public boolean update(long id, Date dob) {
        if (!find(id)) {
            return false;
        } else {
            try {
                PreparedStatement stm = conn.prepareStatement(updateQuery("dob"));
                stm.setDate(1, dob);
                stm.setLong(2, id);
                if (stm.executeUpdate() == 1) {
                    Logging.writeLog(logger.update("dob", id));
                    return true;
                }
            } catch (SQLException e) {
                Logging.writeLog(logger.getError(e));
            }
            return false;
        }
    }

    @Override
    public boolean find(String username) {
        if (getUsers().containsKey(username)) {
            Logging.writeLog(logger.viewByUsername(username));
            return true;
        }
        return false;
    }

    // Should not print by id because sql save by AUTO_INCREMENT
    @Override
    public TreeMap<String, User> getUsers() {
        try {
            PreparedStatement stm = conn.prepareStatement(viewAllQuery);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Date dob = rs.getDate("dob");
                _dob.setTime(dob);
                treeUser.put(
                        rs.getString("user_name"),
                        new User(rs.getLong("id"),
                                rs.getString("full_name"),
                                _dob,
                                rs.getString("address"),
                                rs.getString("number_phone")));
            }
            Logging.writeLog(logger.view(0, treeUser.size()));
            return treeUser;
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        }
        return null;
    }

    @Override
    public boolean delete(List<Long> listIds) {
        try {
            PreparedStatement stm = conn.prepareStatement(deleteRangeQuery(listIds));
            if (stm.executeUpdate() == 1) {
                Logging.writeLog(logger.delete(listIds));
                return true;
            }
        } catch (SQLException e) {
            Logging.writeLog(logger.getError(e));
        }
        return false;
    }
}
