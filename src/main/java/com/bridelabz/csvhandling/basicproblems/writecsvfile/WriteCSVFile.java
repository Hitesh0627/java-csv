package com.bridelabz.csvhandling.basicproblems.writecsvfile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Class to write data to a csv file using bufferedWriter
public class WriteCSVFile {
    public static void main(String[] args) {
        // Define destination file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/basicproblems/writecsvfile/Employees.csv";

        // Use try-catch to handle exceptions
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(" ID, Name, Department, Salary\n");
            bw.write(" 1, Amit, Finance, 60000\n");
            bw.write(" 2, Bhanu, HR, 55000\n");
            bw.write(" 3, Chaitanya, IT, 70000\n");
            bw.write(" 4, Dinesh, Marketing, 50000\n");
            bw.write(" 5, Esha, Sales, 65000\n");
            bw.write(" 6, Farhan, IT, 72000\n");
            System.out.println("CSV file created successfully with name: Employees.csv");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
