package com.example.mail.objects;

public class RealTimeBusesAndMetros {
    public String TransportMode;
    public String GroupOfLine;
    public String DisplayTime;
    public String LineNumber;
    public String Destination;
    public String JourneyDirection;
    public String StopAreaName;
    public String TimeTableDateTime;
    public String ExpectedDateTime;
    public String JourneyNumber;

    @Override
    public String toString() {
        return "RealTimeBusesAndMetros{" +
                "TransportMode='" + TransportMode + '\'' +
                ", GroupOfLine='" + GroupOfLine + '\'' +
                ", DisplayTime='" + DisplayTime + '\'' +
                ", LineNumber='" + LineNumber + '\'' +
                ", Destination='" + Destination + '\'' +
                ", JourneyDirection='" + JourneyDirection + '\'' +
                ", StopAreaName='" + StopAreaName + '\'' +
                ", TimeTableDateTime='" + TimeTableDateTime + '\'' +
                ", ExpectedDateTime='" + ExpectedDateTime + '\'' +
                ", JourneyNumber='" + JourneyNumber + '\'' +
                '}';
    }
}
