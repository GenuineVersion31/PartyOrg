package com.party_org.demo.service;

import com.party_org.demo.dao.PorterDao;
import com.party_org.demo.entity.Porter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.service
 * Created on 2020/9/9-2:10 下午
 */
@Service
public class PorterService {
    @Autowired
    PorterDao porterDao;
    public Porter PorterSave(int uid,String porter,String title){
        Date date=new Date();
        Porter porterMy=new Porter();
        porterMy.setUid(uid);
        porterMy.setPorter(porter);
        porterMy.setTitle(title);
        String currTime = DateFormat.getDateTimeInstance().format(date);
        porterMy.setCreateTime(currTime);
        return porterDao.saveAndFlush(porterMy);
    }
}
