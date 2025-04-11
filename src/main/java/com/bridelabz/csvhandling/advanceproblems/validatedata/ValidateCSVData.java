package com.bridelabz.csvhandling.advanceproblems.validatedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Validate email and phone number of each record using regex
public class ValidateCSVData {
    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/validatedata/Users.csv";

        // Use try-catch to handle exceptions
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            int emailIndex = -1, phoneIndex = -1;

            while((line=br.readLine())!=null) {
                String[] values = line.split(",");
                if(isHeader) {
                    System.out.println("----------------------------------------------------------------------------");;
                    System.out.print("|");
                    for (int i=0;i<values.length;i++) {
                        if(values[i].equalsIgnoreCase("email")){
                            emailIndex=i;
                            System.out.printf("%-25s |", values[i]);
                        }
                        else if(values[i].equalsIgnoreCase("phone")){
                            phoneIndex = i;
                            System.out.printf("%-10s |", values[i]);
                        }
                        else {
                            System.out.printf("%-10s |", values[i]);
                        }
                    }
                    System.out.println();
                    System.out.println("----------------------------------------------------------------------------");
                    isHeader = false;
                } else {
                    if(values[emailIndex].trim().matches("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}") && values[phoneIndex].trim().matches("\\d{10}")) {
                        System.out.print("|");
                        for(int i=0;i<values.length;i++) {
                            if(i==emailIndex) {
                             System.out.printf("%-25s |", values[i]);
                            } else {
                                System.out.printf("%-10s |", values[i]);
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("Invalid email or phone format");
                    }
                    System.out.println("----------------------------------------------------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
// Sample Output ->
// ----------------------------------------------------------------------------
// |ID         |Name       |Age        |Email                     |Phone      |
// ----------------------------------------------------------------------------
// |1          |Amit       |35         |amit12@gmail.com          |9954423456 |
// ----------------------------------------------------------------------------
// Invalid email or phone format
// ----------------------------------------------------------------------------
// |3          |Chaitanya  |21         |chaitanya33@gmail.com     |6927334567 |
// ----------------------------------------------------------------------------
// |4          |Dinesh     |45         |dinesh45@gmail.com        |9876543210 |
// ----------------------------------------------------------------------------
// Invalid email or phone format
// ----------------------------------------------------------------------------
// |6          |Farhan     |30         |farhan22@outlook.com      |7743533456 |
// ----------------------------------------------------------------------------