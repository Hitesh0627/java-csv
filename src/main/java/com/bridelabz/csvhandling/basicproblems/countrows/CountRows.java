package com.bridelabz.csvhandling.basicproblems.countrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class to count number of rows in a csv file using bufferedReader
public class CountRows {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/basicproblems/countrows/Sample.csv";

        // Use try-catch to handle exceptions
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int i=0;
            while(br.readLine()!=null) {
                i++;
            }
            System.out.println("Number of rows in file(excluding header row): " + (i-1));
        } catch (IOException e) {
            System.out.println("An error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Number of rows in file(excluding header row): 10