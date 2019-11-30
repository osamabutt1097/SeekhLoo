package com.example.osamanadeem.seekhloo;




public class requests {

     String name;
     String studentid;
     String token;
     String userCount;


     public  requests()
     {}


    public requests(String name, String studentid, String token, String userCount) {
        this.name = name;
        this.studentid = studentid;
        this.token = token;
        this.userCount = userCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }


}

