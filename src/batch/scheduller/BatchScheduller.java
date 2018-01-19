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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author M. Arief Budiman
 */
public class BatchScheduller {
    
    public BatchScheduller() {
    
    }
    
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        // personils_data is existing every personil schedule
        Map<String,Personil> personils_data = new HashMap<>();
        // events is every event to be scheduled
        Event[] events = new Event[]{};
        // detail timetable variables
        int time_block;
        int total_slot;
         
        try (FileReader reader = new FileReader("data.json"))
        {
            // Read JSON file
            Object data = jsonParser.parse(reader);
            // System.out.println(data);
            
            // Parse data.json (details) into time_block, total_slots
            time_block = parseTimeBlockJson((JSONObject) data);
            total_slot = parseTotalSlotJson((JSONObject) data);
            
            // Parse data.json (existing_schedule) into personils_data
            personils_data = parseDataJson((JSONObject) data,time_block,total_slot);
            
            // Parse data.json (events) into events
            events = parseEventJson((JSONObject) data, personils_data);
            
            // Check data for log
            // showParsedData(personils_data,events);
            
            // Execute GraphMapping and GraphColoring
            GraphMapping GM = new GraphMapping(events);
            GM.setTotalTimeSlot(total_slot);
            GM.welshPowellColoring();
            
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    private static Map<String,Personil> parseDataJson(JSONObject data, int time_block, int total_slot)
    {
        Map<String,Personil> personils_data = new HashMap<>();
        
        // Get existing schedule
        JSONArray ex_schedule = (JSONArray) data.get("existing_schedule");
        for (int i = 0; i < ex_schedule.size() ; i++) {
            JSONObject person = (JSONObject) ex_schedule.get(i);
            String name = (String) person.get("name");
            String schedule = (String) person.get("schedule");
            personils_data.put(name, new Personil(name, new TimeTable(time_block,total_slot,schedule)));
        }
        
        return personils_data;
        
    }
    
    private static Event[] parseEventJson(JSONObject data, Map<String,Personil> personils_data) {
        
        // Get events array
        JSONArray events = (JSONArray) data.get("events");
        Event[] result =  new Event[events.size()];
        
        // Iterate array to get all event
        for (int i=0; i< events.size(); i++) {
            JSONObject item = (JSONObject) events.get(i);
            
            String title = (String) item.get("title");
            String description = (String) item.get("description");
            int time_length = Integer.parseInt( (String) item.get("time_length") );
            JSONArray dosen = (JSONArray) item.get("personils");
            
            Personil[] personils = new Personil[dosen.size()];
            for (int j = 0; j < dosen.size(); j++) {
                personils[j] = personils_data.get((String)dosen.get(j));
            }
            
            result[i] = new Event(title,description,time_length,personils);
        }
        
        return result;
    }
    
    public static int parseTimeBlockJson(JSONObject data) {
        JSONObject details = (JSONObject) data.get("details");
        return Integer.parseInt((String) details.get("time_block"));
    }
    
    public static int parseTotalSlotJson(JSONObject data) {
        JSONObject details = (JSONObject) data.get("details");
        return Integer.parseInt((String) details.get("total_slot"));
    }
    
    public static void showParsedData(Map<String,Personil> personils_data, Event[] events) {
        System.out.print("Personils:\n");
        // Check personils data
        for (Map.Entry entry : personils_data.entrySet()) {
            System.out.println("key: " + entry.getKey() + " | value :");
            Personil p = (Personil) entry.getValue();
            System.out.println(" | " + p.name);
            TimeTable t = p.schedule;
            for (Event ts : t.time_slots) {
                if(ts != null) {
                    System.out.print(" | " + ts.title);
                }else{
                    System.out.print(" | kosong");
                }
            }
            System.out.print("\n");
        }
        
        System.out.print("\n\nEvents:\n");
        // Check events data
        for (Event event : events) {
            System.out.println(event.title + " : ");
            for (Personil p : event.personils) {
                System.out.println(" - " + p.name);
            }
        }
    }
    
    
    
}
