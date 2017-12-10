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
public class TimeTable {
    
    public int time_block;
    /*
        time_block determine how long every slot whould be.
        time_block is in minute. example: 60 ( 1 hour ).
    */
    public int total_slot;
    /*
        total_slot is total array time slot would created.
    */
    public Event[] time_slots;
    /*
        time_slots is array of Event, represent every time_slot available for an event
    */
}
