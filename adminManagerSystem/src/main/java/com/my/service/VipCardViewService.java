package com.my.service;

import com.my.bean.*;
import com.my.dao.SetmealDao;

import java.sql.Connection;
import java.util.List;

public interface VipCardViewService {
    List<VipCardView> showCardList();
    List<CardType> initCardList();
    boolean addcard(int cardtype,Vipinfo vip);
    Page<VipCardView>  showCurrentVipCardView(int offset, int rowcount);
    boolean updatecardinfo(VipCardView v);
    List<VipCardView> showFacilityByParameter(VipCardView vipCardView);
    List<Setmeal> initSetmeal();
    boolean buyservice(boolean flag, int daycount,int id);

}
