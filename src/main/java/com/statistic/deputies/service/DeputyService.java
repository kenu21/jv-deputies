package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputy;

import java.time.LocalDate;
import java.util.List;

public interface DeputyService {

    List<Deputy> getAllDeputies(Integer page, Integer size);

    List<Deputy> getDeputiesByConvocation(Integer rada);

    List<Deputy> getNotPoliticians(Integer page, Integer size);

    List<Deputy> getDeputiesWithAwards(Integer page, Integer size);

    List<Deputy> getAllDeputiesGroupByParty(LocalDate start, LocalDate end);

    List<Deputy> getDeputiesNotUkrainian(Integer page, Integer size);

    List<Deputy> getDeputiesByNationality(String nationality);

    List<String> getPartiesByConvocation(Integer rada);

    List<Deputy> getDeputiesWithShortestActiveTerms(Integer page, Integer size);

    List<Deputy> getDeputiesByUniversityGraduated(String university);

    List<Deputy> getMainPartySwitchers();

    Deputy addDeputy(Deputy deputy);
}
