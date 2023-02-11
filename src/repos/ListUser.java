package repos;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class ListUser {
    private List<User> users = new ArrayList<User>();

    public ListUser() {
    }

    public ListUser(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public ListUser users(List<User> users) {
        setUsers(users);
        return this;
    }

    @Override
    public String toString() {
        return users.toString();
    }
}
