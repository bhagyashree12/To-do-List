package com.example.to_do_list_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.to_do_list_room.Detailed_class.EXTRA_DESCRIPTION;
import static com.example.to_do_list_room.Detailed_class.EXTRA_PRIORITY;
import static com.example.to_do_list_room.Detailed_class.EXTRA_TITLE;

public class MainActivity extends AppCompatActivity {

    private View_model view_model;
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;

    public static final int ADD_NOTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycler view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adaper
        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);


        //flaoting_action_bar
        actionButton=findViewById(R.id.floating_actionbar);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Detailed_class.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }
        });

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


        //swipe to delete item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                view_model.delete(adapter.getNodeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "deledted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

    }

    //to confirm and get the data from the save option
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_NOTE_REQUEST&&resultCode==RESULT_OK){

            String title=data.getStringExtra(EXTRA_TITLE);
            String description=data.getStringExtra(EXTRA_DESCRIPTION);
            int priority=data.getIntExtra(EXTRA_PRIORITY,1);

            Entity_node node=new Entity_node(title,description,priority);
            view_model.insert(node);


            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }

    }
}
