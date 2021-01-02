package com.party_org.demo.dao;

import com.party_org.demo.entity.UInfo;
import com.party_org.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.dao
 * Created on 2020/4/20-12:29 上午
 */
@org.springframework.stereotype.Repository
public interface UInfoDao extends JpaRepository<UInfo,Integer> {
    Optional<UInfo> findByUsername(String username);
    Optional<UInfo> findById(int id);
    List<UInfo> findAll();
}
