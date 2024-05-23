package com.example.test_1.model;

public class Task {
    private String name;
    private String since;
    private String toDate;
    private boolean isDone;

    public Task(String name, String since, String toDate, boolean isDone) {
        this.name = name;
        this.since = since;
        this.toDate = toDate;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public String getSince() {
        return since;
    }

    public String getToDate() {
        return toDate;
    }

    public boolean isDone() {
        return isDone;
    }
}
