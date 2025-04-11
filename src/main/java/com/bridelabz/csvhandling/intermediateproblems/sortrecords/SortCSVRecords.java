package com.bridelabz.csvhandling.intermediateproblems.sortrecords;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

// Class to sort CSV records in descending order of salary
public class SortCSVRecords {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/intermediateproblems/sortrecords/Employees.csv";
        TreeMap<Double, String> map = new TreeMap<>((a,b)-> (int) (b-a));

        // Use try-catch to handle exceptions
        // Read and store lines in descending order of salary
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            int salaryIndex = -1;
            while((line=br.readLine())!=null) {
                String[] values = line.split(",");
                if(isHeader) {
                    for(int i=0;i<values.length;i++) {
                        if(values[i].trim().equalsIgnoreCase("salary")) salaryIndex=i;
                    }
                    isHeader = false;
                } else {
                    map.put(Double.parseDouble(values[salaryIndex].trim()), String.join(",", values));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Write back to the file
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("ID,Name,Depatment,Salary\n");
            for(Map.Entry<Double, String> entry: map.entrySet()) {
                bw.write(entry.getValue());
                bw.newLine();
            }
            System.out.println("Employees sorted in order of salary");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display top 5 paid employees
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            int i=0;
            System.out.println("Top 5 Highest-Paid Employees ->");
            while ((line=br.readLine())!=null && i<=5) {
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
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Employees sorted in order of salary
// Top 5 Highest-Paid Employees ->
// -------------------------------------------------
// |ID         |Name       |Depatment  |Salary     |
// -------------------------------------------------
// |10         |Jaspreet   |IT         |154000     |
// -------------------------------------------------
// |9          |Isha       |Finance    |130000     |
// -------------------------------------------------
// |7          |Gaurav     |IT         |121000     |
// -------------------------------------------------
// |8          |Himanshu   |HR         |120000     |
// -------------------------------------------------
// |6          |Farhan     |Finance    |100000     |
// -------------------------------------------------