package com.kanilturgut.phonemarketapp.backend.pojo;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 18:28
 */
public class LoginPojo {

    private String email;
    private String password;

    public LoginPojo(String email, String password) {
        this.email = email;
        this.password = password;
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
}
