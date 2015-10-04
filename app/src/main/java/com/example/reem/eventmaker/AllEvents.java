package com.example.reem.eventmaker;

import java.util.ArrayList;

/**
 * Created by Reem on 9/16/2015.
 */
public class AllEvents {

    private ArrayList<EventName> events_array= new ArrayList<EventName>();

    public ArrayList<EventName> getEvents() {
        return events_array;
    }

    public void setEvents(ArrayList<EventName> events) {
        this.events_array= events;
    }


}
 class EventName
 {
     private int id;
     private String name;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     @Override
     public String toString(){
         return name;
     }


 }