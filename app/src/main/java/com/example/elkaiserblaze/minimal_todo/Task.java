package com.example.elkaiserblaze.minimal_todo;

import java.util.Date;

public class Task {
    private String title;
    private Date timeFinish;

    public Task(String title, Date timeFinish) {
        this.title = title;
        this.timeFinish = timeFinish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }
    

}
