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
