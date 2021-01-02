package com.party_org.demo.service;

import com.party_org.demo.entity.UInfo;
import com.party_org.demo.entity.Users;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.service
 * Created on 2020/4/20-1:05 上午
 */
@Service
public class UInfoService {
    public void check(Optional<UInfo> uInfo){
        if(uInfo.get().getIdentificaionNumber().isEmpty()){uInfo.get().setIdentificaionNumber("");}
        if(uInfo.get().getMajor().isEmpty()){uInfo.get().setMajor("");}
        if(uInfo.get().getRace().isEmpty()){uInfo.get().setRace("");}
        if(uInfo.get().getClassnumber().isEmpty()){uInfo.get().setRace("");}
    }
    public void checkUser(Users users, Optional<UInfo> uInfo){
        uInfo.get().setId(users.getId());
        uInfo.get().setPassword(users.getPassword());
        uInfo.get().setUsername(users.getUsername());
    }
    public boolean checkBirth(Date birthday){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        System.out.println(year-calendar.get(Calendar.YEAR));
        if(year-calendar.get(Calendar.YEAR)>=18){
            return true;
        }else return false;
    }
}
