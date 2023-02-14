package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User {
    private Long id;
    private String username;
    private String fullName;
    private Calendar dob;
    private String address;
    private String numberPhone;

    public User() {
    }

    public User(Long id, String username, String fullName, Calendar dob, String address, String numberPhone) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.numberPhone = numberPhone;
    }

    // - Use for treeMap
    public User(Long id, String fullName, Calendar dob, String address, String numberPhone) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.numberPhone = numberPhone;
    }

    public User(String username, String fullName, Calendar dob, String address, String numberPhone) {
        this.username = username;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.numberPhone = numberPhone;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Calendar getDob() {
        return this.dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return this.numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User fullName(String fullName) {
        setFullName(fullName);
        return this;
    }

    public User dob(Calendar dob) {
        setDob(dob);
        return this;
    }

    public User address(String address) {
        setAddress(address);
        return this;
    }

    public User numberPhone(String numberPhone) {
        setNumberPhone(numberPhone);
        return this;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd");
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
                ", full name='" + getFullName() + "'" +
                ", date of birthday ='" + sdf.format(getDob().getTime()) +
            ", address = '" + getAddress() + "'" +
            ", number phone = '" + getNumberPhone() + "'" +
            "}";
    }

}
