package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    //Constructor for NewsItemAdapter
    NewsAdapter(Context context, List<NewsItem> newsItems) {
        super(context, 0, newsItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Check if there is an existing listitemview to reuse or inflate a new layout
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        //Find the news story at a given position
        NewsItem newsItem = getItem(position);

        //Find the title textview and display
        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(newsItem.getTitle());

        //Find the date of the article
        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(newsItem.getDate());

        //Find the news topic
        TextView sectionView = listItemView.findViewById(R.id.topic);
        sectionView.setText(newsItem.getSection());

        //Find the news story image
        ImageView imageView = listItemView.findViewById(R.id.image);
        String imageUrl = newsItem.getImage();
        //Set the image or use placeholder
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(imageView);
        } else {
            Picasso.get().load(R.drawable.placeholder).into(imageView);
        }

        return listItemView;

    }


}
