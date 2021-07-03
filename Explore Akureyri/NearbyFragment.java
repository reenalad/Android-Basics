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


public class NearbyFragment extends Fragment {


    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.item_list, container, false);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.lake_myvatn, getString(R.string.lakemyvatntitle), getString(R.string.lakemyvatn)));
        items.add(new Item(R.drawable.godafoss_image, getString(R.string.godafosstitle), getString(R.string.godafoss)));
        items.add(new Item(R.drawable.dimmuborgir_image, getString(R.string.dimmuborgirtitle), getString(R.string.dimmuborgir)));
        items.add(new Item(R.drawable.laufas_image, getString(R.string.laufastitle), getString(R.string.laufas)));
        items.add(new Item(R.drawable.hrisey_island, getString(R.string.hriseytitle), getString(R.string.hrisey)));
        items.add(new Item(R.drawable.krafla, getString(R.string.kraflatitle), getString(R.string.krafla)));

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
