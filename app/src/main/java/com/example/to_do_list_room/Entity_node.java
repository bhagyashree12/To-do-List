package com.example.to_do_list_room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data")
public class Entity_node {


    //primary key
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String title;
    private String detail;
    private Integer priority;

    //constructor
    public Entity_node(String title, String detail, Integer priority) {
        this.title = title;
        this.detail = detail;
        this.priority = priority;
    }

    //set the id
    public void setId(int id) {
        this.id = id;
    }

    //getters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Integer getPriority() {
        return priority;
    }
}
