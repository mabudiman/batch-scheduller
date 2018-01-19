/*
 * Copyright (C) 2017 M. Arief Budiman
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

import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author M. Arief Budiman
 */
public class GraphMapping { 
    
    public Event events[];
    public GraphColoring gc;
    public int total_time_slot;
    
    public GraphMapping(Event[] events) {
        this.events = events;
        this.gc = new GraphColoring(events.length,events);
        int event_itr = 0;
        for (Event event : this.events) {
            for (Personil personil : event.personils) {
                int other_itr = 0;
                for (Event other_event : this.events) {
                    if(!event.equals(other_event)) {
                        for (Personil other_personil : other_event.personils) {
                            if(personil.equals(other_personil)) {
                                gc.addEdge(event_itr, other_itr);
                            }
                        }
                    }
                    other_itr++;
                }
            }
            event_itr++; 
        }
    }
    
    public void setTotalTimeSlot(int tts) {
        this.total_time_slot = tts;
        gc.setTimeDomain(tts);
    }
    
    public void executeGraphColoring() {
        System.out.println("Executing graph");
        gc.greedyColoring();
    }
    
    public void welshPowellColoring() {
        gc.welshPowellColoring();
    }
    
}
