/*
 * Copyright (C) 2017 Arief
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
        if(schedule != null){
            for (int i = 0; i < total_slot; i++) {
                if( '1' == schedule.charAt(i)) {
                    time_slots[i] = new Event("private", 60);
                }
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
