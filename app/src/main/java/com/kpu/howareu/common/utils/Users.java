package com.kpu.howareu.common.utils;

public class Users {

    public int id;
    public String userId;
    public String name;
    public int age;
    public long date;
    public String phone;
    public String school;
    public String type;
    public String typeCheck;
    public int rating;
    public int mentoring;

    public Users() {}

    public Users(int id, String userId, String name, int age, long date, String phone, String school) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.date = date;
        this.phone = phone;
        this.school = school;
    }

}
