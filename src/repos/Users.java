package repos;

import java.util.TreeMap;

import model.User;

/**
 * @see users : implement Singleton Design Pattern
 * @save TreeMap<key = username, value = other>
 */


public class Users {
    private static TreeMap<String, User> users = new TreeMap<String, User>();
    private Users() {}

    public static synchronized TreeMap<String, User> getInstance() {
        if (users == null) {
            users = new TreeMap<String, User>();
        }
        return users;
    }

    @Override
    public String toString() {
        return users.toString();
    }
}
