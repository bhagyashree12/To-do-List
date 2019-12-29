package com.example.to_do_list_room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//class should be abstract
@Database(entities = Entity_node.class, version = 1)
public abstract class Database_node extends RoomDatabase {


    //class reference
    public static Database_node database_node;

    //abstract method
    public abstract Dao_node dao_node();


    //singleton function
    public static Database_node get_node(Context context) {
        if (database_node == null) {
            database_node = Room.databaseBuilder(context.getApplicationContext(), Database_node.class, "data")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }


        return database_node;
    }

    //create tables with some data
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private  static class DataValue extends AsyncTask<Void,Void,Void>{


        private Dao_node dao_node;
        public DataValue(Dao_node dao_node){
            this.dao_node=dao_node;

        }


        @Override
        protected Void doInBackground(Void... voids) {

            dao_node.insert(new Entity_node("elton","singer",1));
            dao_node.insert(new Entity_node("queen","singer",2));
            dao_node.insert(new Entity_node("billy","singer",3));
            return null;
        }
    }

}
