package com.example.osamanadeem.seekhloo;

class Requestclass {

    String name;
    String token;
    String userCount;

    Requestclass()
    {

    }


    Requestclass(String name, String token, String userCount,String studentid)
    {


        this.name = name;
        this.studentid=studentid;
        this.token=token;
        this.userCount=userCount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    String studentid;
}
