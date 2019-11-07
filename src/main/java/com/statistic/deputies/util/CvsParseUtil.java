package com.statistic.deputies.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

public class CvsParseUtil {
    public static List<List<String>> getDeputies() throws IOException, CsvValidationException {
        List<List<String>> records = new ArrayList<>();
        File file = new ClassPathResource("ukrainian_deputies_v3.csv").getFile();
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public static void main(String[] args) throws IOException, CsvValidationException {
        getDeputies();
    }
}
