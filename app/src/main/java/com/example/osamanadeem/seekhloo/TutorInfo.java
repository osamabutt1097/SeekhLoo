package com.example.osamanadeem.seekhloo;

public class TutorInfo {
    public TutorInfo()
    {

    }
    public TutorInfo(String type, String email, String birthday, String firstname, String lastname, String gender) {
        this.type = type;
        this.email = email;
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.name = name;
    }
    public TutorInfo(String type, String email, String birthday, String firstname, String lastname, String gender,String picUrL) {
        this.type = type;
        this.email = email;
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.name = name;
        this.picUrL = picUrL;
    }

    public TutorInfo(String type, String email, String birthday, String firstname, String lastname, String gender,String picUrL,String id) {
        this.type = type;
        this.email = email;
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.name = name;
        this.picUrL = picUrL;
        this.id=id;
    }

    public TutorInfo(String type, String email, String birthday, String firstname, String lastname, String gender,String picUrL,String id,String tokenid) {
        this.type = type;
        this.email = email;
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.picUrL = picUrL;
        this.id=id;
        this.tokenid = tokenid;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    String type;
    String email;
    String birthday;
    String firstname;
    String lastname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getPicUrL() {
        return picUrL;
    }

    public void setPicUrL(String picUrL) {
        this.picUrL = picUrL;
    }

    String picUrL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    String tokenid;
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    String gender;
}
