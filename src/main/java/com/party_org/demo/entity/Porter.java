package com.party_org.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.entity
 * Created on 2020/9/9-1:54 下午
 */
@Entity
public class Porter {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPorter() {
        return porter;
    }

    public void setPorter(String porter) {
        this.porter = porter;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int uid;
    private String title;
    private String porter;
    @Column(name="CREATE_TIME")
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String createTime;
    private int type;
}
