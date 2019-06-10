package com.example.osamanadeem.seekhloo;

import java.util.ArrayList;
import java.util.List;

public class classattributes {
            classattributes(){

            }

    public classattributes(String name, String type, List<String> list) {
        this.name = name;
        this.type = type;
        this.list = list;
    }

    classattributes(String name, String type)
            {
                this.name = name;
                this.type = type;
            }
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            String name,type;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    List<String> list = new ArrayList<String>();

}
