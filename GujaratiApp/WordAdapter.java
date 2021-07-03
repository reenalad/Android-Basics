package com.example.reena.gujaratiapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    //int to store background colour
    private int colourResourceID;

    //constructor for the word adapter
    public WordAdapter(Context context, ArrayList<Word> words, int colour){
        super(context, 0, words);
        colourResourceID = colour;
    }

    //override the getview method
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //get the object allocated at this position in the list
        Word word = getItem(position);

        //find the text view for the gujarati word
        TextView gujaratiTextView = (TextView) listItemView.findViewById(R.id.gujarati);

        //get the gujarati word and display it in the text view
        gujaratiTextView.setText(word.getGujaratiTranslation());

        //find the textview for the transliteration
        TextView transliterationTextView = (TextView) listItemView.findViewById(R.id.transliteration);

        //get the transliteration and display it in the text view
        transliterationTextView.setText(word.getTransliteration());

        ///find the text view for the english word
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english);

        //get the english word and display it in the text view
        englishTextView.setText(word.getEnglishTranslation());

        //find the image view
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);

        if (word.hasImage()) {
            //get the image and display it
            imageView.setImageResource(word.getImageResourceID());
            imageView.setVisibility(View.VISIBLE);
        } else {
            //hide the imageview if there is no image
            imageView.setVisibility(View.GONE);
        }

        //display the background colour
        View textContainer = listItemView.findViewById(R.id.text_container);
        int backgroundColour = ContextCompat.getColor(getContext(), colourResourceID);
        textContainer.setBackgroundColor(backgroundColour);

        //display the layout in the listitemview
        return listItemView;

    }
}
