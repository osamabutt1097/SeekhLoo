package com.example.osamanadeem.seekhloo;

public class NewsLetters {

    public NewsLetters(){

    }
    public NewsLetters(String subject, String messageBody) {
        this.subject = subject;
        this.messageBody = messageBody;
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

    String subject,messageBody;

}
