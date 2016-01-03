package com.devcru.journowatch.api.objects;

/**
 * Created by Monitored on 12/25/2015.
 * User model class (POJO)
 */

public class User {

    private String username = null;
    private String password = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
