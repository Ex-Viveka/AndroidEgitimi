package com.kanilturgut.phonemarketapp.model;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 17:54
 */
public class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public static User me;

    public static User getMe() {
        return me;
    }

    public static void setMe(User me) {
        User.me = me;
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
