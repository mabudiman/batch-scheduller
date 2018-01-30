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

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author M. Arief Budiman
 */
public class GraphColoring {
    private LinkedList<Integer> events_itr[];
    private Event events[];
    private int result[];
    private int time_domain;
    private int total_slot;
    
    public GraphColoring(int total_slot,Event[] events)
    {   
        this.events = new Event[total_slot];
        this.events = events;
        this.total_slot = total_slot;
        this.events_itr = new LinkedList[total_slot];
        for (int i=0; i<total_slot; ++i)
            this.events_itr[i] = new LinkedList();
    }
    
    public void setTimeDomain(int td) {
        this.time_domain = td;
    }

    public LinkedList<Integer>[] getEvents_itr() {
        return events_itr;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public int[] getResult() {
        return result;
    }
    
    public int[] fillPossibleTime(Event e) {
        int time[] = new int[time_domain];
        for (int i = 0; i < time_domain; i++) {
            time[i] = 1; // make all time slot available first
        }
        
        for(Personil p : e.personils) {
            for (int i = 0; i < time_domain; i++) {
                if(p.schedule.time_slots[i] != null) {
                    time[i] = 0;
                }
            }
        }
        
        return time;
    }
    
    public void addEdge(int v,int w)
    {   
        if(!events_itr[v].contains(w)){
            events_itr[v].add(w);
            events_itr[w].add(v);
        }
    }
    
    public void swapElementList(int a, int b)
    {
        for (LinkedList<Integer> events_itr1 : events_itr) {
            for (int j = 0; j < events_itr1.size(); j++) {
                if (events_itr1.get(j) == a) {
                    events_itr1.set(j, b);
                }
                if (events_itr1.get(j) == b) {
                    events_itr1.set(j, a);
                }
            }
        }
    }
    
    public void sortVertex()
    {
        // sort vertex into sorted_itr with quicksort
        sort(events_itr,0,total_slot-1);
    }
    
    private int partition(LinkedList<Integer> arr[], int low, int high)
    {
        int pivot = arr[high].size(); 
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j].size() >= pivot)
            {
                i++;
 
                // swap arr[i] and arr[j]
                LinkedList<Integer> temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swapElementList(i,j);
                
                // swap related event
                Event eventTemp = events[i];
                events[i] = events[j];
                events[j] = eventTemp;
            }
        }
 
        // swap arr[i+1] and arr[high] (or pivot)
        LinkedList<Integer> temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        swapElementList(i+1,high);
        
        // swap related event
        Event eventTemp = events[i+1];
        events[i+1] = events[high];
        events[high] = eventTemp;
 
        return i+1;
    }
    
    private void sort(LinkedList<Integer> arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(arr, low, high);
 
            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
    
    public void welshPowellColoring()
    {
        // use sortVertex method to sort the events_itr & events
        // it's sorted base on number on edge (number of vertex that linked to)
        sortVertex();
        
        // result of allocated time for every event, -1 means unschedulled
        result = new int[total_slot];
        for (int u = 0; u < total_slot; u++)
            result[u] = -1;

        // A temporary array to store the available colors.
        boolean available[] = new boolean[time_domain];
        for (int cr = 0; cr < time_domain; cr++)
            available[cr] = true;
        
        int cur = 0;
        // Going through event array that have been sorted
        while (cur < total_slot) {
            if(result[cur] == -1) {
                // Find available time for the event
                // and flag the color(time) as unavailable
                int[] availableTimes = fillPossibleTime(events[cur]);
                
                // Find available color(time) for current(cur) event
                int i = 0;
                while(i < time_domain) {
                    if (available[i] == true && availableTimes[i] == 1) {
                        result[cur] = i;
                        available[i] = false;
//                        System.out.println(events[cur].title + " = " + result[cur]);
                        break;
                    }
                    i++;
                }
                
                // Find other vertex(event) that can be assign on that color(time)
                int itr = cur+1;
                while(itr < total_slot && i < time_domain) {
                    // If the vertex not assigned yet
                    if(result[itr] == -1){
                        boolean noAdjacentSameColor = true;
                        // checking adjacent color(time)
                        ListIterator<Integer> it = events_itr[itr].listIterator() ;
//                        events_itr[itr].forEach(System.out::println);
//                        System.out.print(events[itr].title + " -> ");
                        while (it.hasNext())
                        {
                            int j = it.next();
//                            System.out.print(j + " " + events[j].title + "=" + result[j] + " | ");
                            if (result[j] == i) {
                                noAdjacentSameColor = false;
                                break;
                            }
                        }
//                        System.out.println();
                        // checking possible event time (based on personil schedule)
                        if(noAdjacentSameColor){
                            availableTimes = fillPossibleTime(events[itr]);
                            if(availableTimes[i] == 1) {
                                result[itr] = i;
//                                System.out.println("Bisa warna sama : " + events[itr].title + " = " + result[itr]);
                            }
                        }
                    }
                    itr++;
                }
                
            }
            cur++;
        }
        
        // Show result coloring(scheduling)
//        for (int i = 0; i < total_slot; i++) {
//            System.out.println(events[i].title + " = " + result[i]);
//        }
//        System.out.println("====== DONE =======");
    }
    
    public void greedyColoring()
    {
        int result[] = new int[total_slot];
 
        // Initialize remaining V-1 vertices as unassigned
        for (int u = 0; u < total_slot; u++)
            result[u] = -1;  // no color is assigned to u
 
        // A temporary array to store the available colors. True
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean available[] = new boolean[time_domain];
        for (int cr = 0; cr < time_domain; cr++)
            available[cr] = false;
 
        // Assign colors to remaining V-1 vertices
        for (int u = 0; u < total_slot; u++)
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
            
            // Find available time for the event
            // and flag the color(time) as unavailable
            int[] availableTimes = fillPossibleTime(events[u]);
            
            // Find the first available color
            int cr;
            for (cr = 0; cr < time_domain; cr++)
                if (available[cr] == false && availableTimes[cr] == 1)
                    break;
            
            if(cr <= 7)
                result[u] = cr; // Assign the found color
 
            System.out.println(u + " = " + result[u]);
            
            // Reset the values back to false for the next iteration
            for (cr = 0; cr < time_domain; cr++)
                available[cr] = false;
        }
 
        // print the result
        for (int u = 0; u < total_slot; u++)
            System.out.println("Vertex " + u + " --->  Color "
                                + result[u]);
    }
    
}
