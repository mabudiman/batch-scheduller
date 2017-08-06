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
public class Person {
    public String nama;
    public int id;
    public String ket;  // keterangan
    
    Person(int id){
        this.id = id;
        this.nama = "Dummy Person "+id;
    }
}
