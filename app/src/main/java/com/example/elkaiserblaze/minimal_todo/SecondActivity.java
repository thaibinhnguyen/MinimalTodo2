package com.example.elkaiserblaze.minimal_todo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    Switch swtRemind;
    EditText edtTask;
    FloatingActionButton floatSend;
    int action; //action = 0 if add, action = 1 if modify
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        swtRemind = findViewById(R.id.switchButton);
        getView();
        // get bundle
        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("data");
        if (bundle != null) {
            task = (Task) bundle.getSerializable("taskMod");
            action = 1;
//            Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
            edtTask.setText(task.getTitle());
            swtRemind.setChecked(true);
            // initiate fragment
            FragmentManager fragmentManager1 = getFragmentManager();
            FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
            RemindFragment remindFragment1 = new RemindFragment();
            Bundle bundleSecond = new Bundle();
            bundleSecond.putSerializable("taskForFrag", task);
            remindFragment1.setArguments(bundleSecond);
            fragmentTransaction1.add(R.id.frameLayoutDate, remindFragment1, "remindFragment");
            fragmentTransaction1.commit();
        } else {
            action = 0;
            swtRemind.setChecked(false);
        }


        swtRemind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final RemindFragment remindFragment = new RemindFragment();
                if (b) {
                    // just for modifying function
                    if (action == 1) {
                        Bundle bundleSecond = new Bundle();
                        bundleSecond.putSerializable("taskForFrag", task);
                        remindFragment.setArguments(bundleSecond);
                    }

                    fragmentTransaction.add(R.id.frameLayoutDate, remindFragment, "remindFragment");
                } else {
                    RemindFragment remindFrag = (RemindFragment) getFragmentManager().findFragmentByTag("remindFragment");
                    fragmentTransaction.remove(remindFrag);
                }
                fragmentTransaction.commit();
//                setDateTime();
            }
        });

        //save data
        floatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemindFragment remind = (RemindFragment) getFragmentManager().findFragmentByTag("remindFragment");
                String datetime ="";
                // in case no alarm
                if (remind != null){
                    if(!remind.edtDay.getText().toString().equals("Today")){
                        datetime = remind.edtDay.getText().toString() + " " + remind.edtHour.getText().toString();
                        Log.d("remindEdtDay", remind.edtDay.getText().toString());
                    }else{
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        datetime = simpleDateFormat.format(calendar.getTime())+ " "+ remind.edtHour.getText().toString();
                    }

                }
                String title = edtTask.getText().toString();
                if (action == 1) {
                    Task taskUpdated = new Task(task.getId(), title, datetime);
                    Bundle bundleUpdate = new Bundle();
                    bundleUpdate.putSerializable("taskUpdated", taskUpdated);
                    Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                    intent1.putExtra("bundleUpdate", bundleUpdate);
                    startActivity(intent1);
                } else {
                    Task taskAdd = new Task(title, datetime);
                    Bundle bundleAdd = new Bundle();
                    bundleAdd.putSerializable("taskAdd", taskAdd);
                    Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
                    intent2.putExtra("bundleAdd", bundleAdd);
                    startActivity(intent2);
                }
            }
        });
    }

    // get view
    public void getView() {
        edtTask = findViewById(R.id.editTextTask);
        floatSend = findViewById(R.id.floatButtonSend);
    }

    // set day, hour
//    public void setDateTime() {
//        RemindFragment remindFrag = (RemindFragment) getFragmentManager().findFragmentByTag("remindFragment");
//        if (remindFrag != null && task != null) {
//            String date_string = task.getDate();
//            String[] parts = date_string.split(" ");
//            remindFrag.edtDay.setText(parts[0]);
//            remindFrag.edtHour.setText(parts[1]);
//            Toast.makeText(SecondActivity.this, date_string, Toast.LENGTH_SHORT).show();
//        }
//    }
}
