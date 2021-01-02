package com.party_org.demo.service;

import com.party_org.demo.dao.FileUserDao;
import com.party_org.demo.dao.PartyInfoDao;
import com.party_org.demo.entity.FileUser;
import com.party_org.demo.entity.PartyInfo;
import com.party_org.demo.entity.UInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.service
 * Created on 2020/4/22-5:40 下午
 */
@Service
public class PartyInfoService {
    PartyInfo partyInfo=new PartyInfo();
    FileUser fileUser;
    @Autowired
    PartyInfoDao partyInfoDao;
    @Autowired
    FileUserDao fileUserDao;
    public void up(int uid,int fileid){
        partyInfo=partyInfoDao.findById(uid);
        fileUser=fileUserDao.findById(fileid);
        partyInfo.setInParty(1);
        partyInfo.setWait(0);
        fileUser.setWait(0);
        fileUserDao.saveAndFlush(fileUser);
        partyInfoDao.saveAndFlush(partyInfo);
    }
}
