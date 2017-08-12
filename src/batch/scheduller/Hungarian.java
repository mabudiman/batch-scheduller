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
public class Hungarian {
    
    private int[][] bobot;
    private int resources;
    private int jobs;
    
    Hungarian(int[][] bobot, int resources, int jobs){
        bobot = new int[resources][jobs];
        this.bobot = bobot;
        this.resources = resources;
        this.jobs = jobs;
    }
    
    void setBobot(int[][] bobotbaru){
        this.bobot = bobotbaru;
    }
    
    void setResources(int resourcesBaru){
        this.resources = resourcesBaru;
    }
    
    void setJobs(int jobsbaru){
        this.jobs = jobsbaru;
    }
    
    public void printBobot()
    {
        for(int i = 0; i < this.resources; i++){
            for(int j = 0; j < this.jobs; j++){
                System.out.print(bobot[i][j] + " ");
            }
            System.out.println();
        }
    }
}
