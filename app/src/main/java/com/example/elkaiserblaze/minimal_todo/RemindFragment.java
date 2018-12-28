package com.example.elkaiserblaze.minimal_todo;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RemindFragment extends Fragment {
    EditText edtDay;
    EditText edtHour;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remind, container, false);
        edtDay = (EditText) view.findViewById(R.id.editTextDay);
        edtHour = (EditText) view.findViewById(R.id.editTextHour);
        //get data from second activity
        Bundle bundle = getArguments();
        if (bundle != null) {
            Task taskReceived = (Task) bundle.getSerializable("taskForFrag");
            String date_string = taskReceived.getDate();
            String[] parts = date_string.split(" ");
            edtDay.setText(parts[0]);
            edtHour.setText(parts[1]);
        }
        edtDay.setFocusable(false);
        edtHour.setFocusable(false);
        edtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate();
            }
        });
        edtHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseTime();
            }
        });
        return view;
    }

    public void ChooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = 0;
        int month = 0;
        int year = 0;
        if (edtDay.getText().toString() != "Today") {
            DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d = f.parse(edtDay.getText().toString());
                calendar.setTime(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void ChooseTime() {
        final Calendar calendar = Calendar.getInstance();
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute= calendar.get(Calendar.MINUTE);

        try {
            DateFormat f = new SimpleDateFormat("HH:mm");
            Date d = f.parse(edtHour.getText().toString());
            calendar.setTime(d);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    calendar.set(0, 0, 0, i, i1);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    edtHour.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }, hour, minute, true);
            timePickerDialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
