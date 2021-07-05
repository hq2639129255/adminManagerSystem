package com.my.bean;

public class User {
    /*
    * username VARCHAR(11),
u_password VARCHAR(32),
a_id TINYINT  COMMENT'权限Id',
sta_id TINYINT   COMMENT'状态id'
    *
    * */
    private String username;
    private String password;
    private int au_id;
    private int  status_id;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", au_id=" + au_id +
                ", status_id=" + status_id +
                '}';
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

    public int getAu_id() {
        return au_id;
    }

    public void setAu_id(int au_id) {
        this.au_id = au_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public User(String username, String password, int au_id, int status_id) {

        this.username = username;
        this.password = password;
        this.au_id = au_id;
        this.status_id = status_id;
    }
}
