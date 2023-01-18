/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Hospital;

import Business.Community.Community;

/**
 *
 * @author Gursheen Kaur
 */
public class Hospital {
 
    
    private String name;
    private Community community;


    public Hospital() {
    }

    public Hospital(String name,  Community community) {
        this.name = name;
        
        this.community = community;
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    
    
      
      @Override
    public String toString() {
        return name;
    }
    
}
