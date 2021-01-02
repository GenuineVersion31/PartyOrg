package com.party_org.demo.dao;

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
 * Created on 2020/4/5-6:21 下午
 */
@org.springframework.stereotype.Repository
public interface UsersDao extends JpaRepository<Users,Integer>{
    void deleteByUsername(String username);
    Optional<Users> findByUsernameAndPassword(String username, String password);
    Users findByUsername(String username);
    List<Users> findAll();
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.password =?1where u.username = ?2",nativeQuery = true)
    int updatePassword(String password,String username);
}
