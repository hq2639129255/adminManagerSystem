package com.my.dao;

import com.my.bean.CardType;

import java.sql.Connection;
import java.util.List;

public interface CardTypeDao {
    List<CardType> findAllCardType(Connection con);

}
