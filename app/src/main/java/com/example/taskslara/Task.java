package com.example.taskslara;

public class Task {
    private int id,state;
    private String name;
    private boolean completed;
    public Task(String name,int id) {
        this.id = id;
        this.state = state;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setName(String name) {
        this.name = name;
    }
}
