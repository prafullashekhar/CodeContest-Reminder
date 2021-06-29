package com.underdogdeveloper.codecontests.model;

public class AlarmModel {
    int sno=0,year, month, date, hour,minute,alarmState;
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
        this.contest = contest;
        this.hour = Integer.parseInt(contest.getStart_time().substring(11, 13));
        this.minute = Integer.parseInt(contest.getStart_time().substring(14, 16));
        this.date=Integer.parseInt(contest.getStart_time().substring(8, 10));
        this.month=Integer.parseInt(contest.getStart_time().substring(5, 7));
        this.year=Integer.parseInt(contest.getStart_time().substring(0, 4));
        strDate=contest.getStart_time().substring(8, 10)+"-"+contest.getStart_time().substring(5, 7)+"-"+contest.getStart_time().substring(0, 4);
    }

    public AlarmModel(int hour, int minute, String strDate, Contest contest) {
        this.hour = hour;
        this.minute = minute;
        this.contest = contest;
        this.date=Integer.parseInt(strDate.substring(0,2));
        this.month=Integer.parseInt(strDate.substring(3,5));
        this.year=Integer.parseInt(strDate.substring(6,10));
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

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
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
        this.date=Integer.parseInt(strDate.substring(0,2));
        this.month=Integer.parseInt(strDate.substring(3,5));
        this.year=Integer.parseInt(strDate.substring(6,10));
    }

    public Contest getContest(){
        return contest;
    }

    public  void setContest(Contest contest){
        this.contest = contest;
    }

}
