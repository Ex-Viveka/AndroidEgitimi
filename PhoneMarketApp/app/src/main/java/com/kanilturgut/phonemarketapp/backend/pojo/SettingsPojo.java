package com.kanilturgut.phonemarketapp.backend.pojo;

/**
 * Author   : kanilturgut
 * Date     : 23/10/15
 * Time     : 17:03
 */
public class SettingsPojo {

    private String userId;

    public SettingsPojo(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
