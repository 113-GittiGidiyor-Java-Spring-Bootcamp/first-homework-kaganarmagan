package com.SMS.models;


import javax.persistence.Entity;

@Entity
public class PermanentInstructor extends Instructor{
    private double fixedSalary;

    public PermanentInstructor(String name, String address, long phoneNumber, double fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public PermanentInstructor() {
    }

    public double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
