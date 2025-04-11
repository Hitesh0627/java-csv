package com.bridelabz.csvhandling.intermediateproblems.searchrecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Class to search record in a CSV file
public class SearchRecord {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/intermediateproblems/searchrecord/Employees.csv";

        // Use try-catch to handle exceptions
        try(Scanner input = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Take name input
            System.out.print("Enter the name whose record to search: ");
            String s = input.nextLine();
            String line;
            boolean isHeader = true;
            int nameIndex = 0;
            System.out.println("Record found successfully ->");
            while((line=br.readLine())!=null) {
                String[] values = line.split(",");
                if(isHeader) {
                    System.out.println("-------------------------------------------------");
                    System.out.print("|");
                    for (int i=0;i<values.length;i++) {
                        if(values[i].equalsIgnoreCase("Name")) nameIndex=i;
                        System.out.printf("%-10s |", values[i]);
                    }
                    System.out.println();
                    System.out.println("-------------------------------------------------");
                    isHeader = false;
                } else {
                    if(values[nameIndex].trim().equalsIgnoreCase(s)) {
                        System.out.print("|");
                        for(String value : values) {
                            System.out.printf("%-10s |", value);
                        }
                        System.out.println();
                        System.out.println("-------------------------------------------------");
                        break;
                    }
                }
           }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Enter the name whose record to search: Dinesh
// Record found successfully ->
// -------------------------------------------------
// |ID         |Name       |Department |Salary     |
// -------------------------------------------------
// |4          |Dinesh     |Marketing  |50000      |
// -------------------------------------------------