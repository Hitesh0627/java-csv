package com.bridelabz.csvhandling.advanceproblems.mergefiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Class to merge two CSV Files
public class MergeCSVFiles {
    public static void main(String[] args) {
        // Define file paths
        String filePath1 = "src/main/java/com/bridelabz/csvhandling/advanceproblems/mergefiles/Students1.csv";
        String filePath2 = "src/main/java/com/bridelabz/csvhandling/advanceproblems/mergefiles/Students2.csv";
        String filePath3 = "src/main/java/com/bridelabz/csvhandling/advanceproblems/mergefiles/Students3.csv";

        Map<String, List<String>> students = new LinkedHashMap<>();

        // Use try-catch to get fields of file1
        try(CSVReader reader = new CSVReader(new FileReader(filePath1))) {
            String[] line;
            reader.readNext(); // Skip header
            while((line=reader.readNext())!=null) {
                String id = line[0];
                students.put(id, new ArrayList<>(Arrays.asList(line)));
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Use try-catch to get fields of file2
        try(CSVReader reader = new CSVReader(new FileReader(filePath2))) {
            String[] line;
            reader.readNext(); // Skip header
            while((line=reader.readNext())!=null) {
                String id = line[0];
                if(students.containsKey(id)) {
                    students.get(id).addAll(Arrays.asList(line[1], line[2]));
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Use try-catch to write to third file
        try(CSVWriter writer = new CSVWriter(new FileWriter(filePath3))) {
            // CSV header
            writer.writeNext(new String[]{"ID", "Name", "Age", "Marks", "Grade"});
            for(List<String> data:students.values()) {
                writer.writeNext(data.toArray(new String[0]));
            }
            System.out.println("CSV files merged successfully");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// CSV files merged successfully