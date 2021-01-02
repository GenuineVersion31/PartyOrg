package com.party_org.demo.dao;

import com.party_org.demo.entity.Porter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.dao
 * Created on 2020/9/9-2:08 下午
 */
@Repository
public interface PorterDao extends JpaRepository<Porter,Integer> {

}
