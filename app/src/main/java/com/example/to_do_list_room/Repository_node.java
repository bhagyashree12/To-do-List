package com.example.to_do_list_room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository_node {

    private Dao_node dao_node;
    private LiveData<List<Entity_node>> livedata;


    public Repository_node(Application application) {
        Database_node database_node = Database_node.get_node(application);
        //abstract method of database_node  class
        dao_node = database_node.dao_node();
        livedata = dao_node.getallnodes();

    }

    //functions repo performs through dao

    public void inserNode(Entity_node node) {

        new InsertNode(dao_node).execute(node);


    }

    public void update(Entity_node node) {

        new UpdateNode(dao_node).execute(node);

    }

    public void delete(Entity_node node) {
        new DeleteNode(dao_node).execute(node);
    }

    public void deleteAll() {
        new DeleteAllNode(dao_node).execute();

    }

    public LiveData<List<Entity_node>> getAllNode() {
        return livedata;
    }


    //create asyntask for each function

    public static class InsertNode extends AsyncTask<Entity_node, Void, Void> {

        private Dao_node dao_node;

        public InsertNode(Dao_node dao_node) {
            this.dao_node = dao_node;
        }


        @Override
        protected Void doInBackground(Entity_node... entity_nodes) {
            dao_node.insert(entity_nodes[0]);
            return null;
        }
    }

    public static class UpdateNode extends AsyncTask<Entity_node, Void, Void> {

        private Dao_node dao_node;

        public UpdateNode(Dao_node dao_node) {
            this.dao_node = dao_node;
        }


        @Override
        protected Void doInBackground(Entity_node... entity_nodes) {
            dao_node.update(entity_nodes[0]);
            return null;
        }
    }

    public static class DeleteNode extends AsyncTask<Entity_node, Void, Void> {

        private Dao_node dao_node;

        public DeleteNode(Dao_node dao_node) {
            this.dao_node = dao_node;
        }


        @Override
        protected Void doInBackground(Entity_node... entity_nodes) {
            dao_node.delete(entity_nodes[0]);
            return null;
        }
    }

    public static class DeleteAllNode extends AsyncTask<Void, Void, Void> {

        private Dao_node dao_node;

        public DeleteAllNode(Dao_node dao_node) {
            this.dao_node = dao_node;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            dao_node.deleteAll();
            return null;
        }
    }


}
