package com.example.newsapp;

class NewsItem {

    //News story title
    private String mTitle;

    //Date
    private String mDate;

    //Section/topic
    private String mSection;

    //URL for the news story
    private String mUrl;

    //URL of news story image
    private String mImage;

    //Constructor
    NewsItem(String title, String date, String section, String url, String image) {
        mTitle = title;
        mDate = date;
        mSection = section;
        mUrl = url;
        mImage = image;
    }

    //Returns the news item title
    String getTitle() {
        return mTitle;
    }

    //Returns the news item date
    String getDate() {
        return mDate;
    }

    //Returns the section of the article
    String getSection() {
        return mSection;
    }

    //Returns the article URL
    String getUrl() {
        return mUrl;
    }

    //Returns the article image URL
    String getImage() {
        return mImage;
    }
}




