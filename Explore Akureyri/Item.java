package com.example.reena.exploreakureyri;

/**
 * Created by reena on 04/05/2020.
 */

public class Item {
    //int to store the image
    private int imageResourceId;

    //string to store the title
    private String titleResourceId;

    //string to store the details
    private String textResourceId;

    //constructor
    public Item(int image, String title, String text) {
        imageResourceId = image;
        titleResourceId = title;
        textResourceId = text;
    }

    //method to get the image resource
    public int getImageResourceId() {
        return imageResourceId;
    }

    //method to get the title
    public String getTitleResourceId() {
        return titleResourceId;
    }

    //method to get the details
    public String getTextResourceId() {
        return textResourceId;
    }
}

