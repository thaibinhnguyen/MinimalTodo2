package com.example.elkaiserblaze.minimal_todo;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private int id;
    private String title;
    private String date;

    public Task(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public Task(int id,String title,String date){
        this.id=id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
