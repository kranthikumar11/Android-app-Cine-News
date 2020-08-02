package com.example.cinemanews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinemanews.adapter.NewsAdapter;
import com.example.cinemanews.news.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //https://gnews.io/api/v3/topics/entertainment?country=in&lang=te&token=0b3ce39cd8a9929a82ebe42fa320129d
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=c7b98f3e8bfe41c2bd4d13d4cf5b8a8f
    private final String URL = "https://gnews.io/api/v3/topics/entertainment?country=in&lang=te&token=0b3ce39cd8a9929a82ebe42fa320129d";
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    GsonBuilder gsonBuilder=new GsonBuilder();
                    Gson gson=gsonBuilder.create();
                    JSONArray jsonArray = response.getJSONArray("articles");
                    Article[] articles=gson.fromJson(String.valueOf(jsonArray), Article[].class);

                    newsAdapter = new NewsAdapter(MainActivity.this, articles);
                    recyclerView.setAdapter(newsAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Please turn on Mobile Data", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rq= Volley.newRequestQueue(this);
        rq.add(jsonObjectRequest);
    }
}