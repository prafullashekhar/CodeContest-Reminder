package com.underdogdeveloper.codecontests.model;

public class AlarmModel {
    int sno,hour,minute,alarmState;
    String date;

    public AlarmModel(){

    }

    public AlarmModel(int hour, int minute, int alarmState, String date){
        this.hour = hour;
        this.minute = minute;
        this.alarmState = alarmState;
        this.date = date;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(int alarmState) {
        this.alarmState = alarmState;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
