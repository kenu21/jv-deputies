package com.statistic.deputies;

import com.opencsv.exceptions.CsvValidationException;
import com.statistic.deputies.entity.Deputy;
import com.statistic.deputies.repository.DeputyRepository;
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
    private DeputyRepository deputyRepository;

    @PostConstruct
    public void initDb() throws CsvValidationException {
        List<List<String>> deputies;
        try {
            deputies = CvsParseUtil.getDeputies();
        } catch (IOException e) {
            return;
        }

        for (int i = 1; i < deputies.size(); i++) {
            Deputy deputy = new Deputy();
            deputy.setRada(Integer.parseInt(deputies.get(i).get(0)));
            deputy.setName(deputies.get(i).get(1));
            deputy.setParty(deputies.get(i).get(2));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("d.MM.yyyy");
            LocalDate localDate;
            try {
                localDate = LocalDate.parse(deputies.get(i).get(7), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                localDate = LocalDate.parse(deputies.get(i).get(7), dateTimeFormatter2);
            }
            deputy.setStartWork(localDate);
            String endDate = deputies.get(i).get(8);
            if (endDate.equals("")) {
                localDate = null;
            } else {
                try {
                    localDate = LocalDate.parse(deputies.get(i).get(8), dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    deputy = null;
                }
            }
            if (deputy != null) {
                deputy.setEndWork(localDate);
                deputy.setNationality(deputies.get(i).get(14));
                deputy.setActivity(deputies.get(i).get(15));
                deputy.setAwards(deputies.get(i).get(21));
                deputy.setEducation(deputies.get(i).get(24));
                deputyRepository.save(deputy);
            }
        }
    }
}
