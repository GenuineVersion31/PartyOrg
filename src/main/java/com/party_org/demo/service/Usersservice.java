package com.party_org.demo.service;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.service
 * Created on 2020/4/5-9:28 下午
 */
import com.party_org.demo.dao.UInfoDao;
import com.party_org.demo.dao.UsersDao;
import com.party_org.demo.entity.UInfo;
import com.party_org.demo.entity.Users;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class Usersservice  {
    @Autowired
    UsersDao usersDao;
    @Autowired
    UInfoDao uInfoDao;
//    public void insertUser(com.party_org.demo.entity.Users users){
//        Optional<com.party_org.demo.entity.Users> usersOptional=usersDao.findByUsername(users.getUsername());
//        if (checkUser(users.getUsername(),users.getPassword())){
//            System.out.println("有相同用户");
//            // 处理 foo ...
//        }
//        else{
//            //另一种情况....
//            usersDao.save(users);
//        }
//        usersDao.save(users);
//    }
    public void updateUser(Users users) {
        usersDao.saveAndFlush(users);
    }
    //查询
    public boolean checkUser(String username,String password){
        Users users= usersDao.findByUsername(username);
        if (users.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }
    public Users finduser(String username){
        return usersDao.findByUsername(username);
    }
    public void superUser(int id){
        Optional<Users> a=usersDao.findById(id);
        Optional<UInfo> b=uInfoDao.findById(id);
        a.get().setRoles("ADMIN");
        b.get().setRole("ADMIN");
        usersDao.save(a.get());
        uInfoDao.save(b.get());
        uInfoDao.flush();
        usersDao.flush();
    }
    public void lowerUser(int id){
        Optional<Users> a=usersDao.findById(id);
        Optional<UInfo> b=uInfoDao.findById(id);
        a.get().setRoles("ROLE");
        b.get().setRole("ROLE");
        usersDao.saveAndFlush(a.get());
        uInfoDao.saveAndFlush(b.get());
    }
    public static void fileupload(byte[] file,String filePath,String fileName) throws IOException {
        //目标目录
        File targetfile = new File(filePath);
        if(targetfile.exists()) {
            targetfile.mkdirs();
        }

        //二进制流写入
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
