package com.example.elkaiserblaze.minimal_todo;

import java.util.Date;

public class Task {
    private String title;
    private Date date;

    public Task(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date timeFinish) {
        this.date = timeFinish;
    }


}
