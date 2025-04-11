package com.bridelabz.csvhandling.advanceproblems.generatereport;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Class to generate a CSV file from a database file
public class GenerateCSVReport {
    public static void main(String[] args) {
        // Define file paths
        String sourcePath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/generatereport/SampleDatabase.txt";
        String destinationPath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/generatereport/CSVReport.csv";

        // read lines from a file and store it in hashmap
        Map<String, List<String>> dataMap = new HashMap<>();
        readFile(dataMap, sourcePath);

        // write lines to a csv file
        writeFile(dataMap, destinationPath);
    }

    // method to read data from a file
    public static void readFile(Map<String, List<String>> dataMap, String sourcePath) {
        // Use try catch to store result
        try(BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {
            String line;
            while((line=br.readLine())!=null) {
                String[] values = line.split("\\|");
                for(String value:values) {
                    String[] pairs = value.trim().split(":");
                    if(!dataMap.containsKey(pairs[0])) {
                        dataMap.put(pairs[0], new ArrayList<>(Collections.singletonList(pairs[1])));
                    } else {
                        dataMap.get(pairs[0]).add(pairs[1]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method to write data to csv file
    public static void writeFile(Map<String, List<String>> dataMap, String destinationPath) {
        // Use try-catch to handle exceptions
        try(CSVWriter writer = new CSVWriter(new FileWriter(destinationPath))) {
            writer.writeNext(new String[]{"ID","Name","Age","Gender"});
            String[] format = {"ID","Name","Age","Gender"};
            int numRows = dataMap.get("ID").size();

            for(int i=0;i<numRows;i++) {
                String[] row = new String[4];
                for(int j=0;j<4;j++) {
                    List<String> values = dataMap.get(format[j]);
                    row[j] = (i<values.size())?values.get(i) : ""; // fallback to empty string if size mismatch
                }
                writer.writeNext(row);
            }
            System.out.println("Data transferred to CSV file successfully!!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
