package com.kanilturgut.phonemarketapp.model;

import java.io.Serializable;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 17:54
 */
public class User {

    private String _id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private static User me;

    public static User getMe() {
        return me;
    }

    public static void setMe(User user) {
        me = user;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
