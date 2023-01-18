/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Patient;

import Business.UserAccount.UserAccount;
import java.util.Date;

/**
 *
 * @author Gursheen Kaur
 */
public class Patient {
    private String patientId;
    private String patientName;
    private String gender;
    private Date dateOfBirth;
    private int age;
    private String issue;
    private double bp;
    private double heartrate;
    private double pulse;
    private double weight;
    private double height;
    private UserAccount assignedStaff;
    private UserAccount assignedDoctor;
    private UserAccount assignedVolunteer;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public double getBp() {
        return bp;
    }

    public void setBp(double bp) {
        this.bp = bp;
    }

    public double getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(double heartrate) {
        this.heartrate = heartrate;
    }

    public double getPulse() {
        return pulse;
    }

    public void setPulse(double pulse) {
        this.pulse = pulse;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public UserAccount getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(UserAccount assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public UserAccount getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(UserAccount assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public UserAccount getAssignedVolunteer() {
        return assignedVolunteer;
    }

    public void setAssignedVolunteer(UserAccount assignedVolunteer) {
        this.assignedVolunteer = assignedVolunteer;
    }
    
    
    
}
