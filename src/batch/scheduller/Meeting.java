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
public class Meeting {
    private int invite; // jumlah yang diundang
    private int min;    // kuorum pertemuan
    private String name;
    public Person[] personil;  // person yang di undang
    
    Meeting(String name,int invite,int min,int[] idPersonils){
        this.name = name;
        this.invite = invite;
        this.min = min;
        this.inisialisasi(idPersonils);
    }
    
    private void inisialisasi(int[] idPersonils){
        personil = new Person[invite];
        for(int i = 0; i<invite;i++){
            personil[i] = new Person(idPersonils[i]);
        }
    }
}
