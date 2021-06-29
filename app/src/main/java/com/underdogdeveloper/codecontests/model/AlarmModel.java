package com.underdogdeveloper.codecontests.model;

public class AlarmModel {
    int sno,year, month, date, hour,minute,alarmState;
    String strDate = "";
    Contest contest;

    public AlarmModel(){
    }

//    public AlarmModel(int hour, int minute, int alarmState, String strDate, Contest contest){
//        this.hour = hour;
//        this.minute = minute;
//        this.alarmState = alarmState;
//        this.strDate = strDate;
//        this.contest = contest;
//    }

    // Sting startTime format = "2021-08-01T06:55:00.000Z"
    public AlarmModel(Contest contest){
        year = Integer.parseInt(contest.getStart_time().substring(0, 4));
        month = Integer.parseInt(contest.getStart_time().substring(5, 7));
        date = Integer.parseInt(contest.getStart_time().substring(8, 10));
        hour = Integer.parseInt(contest.getStart_time().substring(11, 13));
        minute = Integer.parseInt(contest.getStart_time().substring(14, 16));

        this.contest = contest;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public Contest getContest(){
        return contest;
    }

    public  void setContest(Contest contest){
        this.contest = contest;
    }

}
