/*
 * Copyright (C) 2018 Arief
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Arief
 */
public class JSONGenerator {
    
    private String path;
    
    public JSONGenerator(int total_event, int total_personil, int schedule_percentage, 
                         int time_block, int total_slot, int total_data) {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (HH.mm.ss)");
        Date date = new Date();
        for (int k = 0; k < total_data; k++) {
            path = "datatest/" + dateFormat.format(date) +"/data "+k+".json";
           
            JSONArray existing_schedule = new JSONArray();
            /*
                "name": "Dr. H. Enjang Ali Nurdin, M.Kom.",
                "schedule": "10111011"
            */
            for (int i = 0; i < total_personil; i++) {
                JSONObject personil = new JSONObject();
                String schedule = generateRandomSchedule(total_slot,schedule_percentage);
                personil.put("name", "personil "+i);
                personil.put("schedule", schedule);
                existing_schedule.add(personil);
            }

            JSONArray events = new JSONArray();
            /*
                "title": "Winapamungkas Rino",
                "description": "Sidang Skripsi",
                "time_length": "60",
                "personils": [
                  "Dr. H. Enjang Ali Nurdin, M.Kom.",
                  "Lala Septem Riza, M.T.Ph.D.",
                  "Novi Sofia Fitriasari, M.T."
                ]
            */
            for (int i = 0; i < total_event; i++) {
                JSONObject event = new JSONObject();
                event.put("title", "event "+i);
                event.put("description", "event "+i);
                event.put("time_length", Integer.toString(time_block));
                JSONArray personils = new JSONArray();
                int arr_personil[] = new int[total_personil];
                for (int j = 0; j < total_personil; j++) {
                    arr_personil[j] = 1;
                }
                for (int j = 0; j < 3; j++) {
                    int selected = selectRandomFromArray(arr_personil);
                    arr_personil[selected] = 0;
                    personils.add("personil "+selected);
                }
                event.put("personils", personils);
                events.add(event);
            }

            JSONObject details = new JSONObject();
            /*
                "time_block" : "60",
                "total_slot" : "8"
            */
            details.put("time_block", Integer.toString(time_block));
            details.put("total_slot", Integer.toString(total_slot));

            JSONObject data = new JSONObject();
            data.put("events", events);
            data.put("existing_schedule", existing_schedule);
            data.put("details", details);
            this.generate(data,path);
        }
    }
    
    public JSONGenerator(int total_event, int total_personil, int busy, int normal, int loose, 
                         int time_block, int total_slot, int total_data) {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (HH.mm.ss)");
        Date date = new Date();
        for (int k = 0; k < total_data; k++) {
            path = "datatest/" + dateFormat.format(date) +"/data "+k+".json";
           
            JSONArray existing_schedule = new JSONArray();
            /*
                "name": "Dr. H. Enjang Ali Nurdin, M.Kom.",
                "schedule": "10111011"
            */
            for (int i = 0; i < total_personil; i++) {
                JSONObject personil = new JSONObject();
                // personil schedule always start from busy people, then normal, then loose
                String schedule;
                if(i < busy){
                    schedule = generateRandomSchedule(total_slot,20);
                }else if(i < normal+busy){
                    schedule = generateRandomSchedule(total_slot,50);
                }else{
                    schedule = generateRandomSchedule(total_slot,80);
                }
                personil.put("name", "personil "+i);
                personil.put("schedule", schedule);
                existing_schedule.add(personil);
            }

            JSONArray events = new JSONArray();
            /*
                "title": "Winapamungkas Rino",
                "description": "Sidang Skripsi",
                "time_length": "60",
                "personils": [
                  "Dr. H. Enjang Ali Nurdin, M.Kom.",
                  "Lala Septem Riza, M.T.Ph.D.",
                  "Novi Sofia Fitriasari, M.T."
                ]
            */
            for (int i = 0; i < total_event; i++) {
                JSONObject event = new JSONObject();
                event.put("title", "event "+i);
                event.put("description", "event "+i);
                event.put("time_length", Integer.toString(time_block));
                JSONArray personils = new JSONArray();
                int arr_personil[] = new int[total_personil];
                for (int j = 0; j < total_personil; j++) {
                    arr_personil[j] = 1;
                }
                for (int j = 0; j < 3; j++) {
                    int selected = selectRandomFromArray(arr_personil);
                    arr_personil[selected] = 0;
                    personils.add("personil "+selected);
                }
                event.put("personils", personils);
                events.add(event);
            }

            JSONObject details = new JSONObject();
            /*
                "time_block" : "60",
                "total_slot" : "8"
            */
            details.put("time_block", Integer.toString(time_block));
            details.put("total_slot", Integer.toString(total_slot));

            JSONObject data = new JSONObject();
            data.put("events", events);
            data.put("existing_schedule", existing_schedule);
            data.put("details", details);
            this.generate(data,path);
        }
    }
    
    public String getLastPath() {
        return path;
    }
    
    private void generate(JSONObject data, String path) {
        FileWriter fw;
        try {
            Path pathToFile = Paths.get(path);
            if(Files.notExists(pathToFile.getParent())){
                Files.createDirectories(pathToFile.getParent());
                Files.createFile(pathToFile);
            }
            File file = new File(path);
            fw = new FileWriter(file);
            fw.write(data.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private int selectRandomFromArray(int[] array) {
        int rnd = new Random().nextInt(array.length);
        while(array[rnd] == 0){
            rnd = new Random().nextInt(array.length);
        }
        return rnd;
    }
    
    private String generateRandomSchedule(int total_slot, int schedule_percentage) {
        int array[] = new int[total_slot];
        for (int i = 0; i < total_slot; i++) {
            array[i] = 1;
        }
        int total_schedule = schedule_percentage * total_slot / 100;
        for (int i = 0; i < total_schedule; i++) {
            array[selectRandomFromArray(array)] = 0;
        }
        
        StringBuilder sb=new StringBuilder();  
        for (int arr : array) {
            if(arr == 0)
                sb.append("0");
            else
                sb.append("1");
        }
        
        return sb.toString();
    }
    
}
