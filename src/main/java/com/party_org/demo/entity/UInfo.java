package com.party_org.demo.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.entity
 * Created on 2020/4/19-7:24 下午
 */
@Entity
@Table(name = "information")
public class UInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false ,length = 5)
    private int id;
    @Column(nullable = true ,length = 10)
    private String name;
    @Column(nullable = false ,length = 16)
    private String username;
    @Column(nullable = false ,length = 16)
    private String password;
    @Column(nullable = true ,length = 10)
    private String race;
    @Column(nullable = true ,length = 20)
    private String major;
    @Column(nullable = true ,length = 30)
    private String identificaionNumber;
    @Column(nullable = true ,length = 18)
    private String classnumber;
    @Column(nullable = true ,length = 10)
    private String roles;
    @Column(nullable = true ,length = 2)
    private String gender;
    private Date birthday;

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoles() {
        return roles;
    }

    public void setRole(String roles) {
        this.roles = roles;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public UInfo(){
        this.setGender("");
        this.setIdentificaionNumber("");
        this.setRole("ROLE");
        this.setMajor("");
        this.setRace("");
        this.setClassnumber("");
        this.setName("");
    }
}
