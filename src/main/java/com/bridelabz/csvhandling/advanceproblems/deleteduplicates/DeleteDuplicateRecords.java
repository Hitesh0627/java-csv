package com.bridelabz.csvhandling.advanceproblems.deleteduplicates;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Class to remove duplicates from a CSV file based on ID
public class DeleteDuplicateRecords {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/deleteduplicates/FilewithDuplicates.csv";

        Map<String, List<String>> entries = new LinkedHashMap<>();

        // Use try-catch to handle exceptions
        try(CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean header = true;
            System.out.println("Duplicate records in file based on ID: ->");
            while((line=reader.readNext())!=null) {
                if(header) {
                    System.out.println("-------------------------------------------------");
                    System.out.print("|");
                    for (String value : line) {
                        System.out.printf("%-10s |", value);
                    }
                    System.out.println();
                    System.out.println("-------------------------------------------------");
                    header = false;
                }
                else if(!entries.containsKey(line[0])) {
                    entries.put(line[0], new ArrayList<>(Arrays.asList(line)));
                } else {
                    System.out.print("|");
                    for(String value : line) {
                        System.out.printf("%-10s |", value);
                    }
                    System.out.println();
                    System.out.println("-------------------------------------------------");
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error: " +e.getMessage());
        }
    }
}
// Sample Output ->
// Duplicate records in file based on ID: ->
// -------------------------------------------------
// |ID         |Name       |Age        |Gender     |
// -------------------------------------------------
// |3          |Dinesh     |33         |Male       |
// -------------------------------------------------
// |4          |Ekta       |36         |Female     |
// -------------------------------------------------