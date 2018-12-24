package com.example.elkaiserblaze.minimal_todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TaskPersistance persistance=new TaskPersistance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = dateFormatter.parse("15/02/2018 18:20",new ParsePosition(0));
        Task task1 = new Task("huyen thoai",date1);
        persistance.addTask(task1);
        ArrayList<Task> list_tasks = persistance.getAllTasks();
        Log.e("Phuc",String.valueOf(list_tasks.get(list_tasks.size()-1).getTitle()));
    }



}
