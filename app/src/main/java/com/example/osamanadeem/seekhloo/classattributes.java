package com.example.osamanadeem.seekhloo;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class classattributes implements Serializable {
    classattributes() {
        this.weekdayList = null;
        this.type=null;
        this.time=null;
        this.catagory=null;
        this.Description=null;
        this.list=null;
        this.name=null;

    }

    public classattributes(String name, String catagory, List<String> list, String time, String type, List<MaterialDayPicker.Weekday> weekdayList) {
        this.name = name;
        this.catagory = catagory;
        this.list = list;
        this.time = time;
        this.type = type;
        this.weekdayList = weekdayList;
    }
    public classattributes(String name, String catagory, List<String> list, String time, String type, List<MaterialDayPicker.Weekday> weekdayList,String Description,String assignedtutor,String requestedTutor) {
        this.name = name;
        this.catagory = catagory;
        this.list = list;
        this.time = time;
        this.type = type;
        this.weekdayList = weekdayList;
        this.Description = Description;
        this.requestedtutor = requestedTutor;
        this.tutor=assignedtutor;
    }

    public classattributes(String name, String catagory, List<String> list, String time, String type, List<MaterialDayPicker.Weekday> weekdayList,String Description,String assignedtutor,String requestedTutor,String student_id) {
        this.name = name;
        this.catagory = catagory;
        this.list = list;
        this.time = time;
        this.type = type;
        this.weekdayList = weekdayList;
        this.Description = Description;
        this.requestedtutor = requestedTutor;
        this.tutor=assignedtutor;
        this.student_id=student_id;
    }

    public classattributes(String name, String catagory, List<String> list, String time, String type, String Description,String assignedtutor,String requestedTutor,String student_id) {
        this.name = name;
        this.catagory = catagory;
        this.list = list;
        this.time = time;
        this.type = type;
        this.Description = Description;
        this.requestedtutor = requestedTutor;
        this.tutor=assignedtutor;
        this.student_id=student_id;
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

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    String student_id;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    String Description;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    List<String> list = new ArrayList<String>();
    String tutor;

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getRequestedtutor() {
        return requestedtutor;
    }

    public void setRequestedtutor(String requestedtutor) {
        this.requestedtutor = requestedtutor;
    }

    String requestedtutor;



    ////////////////DAYS


    public List<MaterialDayPicker.Weekday> getWeekdayList() {
        return weekdayList;
    }

    public void setWeekdayList(List<MaterialDayPicker.Weekday> weekdayList) {
        this.weekdayList = weekdayList;
    }

    List<MaterialDayPicker.Weekday> weekdayList = new ArrayList<MaterialDayPicker.Weekday>();




}