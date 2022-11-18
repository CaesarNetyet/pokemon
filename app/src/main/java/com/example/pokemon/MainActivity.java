package com.example.pokemon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PokemonAdapter adapter;
    RecyclerView recyclerView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue=SingletonRequest.getInstance(this).getRequestQueue();
        requireMethod();
    }


    private void requireMethod() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=100&offset=200";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Gson gson = new Gson();
                    ListaPokemon listaPokemon = gson.fromJson(response.toString(), ListaPokemon.class);
                    List<Pokemon> pokemonList = listaPokemon.getResults();
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new PokemonAdapter(pokemonList);
                    recyclerView.setAdapter(adapter);
                },
                error -> error.printStackTrace()
        );
        queue.add(request);
    }
}