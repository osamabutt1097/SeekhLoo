package com.example.osamanadeem.seekhloo;

public class resourcesinfo {
    resourcesinfo()
    {

        this.path = null;
        this.sid = null;
    }
    resourcesinfo(String path,String sid,String filename)
        {
            this.path = path;
            this.sid = sid;
            this.filename=filename;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    String filename;
}
