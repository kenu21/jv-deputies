package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;

import java.time.LocalDate;
import java.util.List;

public interface DeputyService {

    List<Deputat> getAllDeputies(Integer page, Integer size);

    List<Deputat> getDeputiesByConvocation(Integer rada);

    List<Deputat> getNotPoliticians(Integer page, Integer size);

    List<Deputat> getDeputiesWithAwards(Integer page, Integer size);

    List<Deputat> getAllDeputiesGroupByParty(LocalDate start, LocalDate end);

    List<Deputat> getDeputiesNotUkrainian();

    List<String> getPartiesByConvocation(Integer rada);

    List<Deputat> getDeputiesWithShortestActiveTerms(Integer page, Integer size);

    List<Deputat> getDeputiesByUniversityGraduated(String university);

    List<Deputat> getMainPartySwitchers();

    Deputat save(Deputat deputat);
}
