package com.bridelabz.csvhandling.advanceproblems.convertfile;

import com.opencsv.bean.CsvBindByName;

// Class for object template for file
public class Student {
    // Attributes
    @CsvBindByName(column = "ID")
    private int ID;
    @CsvBindByName(column = "Name")
    private String Name;
    @CsvBindByName(column = "RollNumber")
    private int RollNumber;
    @CsvBindByName(column = "Age")
    private int Age;

    // Constructor
    public Student() {
    }

    // getters
    public int getID() { return ID; }
    public String getName() { return Name; }
    public int getRollNumber() { return RollNumber; }
    public int getAge() { return Age; }

    // setters
    public void setID(int id) {
        this.ID = id;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void setRollNumber(int rollNumber) {
        this.RollNumber = rollNumber;
    }
    public void setAge(int age) {
        this.Age = age;
    }
}
