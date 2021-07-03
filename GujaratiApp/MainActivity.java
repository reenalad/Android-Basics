package com.example.reena.gujaratiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        //find the view that shows the family category
        TextView family = (TextView) findViewById(R.id.family);

        //set a click listener on the view
        family.setOnClickListener(new View.OnClickListener() {
            //when the family view is clicked
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        //find the view that shows the colours category
        TextView colours = (TextView) findViewById(R.id.colours);

        //set a click listener on the view
        colours.setOnClickListener(new View.OnClickListener() {
            //when the colours view is clicked
            @Override
            public void onClick(View view) {
                Intent coloursIntent = new Intent(MainActivity.this, ColoursActivity.class);
                startActivity(coloursIntent);
            }
        });

        //find the view that shows the phrases category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        //set a click listener on the view
        phrases.setOnClickListener(new View.OnClickListener() {
            //when the phrases view is clicked
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

    }
}
