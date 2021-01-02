package com.party_org.demo.service;

import com.party_org.demo.dao.FileUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.service
 * Created on 2020/9/8-10:52 下午
 */
@Service
public class FileUserService {
    @Autowired
    FileUserDao fileUserDao;
}
