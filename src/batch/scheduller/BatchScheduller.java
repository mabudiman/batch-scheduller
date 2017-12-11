/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.scheduller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        Map<String,Personil> personils_data = new HashMap<>();
        Event[] events = new Event[]{};
         
        try (FileReader reader = new FileReader("data.json"))
        {
            // Read JSON file
            Object data = jsonParser.parse(reader);
            // System.out.println(data);
            
            // Parse data.json (existing_schedule) into personils_data
            personils_data = parseDataJson((JSONObject) data);
            
            // Parse data.json (events) into events
            events = parseEventJson((JSONObject) data, personils_data);
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    private static Map<String,Personil> parseDataJson(JSONObject data)
    {
        Map<String,Personil> personils_data = new HashMap<>();
        
        // Get existing schedule
        JSONArray ex_schedule = (JSONArray) data.get("existing_schedule");
        for (int i = 0; i < ex_schedule.size() ; i++) {
            // System.out.println( ex_schedule.get(i) );
            JSONObject person = (JSONObject) ex_schedule.get(i);
            String name = (String) person.get("name");
            String schedule = (String) person.get("schedule");
            //System.out.println( name + " | " + schedule);
            personils_data.put(name, new Personil(name, new TimeTable(60,7,schedule)));
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
                //System.out.print(dosen.get(j));
            }
            
            result[i] = new Event(title,description,time_length,personils);
            // System.out.println(title + " | " + description + " | " + time_length);
        }
        
        return result;
    }
    
}
