package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;

import java.time.LocalDate;
import java.util.List;

public interface DeputyService {

    List<Deputat> getDeputiesByConvocation(Integer rada);

    List<Deputat> getNotPoliticians();

    List<Deputat> getDeputiesWithAwards();

    List<Deputat> getAllDeputiesGroupByParty(LocalDate start, LocalDate end);

    List<Deputat> getDeputiesNotUkrainian();

    List<String> getPartiesByConvocation(Integer rada);

    List<Deputat> getDeputiesWithShortestActiveTerms();

    List<Deputat> getKpiAndNauGraduates();

    List<Deputat> getMainPartySwitchers();

    Deputat save(Deputat deputat);
}
