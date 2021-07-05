package com.my.exception;

public class UserIsLock extends  Exception {
    public UserIsLock() {
    }

    public UserIsLock(String message) {
        super(message);
    }
}
