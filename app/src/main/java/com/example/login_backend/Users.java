package com.example.login_backend;
//class connecting user information to RTD
public class Users {
    String username,password;
    boolean admin;

    public Users(String username,String password,boolean admin) {
        this.username=username;
        this.password = password;
        this.admin = admin;
    }

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

    public boolean getUsertype() {
        return admin;
    }

    public void setUsertype(boolean admin) {
        this.admin = admin;
    }
}
