package com.hhmhieu.vnuklmslogin.Model;

/**
 * Created by Tran Viet Thanh on 10/22/2017.
 */

// ~/Users/[username]-[password]

public class User {
    private int id;
    private String userName;
    private String passWord;
    private int access; // 0 - student, 1 - teacher

    public User() {
    }

    public User(int id, String userName, String passWord, int access) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.access = access;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}
