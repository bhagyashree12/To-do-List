package com.example.to_do_list_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class Detailed_class extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private NumberPicker priority;

    public static final String EXTRA_TITLE = "com.example.to_do_list_room.TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.to_do_list_room.DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.to_do_list_room.PRIORITY";
    public static final String EXTRA_ID = "com.example.to_do_list_room.ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);


        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        priority = findViewById(R.id.priority);
        priority.setMaxValue(10);
        priority.setMinValue(0);


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }


    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:
                saveNode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    //save the node
    private void saveNode() {

        String title_node = title.getText().toString();
        String detail_node = description.getText().toString();
        int priority_node = priority.getValue();


        //if no information is provided
        if (title_node.trim().isEmpty() || detail_node.trim().isEmpty()) {
            Toast.makeText(this, "PLease enter some data", Toast.LENGTH_SHORT).show();
            return;
        }

        //passing the data
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title_node);
        intent.putExtra(EXTRA_DESCRIPTION, detail_node);
        intent.putExtra(EXTRA_PRIORITY, priority_node);

        //for update
        int id =getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){

            intent.putExtra(EXTRA_ID, id);

        }


        //if intent not mentioned it will give nullpointerexception
        setResult(RESULT_OK, intent);
        finish();

        //override onactivity result in mainactivity to get this data


    }
}
