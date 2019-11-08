package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;

import java.util.List;

public interface DeputyService {

    List<Deputat> getDeputiesByConvocation(Integer rada);
}
