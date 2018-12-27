package com.example.elkaiserblaze.minimal_todo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Task> taskList;

    public TaskAdapter(Context context, int layout, List<Task> taskList) {
        this.context = context;
        this.layout = layout;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTitle, txtDate, txtLogo;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) view.findViewById(R.id.titleTask);
            holder.txtLogo = (TextView) view.findViewById(R.id.logoTask);
            holder.txtDate = (TextView) view.findViewById(R.id.dateTask);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Task task = taskList.get(i);
        holder.txtTitle.setText(task.getId()+". "+task.getTitle());
        String date = task.getDate();
        holder.txtDate.setText(date);
        holder.txtLogo.setText(String.valueOf(task.getTitle().charAt(0)).toUpperCase());
        GradientDrawable gradientDrawable = (GradientDrawable) holder.txtLogo.getBackground().mutate();
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        gradientDrawable.setColor(color);
        return view;
    }


}
