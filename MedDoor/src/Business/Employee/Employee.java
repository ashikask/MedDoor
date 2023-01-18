/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Employee;

import java.util.UUID;

/**
 *
 * @author ashikakalmady
 */
public class Employee {
     private String name;
    private String id;

    public Employee() {
        String uniqueID = UUID.randomUUID().toString();
        this.id = uniqueID;
       
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
