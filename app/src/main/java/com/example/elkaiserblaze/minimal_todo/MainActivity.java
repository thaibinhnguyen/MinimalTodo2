package com.example.elkaiserblaze.minimal_todo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    private TaskPersistance persistance= new TaskPersistance(this);
    ListView lvTask;
    ArrayList<Task> arrayTask=new ArrayList<>();
    TaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTask = (ListView) findViewById(R.id.listViewTask);
        //create database
        //persistance = new TaskPersistance(this,"taskNote.sqlite",null,1);
        //create table
        //persistance.QueryData("CREATE TABLE IF NOT EXISTS Tasks(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR(200), Date VARCHAR(200))");
        //insert value
        //Date dateTest = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //String dateString = sdf.format(dateTest);
        //persistance.QueryData("INSERT INTO Tasks VALUES(null, 'Quet nha', '"+ (String) dateString+"')");
        //select Data
/*        Cursor dataTask = persistance.GetData("SELECT * FROM Tasks");
        while (dataTask.moveToNext()){
            String title = dataTask.getString(1);
            String date_task = dataTask.getString(2);
            Toast.makeText(this, date_task, Toast.LENGTH_SHORT).show();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            int id = dataTask.getInt(0);
            arrayTask.add(new Task(id, title, date_task));
        }*/
//        persistance.addTask(new Task("Di quay", "28/12/2018 16:53"));
        arrayTask.addAll(persistance.getAllTasks());
        adapter = new TaskAdapter(this, R.layout.layout_line, arrayTask);
        lvTask.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        registerForContextMenu(lvTask);

        //floating button action
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
//        sendNotificationAlarm(this);
//        setAlarm(new Task("Di quay", ""));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_lv, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.supprimer_item:
                persistance.deleteTask(arrayTask.get(info.position).getId());
                arrayTask.remove(info.position);
                adapter.notifyDataSetChanged();
                return true;
            default:
                Task task = arrayTask.get(info.position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("taskMod", task);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
                return true;
        }
    }


    public void setAlarm(Task task) {
        if(!task.getDate().equals("")){
            Calendar now=Calendar.getInstance();
            AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent= new Intent(this,AlertReceiver.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("task",task);
            intent.putExtra("dataTask",bundle);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Calendar timeFinished=Calendar.getInstance();
            try {
                timeFinished.setTime(sdf.parse(task.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,timeFinished.getTimeInMillis(),pendingIntent);
        }else {
            Log.i("From setAlarm","no date");
        }

    }
}
