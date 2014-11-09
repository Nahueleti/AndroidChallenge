package com.nmiranda.nmiranda.androidchallenge;

/**
 * Created by Nahuel on 08/11/2014.
 */
public class Task {
    private long id;
    private String task;
    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return task;
    }
}
