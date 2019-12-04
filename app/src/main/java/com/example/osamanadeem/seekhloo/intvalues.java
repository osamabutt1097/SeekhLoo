package com.example.osamanadeem.seekhloo;

public class intvalues {
    static int city;
    static int catagory;
    static int time;
    static double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    static int type;

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    static String t_id;

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCatagory() {
        return catagory;
    }

    public void setCatagory(int catagory) {
        this.catagory = catagory;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public intvalues() {
    }

    public intvalues(int city, int catagory, int time,int type,String t_id,double distance) {
        this.city = city;
        this.catagory = catagory;
        this.time = time;
        this.t_id=t_id;
        this.type=type;
        this.distance=distance;
    }
}
