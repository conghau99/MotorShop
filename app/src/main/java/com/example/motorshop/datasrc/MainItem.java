package com.example.motorshop.datasrc;

public class MainItem {
    private int imageMain;
    private String name;

    public MainItem() { }

    public MainItem(int imageMain, String name) {
        this.imageMain = imageMain;
        this.name = name;
    }

    public int getImageMain() {
        return imageMain;
    }

    public void setImageMain(int imageMain) {
        this.imageMain = imageMain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}