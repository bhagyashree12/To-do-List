package com.example.to_do_list_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Dao_node {

    @Insert
    void insert(Entity_node node);

    @Update
    void update(Entity_node node);

    @Delete
    void delete(Entity_node node);

    //custom query can be made by using query
    @Query("DELETE FROM data")
    void deleteAll();

    //show all the nodes
    @Query("SELECT * FROM data ORDER BY priority DESC")
    LiveData<List<Entity_node>> getallnoted();


}
