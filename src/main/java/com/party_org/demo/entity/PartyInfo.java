package com.party_org.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.entity
 * Created on 2020/4/20-1:26 上午
 */
@Entity
public class PartyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false ,length = 5)
    private int id;
    @Column(nullable = true ,length = 10)
    private String name;
    @Column(nullable = true ,length = 16)
    private Date birthday;
    @Column(nullable = true ,length = 1)
    private int wait;
    @Column(nullable = true ,length = 1)
    private int inParty;
    @Column(nullable = true ,length = 1)
    private String race;
    @Column(nullable = true ,length = 20)
    private String major;
    @Column(nullable = true ,length = 30)
    private String identificaionNumber;
    @Column(nullable = true ,length = 18)
    private String classnumber;
    @Column(nullable = true ,length = 2)
    private String gender;

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getInParty() {
        return inParty;
    }

    public void setInParty(int inParty) {
        this.inParty = inParty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdentificaionNumber() {
        return identificaionNumber;
    }

    public void setIdentificaionNumber(String identificaionNumber) {
        this.identificaionNumber = identificaionNumber;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public PartyInfo(){
        this.setWait(1);
        this.setInParty(0);
    }
}
