/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Voluntary;

/**
 *
 * @author ashikakalmady
 */
public class VitalSigns {
   private int respiratoryRate;
   private int heartRate;
   private double temperature;
   private double BP;
   private float weight;
   private float height;
   
   
   public VitalSigns() {
       
   }
   
   public VitalSigns(int respiratoryRate, int heartRate, double temperature, double BP, float weight, float height) {
       this.respiratoryRate = respiratoryRate;
       this.heartRate = heartRate;
       this.weight = weight;
       this.BP = BP;
       this.height = height;
       this.temperature = temperature;
   }
   
   public int getRespiratoryRate() {
        return respiratoryRate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setRespiratoryRate(int respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getBP() {
        return BP;
    }

    public void setBP(double BP) {
        this.BP = BP;
    }
    
    
}

