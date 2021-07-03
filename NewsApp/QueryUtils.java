package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    //Constructor for QueryUtils
    private QueryUtils() {
    }

    static List<NewsItem> fetchNewsData(String requestURL) {
        // Create a URL object
        URL url = createUrl(requestURL);

        //Perform http request and extract relevant info
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        return extractNews(jsonResponse);
    }

    //Method to create a URL object
    private static URL createUrl(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    //Make a http request
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the url is null, return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the connection was successful, read the inputstream and parse response
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the book JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Convert the inputstream into a string
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    //Return a list of news stories the JSON response **/
    private static List<NewsItem> extractNews(String newsJSON) {

        List<NewsItem> newsItems = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject newsObject = baseJsonResponse.getJSONObject("response");
            JSONArray currentNewsItem = newsObject.getJSONArray("results");

            for (int i = 0; i < currentNewsItem.length(); i++) {
                JSONObject results = currentNewsItem.getJSONObject(i);
                String title = results.getString("webTitle");
                String date = results.getString("webPublicationDate");
                String section = results.getString("sectionName");
                String url = results.getString("webUrl");
                JSONObject fields = results.optJSONObject("fields");
                String image = null;
                if (fields != null) {
                    image = fields.optString("thumbnail");
                }
                date = date.replaceAll("T.*", " ");
                NewsItem newsItem = new NewsItem(title, date, section, url, image);
                newsItems.add(newsItem);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return newsItems;
    }
}

