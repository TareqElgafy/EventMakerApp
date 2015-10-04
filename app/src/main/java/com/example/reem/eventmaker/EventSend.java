package com.example.reem.eventmaker;

import org.w3c.dom.Text;

/**
 * Created by Reem on 8/26/2015.
 */
public class EventSend {


    private int user_id;
    private String name;
    private String description;
    private String location;
    private long date;




    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




}
