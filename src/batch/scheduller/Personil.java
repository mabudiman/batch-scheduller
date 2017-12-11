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
public class Personil {
    
    public String name;
    public TimeTable schedule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeTable getSchedule() {
        return schedule;
    }

    public void setSchedule(TimeTable schedule) {
        this.schedule = schedule;
    }
    
    public Personil(String name, TimeTable schedule) {
        this.name = name;
        this.schedule = schedule;
    }
    
}
