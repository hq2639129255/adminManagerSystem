package com.my.service;

import java.sql.Connection;

public interface UserService {
   byte login(String usernaem,String password,int role);
}
