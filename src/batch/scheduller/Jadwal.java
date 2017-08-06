/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch.scheduller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author M. Arief Budiman
 */
public class Jadwal {
    private int timeSlot;
    private int banyakPersonil;
    private int[][] jadwalSekarang;
    
    Jadwal(int t,int p){
        this.timeSlot = t;
        this.banyakPersonil = p;
    }
    
    void randomJadwal(int kesibukan){
        jadwalSekarang = new int[banyakPersonil][timeSlot];
        for(int i = 0; i < banyakPersonil; i++){
            List<Integer> list = new ArrayList<>(timeSlot);
            int j;
            
            for (j = 0 ; j < timeSlot ; j++)
                list.add(j);
            Collections.shuffle(list);
            
            int n = timeSlot * kesibukan / 100;
            for (j = 0; j < n; j++)
                jadwalSekarang[i][list.get(j)] = 1;
            
        }
    }
    
    int[][] getJadwal(){
        return this.jadwalSekarang;
    }
    
    void setTimeSlot(int t){
        this.timeSlot = t;
    }
    
    void setBanyakPersonil(int p){
        this.banyakPersonil = p;
    }
    
    int getTimeSlot(){
        return this.timeSlot;
    }
    
    int getBanyakPersonil(){
        return this.banyakPersonil;
    }
    
}
