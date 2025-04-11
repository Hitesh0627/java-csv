package com.bridelabz.csvhandling.advanceproblems.convertfileformats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Class to convert JSON data to CSV and vice-versa
public class ConvertFileFormat {

    public static void main(String[] args) {
        // Define file paths
        String JSONinput = "src/main/java/com/bridelabz/csvhandling/advanceproblems/convertfileformats/JSONinput.json";
        String CSVinput = "src/main/java/com/bridelabz/csvhandling/advanceproblems/convertfileformats/CSVinput.csv";
        String JSONoutput = "src/main/java/com/bridelabz/csvhandling/advanceproblems/convertfileformats/JSONoutput.json";
        String CSVoutput = "src/main/java/com/bridelabz/csvhandling/advanceproblems/convertfileformats/CSVoutput.csv";

        // convert json to csv
        convertTOCSV(JSONinput, CSVoutput);

        // convert csv to json
        convertToJSON(CSVinput, JSONoutput);
    }

    // method to convert JSON data to CSV data
    public static void convertTOCSV(String JSONinput, String CSVoutput) {
        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // Use try-catch to handle exceptions
        try {
            // Read JSON array as list of maps
            List<Map<String, Object>> jsonList = mapper.readValue(
                    new File(JSONinput),
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            // Write to CSV
            try(CSVWriter writer = new CSVWriter(new FileWriter(CSVoutput))) {
                // Add header
                Set<String> headers = jsonList.get(0).keySet();
                writer.writeNext(headers.toArray(new String[0]));

                // Write data rows
                for(Map<String, Object> obj:jsonList) {
                    List<String> row = new ArrayList<>();
                    for(String header:headers) {
                        row.add(obj.get(header).toString());
                    }
                    writer.writeNext(row.toArray(new String[0]));
                }
                System.out.println("JSON successfully converted to CSV!");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method to convert csv to json
    public static void convertToJSON(String CSVinput, String JSONouptut) {
        // List to store json object
        List<Map<String, String>> jsonList = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader(CSVinput))) {
            String[] headers = reader.readNext(); // read header
            String[] line;

            while((line=reader.readNext())!=null) {
                Map<String, String> jsonObject = new LinkedHashMap<>();
                for(int i=0;i<headers.length;i++) {
                    jsonObject.put(headers[i],line[i]);
                }
                jsonList.add(jsonObject);
            }

            // Convert list to JSON file
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(JSONouptut), jsonList);

            System.out.println("CSV successfully converted to JSON!");
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
