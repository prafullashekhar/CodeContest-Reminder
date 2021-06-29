package com.underdogdeveloper.codecontests.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.underdogdeveloper.codecontests.model.AlarmModel;
import com.underdogdeveloper.codecontests.model.Contest;

public class DbHandler extends SQLiteOpenHelper {
    private static final String dbName="alarmDb";
    private static final int version=1;

    public DbHandler(Context context){
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        Creatting the alarm table in sql
        String sql="CREATE TABLE ALARM (sno INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SITE TEXT,URL TEXT, HOUR INTEGER, MINUTE INTEGER, STRDATE TEXT,ALARMSTATE INTEGER)";
        db.execSQL(sql);
    }
    public void addAlarm(AlarmModel alarm){

//        Insert the alarm data in sql database
        SQLiteDatabase database=this.getWritableDatabase();
        Contest contest=alarm.getContest();
        ContentValues values=new ContentValues();
        values.put("NAME",contest.getName());
        values.put("SITE",contest.getSite());
        values.put("URL",contest.getUrl());
        values.put("HOUR",alarm.getHour());
        values.put("MINUTE",alarm.getMinute());
        values.put("STRDATE",alarm.getStrDate());
        values.put("ALARMSTATE",alarm.getAlarmState());
        long k=database.insert("ALARM",null,values);
        Log.d("MyTag",Long.toString(k));
        database.close();
    }
    public AlarmModel getAlarm(int sno){

//        get the alarm data from database
        SQLiteDatabase database=this.getReadableDatabase();
        Contest contest=new Contest();
        AlarmModel alarmModel=new AlarmModel();
        Cursor cursor=database.query("ALARM",new String[]{"sno,NAME,SITE,URL,HOUR,MINUTE,STRDATE,ALARMSTATE"},"sno=?",new String[]{String.valueOf(sno)},null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            alarmModel.setSno(cursor.getInt(0));
            contest.setName(cursor.getString(1));
            contest.setSite(cursor.getString(2));
            contest.setUrl(cursor.getString(3));
            alarmModel.setHour(cursor.getInt(4));
            alarmModel.setMinute(cursor.getInt(5));
            alarmModel.setAlarmState(cursor.getInt(7));
            alarmModel.setStrDate(cursor.getString(6));
            alarmModel.setContest(contest);
        }
        return alarmModel;
    }

//    update the alarm in database
    public void updateAlarm(AlarmModel alarmModel){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("HOUR",alarmModel.getHour());
        values.put("MINUTE",alarmModel.getMinute());
        values.put("DATE",alarmModel.getDate());
        values.put("ALARMSTATE",alarmModel.getAlarmState());
        database.update("ALARM",values,"sno=?",new String[]{String.valueOf(alarmModel.getSno())});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}