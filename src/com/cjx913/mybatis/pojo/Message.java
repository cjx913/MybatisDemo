package com.cjx913.mybatis.pojo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = -3212220929521671514L;
    private int message_id;
    private String gender;
    private String e_mail;
    private int age;
    private Date birth;
    private int user_id;

    private User user;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", gender='" + gender + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", user_id=" + user_id +
                '}';
    }
}
