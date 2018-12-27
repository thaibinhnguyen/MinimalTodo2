package com.example.elkaiserblaze.minimal_todo;

import java.util.ArrayList;

public interface PersistanceInterface {

    public void addTask(Task task);

    public void deleteTask(int index);

    public void getTask(Task task);

    public void iniData();

    public ArrayList<Task> getAllTasks();
}
