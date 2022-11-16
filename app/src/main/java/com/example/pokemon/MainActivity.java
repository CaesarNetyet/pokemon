package com.example.pokemon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);

        requireMethod();
    }

    private void requireMethod() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=100&offset=200";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Gson gson = new Gson();
                    ListaPokemon listaPokemon = gson.fromJson(response.toString(), ListaPokemon.class);
                    List<Pokemon> pokemonList = listaPokemon.getResults();
                },
                error -> error.printStackTrace()
        );
        mQueue.add(request);
    }
}