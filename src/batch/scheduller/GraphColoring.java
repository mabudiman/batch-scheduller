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

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author M. Arief Budiman
 */
public class GraphColoring {
    private int total_slot;
    private LinkedList<Integer> events_itr[];
    private Event events[];
    private int time_domain;
    
    public GraphColoring(int total_slot,Event[] events)
    {   
        this.events = new Event[total_slot];
        this.events = events;
        this.total_slot = total_slot;
        events_itr = new LinkedList[total_slot];
        for (int i=0; i<total_slot; ++i)
            events_itr[i] = new LinkedList();
    }
    
    public void setTimeDomain(int td) {
        this.time_domain = td;
    }
    
    public void addEdge(int v,int w)
    {   
        if(!events_itr[v].contains(w)){
            events_itr[v].add(w);
            events_itr[w].add(v);
        }
    }
    
    public void greedyColoring()
    {
        int result[] = new int[total_slot];
 
        // Assign the first color to first vertex
        result[0]  = 0;
 
        // Initialize remaining V-1 vertices as unassigned
        for (int u = 1; u < total_slot; u++)
            result[u] = -1;  // no color is assigned to u
 
        // A temporary array to store the available colors. True
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean available[] = new boolean[total_slot];
        for (int cr = 0; cr < total_slot; cr++)
            available[cr] = false;
 
        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < total_slot; u++)
        {
            // Process all adjacent vertices and flag their colors
            // as unavailable
            Iterator<Integer> it = events_itr[u].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = true;
            }
 
            // Find the first available color
            int cr;
            for (cr = 0; cr < total_slot; cr++)
                if (available[cr] == false)
                    break;
 
            result[u] = cr; // Assign the found color
 
            // Reset the values back to false for the next iteration
            it = events_itr[u].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }
        }
 
        // print the result
        for (int u = 0; u < total_slot; u++)
            System.out.println("Vertex " + u + " --->  Color "
                                + result[u]);
    }
    
}
