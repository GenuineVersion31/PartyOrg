package com.party_org.demo.dao;

import com.party_org.demo.entity.PartyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.dao
 * Created on 2020/4/22-3:04 下午
 */
@Repository
public interface PartyInfoDao extends JpaRepository<PartyInfo,Integer> {
    PartyInfo findById(int id);
    List<PartyInfo> findAllByInParty(int inparty);
}
