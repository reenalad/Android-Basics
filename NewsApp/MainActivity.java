package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    //Adapter for the news items
    private NewsAdapter adapter;

    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the views in the layout
        ListView newsListView = findViewById(R.id.list);

        TextView mEmptyStateTextView = findViewById(R.id.empty);
        newsListView.setEmptyView(mEmptyStateTextView);

        loading = findViewById(R.id.loading_indicator);

        //Create a new adapter that takes the list of news items as input
        adapter = new NewsAdapter(this, new ArrayList<NewsItem>());

        //Set the adapter on the listview so it can be populated
        newsListView.setAdapter(adapter);

        //Make a network request
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            NewsAsyncTask newsAsyncTask = new NewsAsyncTask();
            //URL for the news data
            String newsItem_request_url = "https://content.guardianapis.com/search?q=news%20AND%20NOT%20sport&api-key="key"&show-fields=thumbnail";
            newsAsyncTask.execute(newsItem_request_url);
        } else {
            mEmptyStateTextView.setText(R.string.no_internet);
        }

        //Set onclicklistener to open webpage with the full news item
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem currentNewsItem = adapter.getItem(position);
                Uri bookUri = Uri.parse(currentNewsItem.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);
                startActivity(websiteIntent);
            }
        });

    }

    //Make a network request on another thread
    private class NewsAsyncTask extends AsyncTask<String, Void, List<NewsItem>> {
        @Override
        protected List<NewsItem> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            return QueryUtils.fetchNewsData(urls[0]);
        }

        protected void onPostExecute(List<NewsItem> data) {
            adapter.clear();
            if (data != null && !data.isEmpty()) {
                loading.setVisibility(View.GONE);
                adapter.addAll(data);
            }
        }
    }

}

