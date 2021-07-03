package com.example.reena.exploreakureyri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by reena on 04/05/2020.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    //constructor for the ItemAdapter
    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    //override the getView method
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get the item allocated at this position in the list
        Item currentItem = getItem(position);

        //find the ImageView for the image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        //display the image
        imageView.setImageResource(currentItem.getImageResourceId());

        //find the TextView for the title text
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);

        //set the text in the title TextView
        titleView.setText(currentItem.getTitleResourceId());


        //display the layout in the listItemView
        return listItemView;
    }
}
