package com.example.osamanadeem.seekhloo;

public class resourcesinfo {
    resourcesinfo()
    {
        this.path = null;
        this.sid = null;
    }
    resourcesinfo(String path,String sid)
        {
            this.path = path;
            this.sid = sid;
        }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String path;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    String sid;
}
