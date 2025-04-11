package com.bridelabz.csvhandling.intermediateproblems.filterstudents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class to filter students with marks>=80 from a csv file
public class FilterStudents {
    public static void main(String[] args) {
        // Define file Path
        String filePath = "src/main/java/com/bridelabz/csvhandling/intermediateproblems/filterstudents/Students.csv";

        // use try-catch to handle exceptions
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            int marksIndex = 0;
            String line;
            System.out.println("Students with marks greater than 80: ->");
            while((line=br.readLine())!=null) {
                String[] values = line.split(",");
                if(isHeader) {
                    System.out.println("-------------------------------------------------");
                    System.out.print("|");
                    for (int i=0;i<values.length;i++) {
                        if(values[i].equalsIgnoreCase("marks")) marksIndex=i;
                        System.out.printf("%-10s |", values[i]);
                    }
                    System.out.println();
                    System.out.println("-------------------------------------------------");
                    isHeader = false;
                } else {
                    if(Double.parseDouble(values[marksIndex])>80) {
                        System.out.print("|");
                        for(String value : values) {
                            System.out.printf("%-10s |", value);
                        }
                        System.out.println();
                        System.out.println("-------------------------------------------------");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Students with marks greater than 80: ->
// -------------------------------------------------
// |ID         |Name       |Age        |Marks      |
// -------------------------------------------------
// |1          |Amit       |21         |85.5       |
// -------------------------------------------------
// |2          |Bhanu      |20         |90         |
// -------------------------------------------------
// |4          |Dinesh     |23         |82         |
// -------------------------------------------------
// |5          |Esha       |21         |92         |
// -------------------------------------------------
// |6          |Farhan     |22         |88         |
// -------------------------------------------------
// |8          |Harsh      |21         |95         |
// -------------------------------------------------
// |10         |Jayant     |23         |87         |
// -------------------------------------------------