package com.bridelabz.csvhandling.basicproblems.readcsvfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class to read data from a CSV file using BufferReader
public class ReadCSVFile {
    public static void main(String[] args) {
        // Define filePath
        String filePath = "src/main/java/com/bridelabz/csvhandling/basicproblems/readcsvfile/Students.csv";

        // Use try-catch to handle exception
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("CSV file: ->");
            String line;
            boolean isHeader =true;
            while((line=br.readLine())!=null) {
                String[] values = line.split(",");
                if(isHeader) {
                    System.out.println("-------------------------------------------------");
                    System.out.print("|");
                    for (String header : values) {
                        System.out.printf("%-10s |", header);
                    }
                    System.out.println();
                    System.out.println("-------------------------------------------------");
                    isHeader = false;
                } else {
                    System.out.print("|");
                    for(String value : values) {
                        System.out.printf("%-10s |", value);
                    }
                    System.out.println();
                    System.out.println("-------------------------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// CSV file: ->
// -------------------------------------------------
// | ID        | Name      | Age       | Marks     |
// -------------------------------------------------
// | 1         | Amit      | 21        | 85.5      |
// -------------------------------------------------
// | 2         | Bhanu     | 20        | 90        |
// -------------------------------------------------
// | 3         | Chaitanya | 22        | 78.5      |
// -------------------------------------------------
// | 4         | Dinesh    | 23        | 82        |
// -------------------------------------------------
// | 5         | Esha      | 21        | 92        |
// -------------------------------------------------
// | 6         | Farhan    | 22        | 88        |
// -------------------------------------------------
// | 7         | Gaurav    | 20        | 76        |
// -------------------------------------------------
// | 8         | Harsh     | 21        | 95        |
// -------------------------------------------------
// | 9         | Isha      | 22        | 80        |
// -------------------------------------------------
// | 10        | Jayant    | 23        | 87        |
// -------------------------------------------------