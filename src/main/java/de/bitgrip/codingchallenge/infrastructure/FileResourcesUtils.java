package de.bitgrip.codingchallenge.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileResourcesUtils {

    public InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    // public File getFileFromResource(String fileName) throws URISyntaxException {

    // ClassLoader classLoader = getClass().getClassLoader();
    // URL resource = classLoader.getResource(fileName);
    // if (resource == null) {
    // throw new IllegalArgumentException("file not found! " + fileName);
    // } else {
    // return new File(resource.toURI());
    // }

    // }

    public List<List<String>> getLines(InputStream is) {
        List<List<String>> records = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                records.add(getRecordFromLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}
