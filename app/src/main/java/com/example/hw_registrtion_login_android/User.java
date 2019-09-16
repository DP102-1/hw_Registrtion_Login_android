package com.example.hw_registrtion_login_android;

import java.io.Serializable;

public class User implements Serializable {
    private String username , userpassword ;

    public User(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public User(String username , String userpassword){
        super();
        this.username = username;
        this.userpassword = userpassword;
    }
}
