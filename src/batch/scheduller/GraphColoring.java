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
 * @author Arief
 */
public class GraphColoring {
    private int total_slot;   // No. of vertices
    private LinkedList<Integer> events[]; //Adjacency List
 
    //Constructor
    GraphColoring(int total_slot)
    {
        this.total_slot = total_slot;
        events = new LinkedList[total_slot];
        for (int i=0; i<total_slot; ++i)
            events[i] = new LinkedList();
    }
 
    //Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        events[v].add(w);
        events[w].add(v); //Graph is undirected
    }
 
    // Assigns colors (starting from 0) to all vertices and
    // prints the assignment of colors
    void greedyColoring()
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
            Iterator<Integer> it = events[u].iterator() ;
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
            it = events[u].iterator() ;
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
 
    // Driver method
    public static void main(String args[])
    {
        GraphColoring g1 = new GraphColoring(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        System.out.println("Coloring of graph 1");
        g1.greedyColoring();
 
        System.out.println();
        GraphColoring g2 = new GraphColoring(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(4, 3);
        System.out.println("Coloring of graph 2 ");
        g2.greedyColoring();
    }
}
