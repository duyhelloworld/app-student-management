package controller;

import java.sql.Date;
import java.util.List;
import java.util.TreeMap;

import model.User;

public interface CrudUser {
    
    public void save(String username, String fullName, boolean sex, String dob, String address, String numberPhone);
    
    public User getUser(Long id);
    
    public TreeMap<String, User> getUsers();

    public TreeMap<String, User> getUsers(long start, long end);

    public boolean update(long id, String username);

    public boolean update(long id, String username, String fullName);

    public boolean update(long id, String username, String fullName, String address);
    
    public boolean update(long id, String username, String fullName, String address, String nummberPhone);

    public boolean update(long id, boolean sex);
    
    public boolean update(long id, Date dob);
    
    public boolean find(long id);

    public boolean find(String username);
    
    public boolean delete(List<Long> listIds);
}
