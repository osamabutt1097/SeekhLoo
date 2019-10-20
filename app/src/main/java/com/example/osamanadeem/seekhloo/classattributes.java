package com.example.osamanadeem.seekhloo;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class classattributes {
    classattributes() {

    }

    public classattributes(String name, String catagory, List<String> list, String time, String type, List<MaterialDayPicker.Weekday> weekdayList) {
        this.name = name;
        this.catagory = catagory;
        this.list = list;
        this.time = time;
        this.type = type;
        this.weekdayList = weekdayList;
    }

    classattributes(String name, String catagory, String time, String type) {
        this.name = name;
        this.catagory = catagory;
        this.time = time;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    String name;
    String catagory;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    List<String> list = new ArrayList<String>();




    ////////////////DAYS


    public List<MaterialDayPicker.Weekday> getWeekdayList() {
        return weekdayList;
    }

    public void setWeekdayList(List<MaterialDayPicker.Weekday> weekdayList) {
        this.weekdayList = weekdayList;
    }

    List<MaterialDayPicker.Weekday> weekdayList = new ArrayList<MaterialDayPicker.Weekday>();




}