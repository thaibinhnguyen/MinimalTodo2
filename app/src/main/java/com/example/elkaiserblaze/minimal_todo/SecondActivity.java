package com.example.elkaiserblaze.minimal_todo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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

        getView();
        // get bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle != null) {
            task = (Task) bundle.getSerializable("taskMod");
            action = 1;
            Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
            edtTask.setText(task.getTitle());
        }

        swtRemind = findViewById(R.id.switchButton);
        swtRemind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                final RemindFragment remindFragment = new RemindFragment();
                if (b) {
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
