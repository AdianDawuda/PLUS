package eot_schachtschneider_kibet_dawuda;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParser {
    // Create Arraylists to store columns of the CSV file
    static ArrayList<String> lng = new ArrayList<>();
    static ArrayList<String> lat = new ArrayList<>();
    static ArrayList<String> tweet = new ArrayList<>();
    static ArrayList<String> createdAt = new ArrayList<>();
    static ArrayList<String> userID = new ArrayList<>();

    /**
     * Parses a CSV file and extracts attributes as specified
     *
     * @param fileToParse the path to the CSV file to parse
     */
    public static void parse(String fileToParse) {
        try {
            // Create a reader to read the CSV file
            FileReader filereader = new FileReader(fileToParse);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

            // Create a String[] to store the line currently being parsed
            String[] nextRecord;

            // Skip the first line containing the column name
            csvReader.readNext();

            // Extract columns of importance line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                lng.add(nextRecord[1]);
                lat.add(nextRecord[2]);
                tweet.add(nextRecord[5]);
                createdAt.add(nextRecord[6]);
                userID.add(nextRecord[7]);

            } // End while
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } // End catch
    } // End parse()
} // End class
