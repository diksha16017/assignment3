package com.example.ajay.datahandle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Diksha on 01-Oct-16.
 */
public class DBclass extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;
   //database name
    private static final String DATABASE_NAME = "SpecialEventManager";
    //table name
    private static final String TABLE_SPECIAL = "sspecialEvents";
    //feild 1 -- event type
    public static final String KEY_EVENT = "eventtype";
    // feild  -- auto increament id
    public static final String KEY_ROWID = "_id";
    // feild 2 -- event of
    public static final String KEY_EVENTOF = "eventof";
    // feild 2 -- event on
    public static final String KEY_EVENTON = "eventon";
   //string for creation of table
    private static final String SPECIAL_EVENT_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SPECIAL + "("
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_EVENT + " VARCHAR(30)," + KEY_EVENTOF + " VARCHAR(50) UNIQUE, " + KEY_EVENTON + " VARCHAR(15) )";

    //constructor getting context
    public DBclass(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SPECIAL_EVENT_CREATE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIAL);

        // Create tables again
        onCreate(db);
    }

    //for opening of database
    public SQLiteDatabase open()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }

    /*
    * function for adding to database
    * */
    public long addEvent(SpecialEvent se) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        System.out.println("Special inside database :" + se.getEventOf());
        values.put(KEY_EVENT, se.getEventType());
        values.put(KEY_EVENTOF, se.getEventOf());
        values.put(KEY_EVENTON, se.getEventOn());
        // Inserting Row
        return(db.insert(TABLE_SPECIAL, null, values));
        //db.close(); // Closing database connection

    }

    /*
    * function for updating the special event in database
    * */
    public int updateEventOn(SpecialEvent se) {

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT, se.getEventType());
        values.put(KEY_EVENTOF, se.getEventOf());
        values.put(KEY_EVENTON, se.getEventOn());
        values.put(KEY_EVENTON, se.getEventOn());
        String[] args = new String[]{se.getEventOf()};
        SQLiteDatabase db = this.getWritableDatabase();
        try {
               // int num = db.update(TABLE_SPECIAL, values, KEY_EVENT + "='" + se.getEventType() + "'" + "and" + KEY_EVENTOF + "='" + se.getEventOf() + "'", null);
                //return num;
                int num = db.update(TABLE_SPECIAL,values,"eventof=?",args);
                return num;
        } catch (Exception e) {
        }

        return 0;
    }

    /*
    * function for deleting a particular row in database
    * */
    public int deleteRow(SpecialEvent se)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{se.getEventOf()};
        int num = db.delete(TABLE_SPECIAL,"eventof=?",args);
        return num;
    }

    //fuction for getting raw query for displaying all events present in database
    public Cursor getAllTitles() {
        // using simple SQL query
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_SPECIAL, null);
    }
    }