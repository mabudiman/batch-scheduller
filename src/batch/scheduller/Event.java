/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.scheduller;

/**
 *
 * @author M. Arief Budiman
 */
public class Event {
    
    public String title;
    /*
        the event name, shows on TimeTable or detail
    */
    public String description;
    /*
        description can be show manually for detail
    */
    public int time_length;
    /*
        time_length variable used for determine where to put the event on the time slot
        if the time_length 60 (1 hour) and the time_block on TimeTable on 30 (half hour)
        then it will use 2 time_slot
        time_length and time_slot both in minute
    */
    public Personil[] personils;
    /*
        bunch of personil that invited to the event
    */

    public Event(String title, String description, int time_length, Personil[] personils) {
        this.title = title;
        this.description = description;
        this.time_length = time_length;
        this.personils = personils;
    }

    public Event(String title, int time_length) {
        this.title = title;
        this.time_length = time_length;
    }
    
    
}
