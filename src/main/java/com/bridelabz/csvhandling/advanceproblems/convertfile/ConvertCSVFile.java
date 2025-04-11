package com.bridelabz.csvhandling.advanceproblems.convertfile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.List;

// Class to convert CSV file data to objects
public class ConvertCSVFile {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/convertfile/Students.csv";

        // Use try-catch to handle exceptions
        try(FileReader fr = new FileReader(filePath)) {
            CsvToBean<Student> students = new CsvToBeanBuilder<Student>(fr)
                    .withType(Student.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Student> studentList = students.parse();

            for(Student student:studentList) {
                System.out.println("Student ID: " + student.getID() + "| Name: " + student.getName() + "| Roll Number: " + student.getRollNumber() + "| Age: " + student.getAge());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample output ->
// Student ID: 1| Name: Amit| Roll Number: 101| Age: 20
// Student ID: 2| Name: Bhanu| Roll Number: 102| Age: 21
// Student ID: 3| Name: Chaitanya| Roll Number: 103| Age: 22
// Student ID: 4| Name: Dinesh| Roll Number: 104| Age: 23
// Student ID: 5| Name: Ekta| Roll Number: 105| Age: 24
// Student ID: 6| Name: Farhan| Roll Number: 106| Age: 25
// Student ID: 7| Name: Gaurav| Roll Number: 107| Age: 26
// Student ID: 8| Name: Himanshu| Roll Number: 108| Age: 27
// Student ID: 9| Name: Isha| Roll Number: 109| Age: 28
// Student ID: 10| Name: Jaspreet| Roll Number: 110| Age: 29