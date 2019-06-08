package com.example.osamanadeem.seekhloo;

public class NewsLetters {

    public NewsLetters(){

    }
    public NewsLetters(String subject, String messageBody,String picurl) {
        this.subject = subject;
        this.messageBody = messageBody;
        this.picurl = picurl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    String subject;
    String messageBody;

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    String picurl;

}
