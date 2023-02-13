package repos;

import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * @see users : implement Singleton Design Pattern
 */


public class ListUser {
    private static List<User> users = new ArrayList<User>();
    private ListUser() {}

    public static synchronized List<User> getInstance() {
        if (users == null) {
            users = new ArrayList<User>();
        }
        return users;
    }

    @Override
    public String toString() {
        return users.toString();
    }
}
