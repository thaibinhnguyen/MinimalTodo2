package com.example.elkaiserblaze.minimal_todo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RemindFragment extends Fragment {
    EditText edtDay;
    EditText edtHour;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remind, container, false);
        edtDay = (EditText) view.findViewById(R.id.editTextDay);
        edtHour = (EditText) view.findViewById(R.id.editTextHour);
        edtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        edtHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
