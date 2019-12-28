package com.example.to_do_list_room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//class should be abstract
@Database(entities = Entity_node.class, version = 1)
public abstract class Database_node extends RoomDatabase {

    Dao_node dao;



}
