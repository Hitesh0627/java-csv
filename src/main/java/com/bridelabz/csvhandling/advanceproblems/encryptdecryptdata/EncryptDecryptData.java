package com.bridelabz.csvhandling.advanceproblems.encryptdecryptdata;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

// Class to encrypt sensitive fields while writing and decrypt while reading
public class EncryptDecryptData {
    private static final String SECRET_KEY = "1234567890123456"; // 16-char key
    private static final String ALGO = "AES";

    public static void main(String[] args) {
        // Define file path
        String filePath = "src/main/java/com/bridelabz/csvhandling/advanceproblems/encryptdecryptdata/EncryptedCSV.csv";

        // D<ata rows
        List<String[]> data = Arrays.asList(
                new String[]{"E001", "Rahul", "30", "Finance", "rahul@xyz.com", "60000"},
                new String[]{"E002", "Sneha", "28", "IT", "sneha@xyz.com", "75000"},
                new String[]{"E003", "Amit", "35", "HR", "amit@xyz.com", "55000"},
                new String[]{"E004", "Neha", "25", "Marketing", "neha@xyz.com", "65000"},
                new String[]{"E005", "Vikram", "40", "Operations", "vikram@xyz.com", "80000"},
                new String[]{"E006", "Priya", "29", "Sales", "priya@xyz.com", "70000"}
        );

        // write data to file
        writeToCsv(filePath, data);

        // read from csv
        readFromCsv(filePath);
    }

    // method to write data to csv file
    public static void writeToCsv(String filePath, List<String[]> data) {
        try(CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"ID", "Name", "Age", "Department", "Email", "Salary"});

            for(String[] row:data) {
                String[] encryptedRow = row.clone();
                encryptedRow[4] = encryptWrite(row[4]);
                encryptedRow[5] = encryptWrite(row[5]);
                writer.writeNext(encryptedRow);
            }

            System.out.println("Encrypted CSV written successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method to read data from Csv
    public static void readFromCsv(String filePath) {
        List<String[]> data = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean header=true;

            while((line=reader.readNext())!=null) {
                if(header) {
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-20s | %-8s |\n",
                            "ID", "Name", "Age", "Dept", "Email", "Salary");
                    System.out.println("-----------------------------------------------------------------------------");
                    header = false;
                    continue;
                }

                String[] decryptedRow = line.clone();
                decryptedRow[4] = decryptMessage(line[4]); // decrypt email
                decryptedRow[5] = decryptMessage(line[5]); // decrypt salary
                data.add(decryptedRow);

                // Print in tabular format
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-20s | %-8s |\n",
                        decryptedRow[0], decryptedRow[1], decryptedRow[2],
                        decryptedRow[3], decryptedRow[4], decryptedRow[5]);
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Decrypted CSV read successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method encrypt fields while writing
    public static String encryptWrite(String data) throws Exception {
        SecretKeySpec Key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, Key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // method to decode fields while reading
    public static String decryptMessage(String data) throws Exception {
        SecretKeySpec Key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, Key);
        byte[] decoded = Base64.getDecoder().decode(data);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
// Sample Output ->
// Encrypted CSV written successfully.
// -----------------------------------------------------------------------------
// | ID    | Name       | Age   | Dept       | Email                | Salary   |
// -----------------------------------------------------------------------------
// | E001  | Rahul      | 30    | Finance    | rahul@xyz.com        | 60000    |
// | E002  | Sneha      | 28    | IT         | sneha@xyz.com        | 75000    |
// | E003  | Amit       | 35    | HR         | amit@xyz.com         | 55000    |
// | E004  | Neha       | 25    | Marketing  | neha@xyz.com         | 65000    |
// | E005  | Vikram     | 40    | Operations | vikram@xyz.com       | 80000    |
// | E006  | Priya      | 29    | Sales      | priya@xyz.com        | 70000    |
// -----------------------------------------------------------------------------
// Decrypted CSV read successfully.