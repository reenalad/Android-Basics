package com.example.reena.exploreakureyri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class FoodFragment extends Fragment {

    public FoodFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_list, container, false);

        //create an arraylist of item objects
        final ArrayList<Item> items = new ArrayList<>();

        //add the image and text to the item object
        items.add(new Item(R.drawable.rub_23, getString(R.string.rub23title), getString(R.string.rub23)));
        items.add(new Item(R.drawable.aurora_restaurant, getString(R.string.auroratitle), getString(R.string.aurora)));
        items.add(new Item(R.drawable.bautinn_restaurant, getString(R.string.bautinntitle), getString(R.string.bautinn)));
        items.add(new Item(R.drawable.akureyri_fish, getString(R.string.akureyrifishtitle), getString(R.string.akureyrifish)));
        items.add(new Item(R.drawable.mulaberg_restaurant, getString(R.string.mulabergtitle), getString(R.string.mulaberg)));
        items.add(new Item(R.drawable.strikid_restaurant, getString(R.string.strikidtitle), getString(R.string.strikid)));


        ItemAdapter adapter = new ItemAdapter(getActivity(), items);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        //set click listener so the user can view more details if they click on an item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the position of the current list item
                Item item = items.get(position);

                //create a bundle object and add data to present in the details activity
                Bundle extras = new Bundle();
                extras.putInt("image", item.getImageResourceId());
                extras.putString("title", item.getTitleResourceId());
                extras.putString("text", item.getTextResourceId());

                //create and initialise the intent
                Intent detailsIntent = new Intent(getActivity(), DetailsActivity.class);

                //attach the bundle to the intent object
                detailsIntent.putExtras(extras);

                //start the activity
                startActivity(detailsIntent);

            }
        });

        return rootView;
    }
}
