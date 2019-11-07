package com.statistic.deputies;

import com.opencsv.exceptions.CsvValidationException;
import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.repository.DeputatRepository;
import com.statistic.deputies.util.CvsParseUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParseData {

    @Autowired
    private DeputatRepository deputatRepository;

    @PostConstruct
    public void initDb() throws CsvValidationException {
        List<List<String>> deputies;
        try {
            deputies = CvsParseUtil.getDeputies();
        } catch (IOException e) {
            return;
        }

        for (int i = 1; i < deputies.size(); i++) {
            Deputat deputat = new Deputat();
            deputat.setRada(Integer.parseInt(deputies.get(i).get(0)));
            deputat.setName(deputies.get(i).get(1));
            deputat.setParty(deputies.get(i).get(2));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("d.MM.yyyy");
            LocalDate localDate = null;
            try {
                localDate = LocalDate.parse(deputies.get(i).get(7), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                localDate = LocalDate.parse(deputies.get(i).get(7), dateTimeFormatter2);
            }
            deputat.setStartWork(localDate);
            String endDate = deputies.get(i).get(8);
            if (endDate.equals("")) {
                localDate = null;
            } else {
                try {
                    localDate = LocalDate.parse(deputies.get(i).get(8), dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    deputat = null;
                }
            }
            if (deputat != null) {
                deputat.setEndWork(localDate);
                deputat.setNationality(deputies.get(i).get(14));
                deputat.setActivity(deputies.get(i).get(15));
                deputat.setAwards(deputies.get(i).get(21));
                deputat.setEdu(deputies.get(i).get(24));
                Deputat savedDeputata = deputatRepository.save(deputat);
            }
        }
    }
}
