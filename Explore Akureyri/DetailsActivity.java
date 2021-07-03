package com.example.reena.exploreakureyri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //find views
        ImageView image = (ImageView) findViewById(R.id.image_view);
        TextView title = (TextView) findViewById(R.id.title_view);
        TextView text = (TextView) findViewById(R.id.details_view);

        //get intent
        Intent intent = getIntent();

        //get the attached bundle
        Bundle extras = intent.getExtras();

        //extract the data
        Integer locationImage = extras.getInt("image");
        String locationTitle = extras.getString("title");
        String locationText = extras.getString("text");

        //set the data in the views
        image.setImageResource(locationImage);
        title.setText(locationTitle);
        text.setText(locationText);

    }

}
