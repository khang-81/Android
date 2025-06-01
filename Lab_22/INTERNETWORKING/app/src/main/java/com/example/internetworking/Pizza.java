package com.example.internetworking;


public class Pizza {
    private String title;
    private String description;

    public Pizza(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title; // sẽ hiển thị trong ListView
    }
}
