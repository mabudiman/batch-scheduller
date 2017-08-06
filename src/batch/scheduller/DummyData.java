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
import java.util.Random;

/**
 *
 * @author M. Arief Budiman
 */
public final class DummyData {

    /**
     *
     */
    public Jadwal kesibukanPersonil;

    /**
     *
     */
    public Meeting[] pertemuan;
    public Person[] allPersonils;
    private int jumlahMeeting;
    private Random rand;
    private int banyakPersonil;
    
    /**
     *
     * @param jumlahMeeting
     * @param timeSlot
     * @param banyakPersonil
     * @param kesibukan
     */
    public DummyData(int jumlahMeeting,int timeSlot,int banyakPersonil,int kesibukan){
        this.jumlahMeeting = jumlahMeeting;
        this.banyakPersonil = banyakPersonil;
        pertemuan = new Meeting[jumlahMeeting];
        this.makeDummyJadwal(timeSlot,banyakPersonil,kesibukan);
    }
    
    public void makeDummyMeeting(){
        for(int i = 0; i < jumlahMeeting; i++){
            int invite = rand.nextInt(this.banyakPersonil - 1)+1;
            int min = rand.nextInt(invite)+1;
            List<Person> list = new ArrayList<>(this.banyakPersonil);
            int j;
            
            list.addAll(Arrays.asList(allPersonils));
            Collections.shuffle(list);
            
            int n = banyakPersonil * 50 / 100;
            int[] selectedPersonils = new int[n];
            for (j = 0; j < n; j++)
                selectedPersonils[j] = list.get(j).id;
            
            pertemuan[i] = new Meeting("Meeting "+i,invite,min,selectedPersonils);
        }
    }
    
    public void makeDummyJadwal(int timeSlot, int banyakPersonil,int kesibukan){
        System.out.println("Make dummy jadwal : ");
        Jadwal jdl = new Jadwal(timeSlot,banyakPersonil);
        jdl.randomJadwal(kesibukan);
        // Lihat tabel kesibukan personil
        for (int[] personil : jdl.getJadwal()) {
            for (int j = 0; j < personil.length; j++) {
                System.out.print(personil[j]+" ");
            }
            System.out.println();
        }
    }
    
}
