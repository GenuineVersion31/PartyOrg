package com.party_org.demo.dao;

import com.party_org.demo.entity.FileUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.dao
 * Created on 2020/9/8-10:35 下午
 */
@Repository
public interface FileUserDao extends JpaRepository<FileUser,Integer> {
    List<FileUser> findAll();
    List<FileUser> findAllByWait(int wait);
    FileUser findById(int id);
}
