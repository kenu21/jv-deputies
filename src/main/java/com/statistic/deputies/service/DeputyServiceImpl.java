package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.repository.DeputatRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeputyServiceImpl implements DeputyService {

    @Autowired
    private DeputatRepository deputatRepository;

    @Override
    public List<Deputat> getDeputiesByConvocation(Integer rada) {
        return deputatRepository.getDeputiesByConvocation(rada);
    }

    @Override
    public List<Deputat> getNotPoliticians() {
        return deputatRepository.findByActivityNotLike("%політик%");
    }

    @Override
    public List<Deputat> getDeputiesWithAwards() {
        return deputatRepository.findByAwardsNot("");
    }

    @Override
    public List<Deputat> getAllDeputiesGroupByParty(LocalDate start, LocalDate end) {
        return deputatRepository.getAllDeputiesGroupByParty(start, end);
    }
}
