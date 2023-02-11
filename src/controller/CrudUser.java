package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.User;

public interface CrudUser {
    
    public void create(String username, String fullName, Calendar dob, String address, String numberPhone);
    
    public User getUser(long id);
    
    public List<User> getUsers();

    public List<User> getUsers(long[] id);

    public boolean update(User user);

    public boolean update(long id, String username);

    public boolean update(long id, String username, String fullName);

    public boolean update(long id, String username, String fullName, String address);
    
    public boolean update(long id, String username, String fullName, String address, String nummberPhone);

    public boolean update(long id, boolean sex);
    
    public boolean update(long id, Date dob);
    
    public boolean find(long id);

    public boolean find(String username);

    public boolean delete(User user);
    
    public boolean delete(long id);
}
