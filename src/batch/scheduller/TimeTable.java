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
    public Event[] time_slots = new Event[]{};
    /*
        time_slots is array of Event, represent every time_slot available for an event
    */

    public TimeTable(int time_block, int total_slot, Event[] time_slots) {
        this.time_block = time_block;
        this.total_slot = total_slot;
        this.time_slots = time_slots;
    }

    public TimeTable(int time_block, int total_slot, String schedule) {
        this.time_block = time_block;
        this.total_slot = total_slot;
        this.time_slots = new Event[total_slot];
        for (int i = 0; i < total_slot; i++) {
            if( '1' == schedule.charAt(i)) {
                time_slots[i] = new Event("dummy", 60);
            }
        }
    }

    public int getTime_block() {
        return time_block;
    }

    public void setTime_block(int time_block) {
        this.time_block = time_block;
    }

    public Event[] getTime_slots() {
        return time_slots;
    }

    public void setTime_slots(Event[] time_slots) {
        this.time_slots = time_slots;
    }
    
}
