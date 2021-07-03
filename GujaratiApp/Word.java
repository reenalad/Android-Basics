package com.example.reena.gujaratiapp;

/**
 * Created by reena on 16/04/2020.
 */

public class Word {

    //the string for the english word
    private String englishTranslation;
    //the string for the gujarati word
    private String gujaratiTranslation;
    //the string for the transliteration
    private String transliteration;
    //int for the image resource
    private int imageResourceID = NO_IMAGE_PROVIDED;
    //int for the audio file
    private int audioResourceID;

    private static final int NO_IMAGE_PROVIDED = -1;

    //constructor
    public Word(String englishWord, String gujaratiWord, String transliterationWord, int audio) {
        englishTranslation = englishWord;
        gujaratiTranslation = gujaratiWord;
        transliteration = transliterationWord;
        audioResourceID = audio;

    }

    //constructor to include image
    public Word(String englishWord, String gujaratiWord, String transliterationWord, int image, int audio) {
        englishTranslation = englishWord;
        gujaratiTranslation = gujaratiWord;
        transliteration = transliterationWord;
        imageResourceID = image;
        audioResourceID = audio;
    }

    //method to get the english word
    public String getEnglishTranslation(){
        return englishTranslation;
    }

    //method to get the gujarati word
    public String getGujaratiTranslation() {
        return gujaratiTranslation;
    }

    //method to get the transliteration
    public String getTransliteration() {
        return transliteration;
    }

    //method to get the image
    public int getImageResourceID() {
        return imageResourceID;
    }

    //method to state whether the word has an image
    public boolean hasImage(){
        return imageResourceID != NO_IMAGE_PROVIDED;
    }

    //method to get the audio resource
    public int getAudioResourceID() {
        return audioResourceID;
    }
}
