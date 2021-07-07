package com.my.service;

import com.my.bean.CardType;
import com.my.bean.Page;
import com.my.bean.VipCardView;
import com.my.bean.Vipinfo;

import java.sql.Connection;
import java.util.List;

public interface VipCardViewService {
    List<VipCardView> showCardList();
    List<CardType> initCardList();
    boolean addcard(int cardtype,Vipinfo vip);
    Page<VipCardView>  showCurrentVipCardView(int offset, int rowcount);


}
