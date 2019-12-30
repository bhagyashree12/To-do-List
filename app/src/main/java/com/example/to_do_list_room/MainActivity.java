package com.example.to_do_list_room;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View_model view_model;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycler view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adaper
        final Adapter adapter=new Adapter();
        recyclerView.setAdapter(adapter);

        //viewmodel
        view_model = ViewModelProviders.of(this).get(View_model.class);
        view_model.getAllNotes().observe(this, new Observer<List<Entity_node>>() {
            @Override
            public void onChanged(@Nullable List<Entity_node> notes) {
                //update RecyclerView
                adapter.setNodes(notes);
                Toast.makeText(MainActivity.this, "hello world", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
