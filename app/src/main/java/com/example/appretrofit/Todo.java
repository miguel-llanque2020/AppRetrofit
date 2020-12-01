package com.example.appretrofit;

public class Todo {

    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    public Todo(){

    }
    public Todo(int userId, int id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

}
