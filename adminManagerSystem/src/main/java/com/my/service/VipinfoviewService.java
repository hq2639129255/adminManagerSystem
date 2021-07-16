package com.my.service;

import com.my.bean.*;

import java.sql.Connection;
import java.util.List;

public interface VipinfoviewService {
    Page<Vipinfoview> showCurrentVipinfoview(int offset, int rowcount);
    List<Vipinfoview> findVipinfoviewByParameter(String  phone, String type, String name);
    boolean addviminfo( Vipinfo vip, int type);
    Boolean updatevipinfobyid(Vipinfo vip);
    Vipinfo getCurentVipinfo(String phone);
    boolean isVipinfoByphone(String phone);
    boolean isVipinfoByEmail(String email);

}
