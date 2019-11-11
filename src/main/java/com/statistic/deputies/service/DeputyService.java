package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;

import java.util.List;

public interface DeputyService {

    List<Deputat> getDeputiesByConvocation(Integer rada);

    List<Deputat> getDeputiesNotUkrainian();

    List<String> getPartiesByConvocation(Integer rada);

    List<Deputat> getDeputiesWithShortestActiveTerms();

    Deputat save(Deputat deputat);
}
