package com.example.elkaiserblaze.minimal_todo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Switch swtRemind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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
                    if (remindFrag != null) {
                        fragmentTransaction.remove(remindFrag);

                    } else {
                        Toast.makeText(SecondActivity.this, "Fragment doesn't exist!", Toast.LENGTH_SHORT).show();
                    }
                }
                fragmentTransaction.commit();
            }
        });

    }
}
