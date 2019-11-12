package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.repository.DeputatRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeputyServiceImpl implements DeputyService {

    @Autowired
    private DeputatRepository deputatRepository;

    @Override
    public List<Deputat> getDeputiesByConvocation(Integer rada) {
        return deputatRepository.findByRada(rada);
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

    @Override
    public List<Deputat> getDeputiesNotUkrainian() {
        return deputatRepository.findByNationalityIgnoreCaseNotLike("УКРАЇН%");
    }

    @Override
    public List<String> getPartiesByConvocation(Integer rada) {
        return deputatRepository.findByRada(rada).stream()
                .map(Deputat::getParty).distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Deputat> getDeputiesWithShortestActiveTerms() {
        return deputatRepository.getDeputiesWithShortestActiveTerms();
    }

    @Override
    public Deputat save(Deputat deputat) {
        return deputatRepository.save(deputat);
    }
}
