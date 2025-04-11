package com.bridelabz.csvhandling.intermediateproblems.modifyfile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Class to update salaries of employees whose department is IT in CSV File
public class ModifyCSVFile {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/intermediateproblems/modifyfile/Employees.csv";

        // List to store updated lines
        List<String> newLines = new ArrayList<>();
        int salaryIndex = -1, departmentIndex = -1;

        // Use try-catch to handle exceptions
        // 1. Read the file and update lines
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while((line=br.readLine())!=null) {
                String[] values = line.split(",");

                if(isHeader) {
                    for(int i=0;i<values.length;i++) {
                        if(values[i].equalsIgnoreCase("salary")) salaryIndex = i;
                        else if(values[i].equalsIgnoreCase("department")) departmentIndex = i;
                    }
                    isHeader = false;
                } else {
                    if(values[departmentIndex].trim().equalsIgnoreCase("it")) {
                        double salary = Double.parseDouble(values[salaryIndex].trim());
                        values[salaryIndex] = String.format("%.2f", salary + 0.1 * salary);
                    }
                }
                newLines.add(String.join(",", values));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // 2. Write lines to the file
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for(String newLine:newLines) {
                bw.write(newLine);
                bw.newLine();
            }
            System.out.println("Salaries updated successfully!!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Salaries updated successfully!!