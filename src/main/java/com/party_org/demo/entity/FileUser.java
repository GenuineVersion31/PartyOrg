package com.party_org.demo.entity;

import javax.persistence.*;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.entity
 * Created on 2020/9/8-10:30 下午
 */
@Entity
public class FileUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false ,length = 5)
    public Integer id;
    @Column(nullable = false ,length = 100)
    public String fileName;
    @Column(nullable = false ,length = 10)
    public int uid;
    @Column(nullable = false ,length = 16)
    public String username;
    @Column(nullable = true ,length = 1)
    private int wait;

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false ,length = 100)
    public String path;
    @Column(nullable = false)
    public String dsc;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int Uid) {
        uid = Uid;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

}
