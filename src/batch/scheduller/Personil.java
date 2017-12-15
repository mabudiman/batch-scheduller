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
