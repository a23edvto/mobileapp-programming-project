package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JsonURL = "https://mobprog.webug.se/json-api?login=a23edvto";

    private Gson myGson = new Gson();

    private RecyclerView myRecyclerView;

    private RecyclerViewAdapter recViewAdapter;

    private ArrayList<Landskap> Landskap;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JsonURL);

        Landskap = new ArrayList<>();

        recViewAdapter = new RecyclerViewAdapter(this, Landskap);

        myRecyclerView = findViewById(R.id.recycler_view);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(recViewAdapter);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPostExecute(String json){
        Type type = new TypeToken<ArrayList<Landskap>>() {}.getType();
        ArrayList<Landskap> listOfLandskap = myGson.fromJson(json, type);

        recViewAdapter.updateAdapter(listOfLandskap);

        recViewAdapter.notifyDataSetChanged();
    }
}
