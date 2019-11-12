package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;

import java.time.LocalDate;
import java.util.List;

public interface DeputyService {

    List<Deputat> getDeputiesByConvocation(Integer rada);

    List<Deputat> getNotPoliticians();

    List<Deputat> getDeputiesWithAwards();

    List<Deputat> getAllDeputiesGroupByParty(LocalDate start, LocalDate end);
}
