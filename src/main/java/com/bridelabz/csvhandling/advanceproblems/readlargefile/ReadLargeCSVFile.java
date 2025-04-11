package com.bridelabz.csvhandling.advanceproblems.readlargefile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class to read a large CSV file efficiently
public class ReadLargeCSVFile {
    public static void main(String[] args) {
        // Define file Path
        String filePath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/readlargefile/LargeCSVFile.csv";

        // Create the file
        createFile(filePath);

        // Process the file
        processFile(filePath);
    }

    // method to generate a large csv file
    public static void createFile(String filePath) {
        int numRecords = 2_000_000;
        String[] grades = {"A", "B", "C", "D", "E"};
        Random random = new Random();

        try(FileWriter fw = new FileWriter(filePath)) {
            fw.write("ID,Name,Age,Marks,Grade\n");

            for(int i=0;i<numRecords;i++) {
                int age = 18 + random.nextInt(5);
                int marks = random.nextInt(100);
                String grade = grades[random.nextInt(grades.length)];
                fw.write(i + ",Student" + i + "," + age + "," + marks + "," + grade + "\n");

                if(i % 100000 == 0) {
                    System.out.println("Written: " + i + " records...");
                }
            }
            System.out.println("Large CSV created successfully!!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method to process file efficiently
    public static void processFile(String filePath) {
        int linesAtOnce = 100;
        int totalProcessed = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
           String line;
            List<String> lines = new ArrayList<>();
            br.readLine(); // Skip header

            while((line=br.readLine())!=null) {
                lines.add(line);
                if(lines.size()==linesAtOnce) {
                    totalProcessed += linesAtOnce;
                    System.out.println("Processed: " + totalProcessed + " records.");
                    lines.clear();
                }
            }

            if(!lines.isEmpty()) {
                totalProcessed += linesAtOnce;
                System.out.println("Processed: " + totalProcessed + " records.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
