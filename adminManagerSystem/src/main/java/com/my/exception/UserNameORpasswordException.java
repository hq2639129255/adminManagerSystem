package com.my.exception;

public class UserNameORpasswordException extends  Exception {
    public UserNameORpasswordException(String msg) {
        super(msg);
    }

    public UserNameORpasswordException() {
    }
}
