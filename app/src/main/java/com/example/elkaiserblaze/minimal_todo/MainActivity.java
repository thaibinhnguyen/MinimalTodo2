package com.example.elkaiserblaze.minimal_todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        persistance.addTask(new Task("Di choi", "29/12/2018 06:03"));
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
                return super.onContextItemSelected(item);
        }

    }
}
