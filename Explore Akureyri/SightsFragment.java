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


public class SightsFragment extends Fragment {


    public SightsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_list, container, false);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.akureyrichurch, getString(R.string.akureyrarkirkjatitle), getString(R.string.akureyrakirkja)));
        items.add(new Item(R.drawable.christmas_cat, getString(R.string.christmascattitle), getString(R.string.christmascat)));
        items.add(new Item(R.drawable.old_town, getString(R.string.oldtowntitle), getString(R.string.oldtown)));
        items.add(new Item(R.drawable.botanical_garden, getString(R.string.botanicaltitle), getString(R.string.botanical)));
        items.add(new Item(R.drawable.northern_lights, getString(R.string.northernlightstitle), getString(R.string.northernlights)));
        items.add(new Item(R.drawable.hof_akureyri, getString(R.string.hoftitle), getString(R.string.hof)));

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
