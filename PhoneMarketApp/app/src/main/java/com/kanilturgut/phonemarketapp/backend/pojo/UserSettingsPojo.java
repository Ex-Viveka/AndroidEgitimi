package com.kanilturgut.phonemarketapp.backend.pojo;

/**
 * Author   : kanilturgut
 * Date     : 23/10/15
 * Time     : 16:28
 */
public class UserSettingsPojo {

    private String userId;
    private String firstName;
    private String lastName;
    private String password;

    public UserSettingsPojo(String _id, String firstName, String lastName, String password) {
        this.userId = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String get_id() {
        return userId;
    }

    public void set_id(String _id) {
        this.userId = _id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
