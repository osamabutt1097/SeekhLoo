package com.example.osamanadeem.seekhloo;

public class UserInfo {
    public UserInfo()
    {

    }
    public UserInfo(String type, String email, String birthday, String firstname, String lastname) {
        this.type = type;
        this.email = email;
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
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

    String type,email,birthday,firstname,lastname;
}
