package com.example.to_do_list_room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class View_model extends AndroidViewModel {
    private Repository_node repository;
    private LiveData<List<Entity_node>> liveData;

    public View_model(@NonNull Application application) {
        super(application);
        repository = new Repository_node(application);
        //getting access to livedata through repo
        liveData = repository.getAllNode();

    }


    //creating function to access the repo

    public void insert(Entity_node node) {
        repository.inserNode(node);
    }

    public void update(Entity_node node) {
        repository.update(node);
    }

    public void delete(Entity_node node) {
        repository.delete(node);
    }

    public void deleteAllNotes() {
        repository.deleteAll();
    }

    public LiveData<List<Entity_node>> getAllNotes() {
        return liveData;
    }
}