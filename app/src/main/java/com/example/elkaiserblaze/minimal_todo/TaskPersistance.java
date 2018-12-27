package com.example.elkaiserblaze.minimal_todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskPersistance extends SQLiteOpenHelper implements PersistanceInterface {


    // CREATE, INSERT, UPDATE, DELETE
    //public void QueryData(String sql){
    //    SQLiteDatabase database = getWritableDatabase();
    //    database.execSQL(sql);
    //}
    //SELECT
/*    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }*/
/*    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }*/


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "modules.db";
    private static final String TABLE_TASKS = "tasks"; //name of the table
    private static final String ATTRIBUTE_TITLE="title"; //attribute title
    private static final String ATTRIBUTE_DATE="date"; //attribute date
    private static final String ATTRIBUTE_ID="id";

    public TaskPersistance(Context context) {
        super(context,TABLE_TASKS,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table_task_create =
                "CREATE TABLE "+TABLE_TASKS+ "(" +
                        ATTRIBUTE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ATTRIBUTE_TITLE+ " TEXT," +
                        ATTRIBUTE_DATE + " TEXT"+
                        ")"
                ;
        db.execSQL(table_task_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }


    @Override
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(ATTRIBUTE_ID,task.getId());
        contentValues.put(ATTRIBUTE_TITLE,task.getTitle());
        contentValues.put(ATTRIBUTE_DATE,task.getDate());
        long result = db.insert(TABLE_TASKS, null, contentValues);
        db.close();
    }

    @Override
    public void deleteTask(int index) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String delete_task_string = "DELETE FROM "+TABLE_TASKS+" WHERE "+ ATTRIBUTE_ID+" = "+index;
        db.execSQL(delete_task_string);
    }

    @Override
    public void getTask(Task task) {

    }

    @Override
    public void iniData() {

    }

    @Override
    public ArrayList<Task> getAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_TASKS;
        Cursor res = db.rawQuery(query,null);
        ArrayList<Task> listTasks= new ArrayList<Task>();
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String title=res.getString(res.getColumnIndex(ATTRIBUTE_TITLE));
            String date = res.getString(res.getColumnIndex(ATTRIBUTE_DATE));
            int id = res.getInt(res.getColumnIndex(ATTRIBUTE_ID));
            Task task= new Task(id,title,date);
            listTasks.add(task);
            res.moveToNext();
        }
        res.close();
        return listTasks;
    }
}
