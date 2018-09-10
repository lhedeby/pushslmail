package com.example.mail.objects;

public class DBdata {
    public String mail;
    public String journeynumber;
    public String timebeforeleavning;
    public String date;
    public String siteInfo;

    @Override
    public String toString() {
        return "DBdata{" +
                "mail='" + mail + '\'' +
                ", journeynumber='" + journeynumber + '\'' +
                ", timebeforeleavning='" + timebeforeleavning + '\'' +
                ", date='" + date + '\'' +
                ", SiteInfo='" + siteInfo + '\'' +
                '}';
    }

    public DBdata(String mail, String journeynumber, String timebeforeleavning, String date, String siteInfo) {
        this.mail = mail;
        this.journeynumber = journeynumber;
        this.timebeforeleavning = timebeforeleavning;
        this.date = date;
        this.siteInfo = siteInfo;
    }
}
