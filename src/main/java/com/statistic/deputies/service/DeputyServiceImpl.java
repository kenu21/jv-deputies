package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.repository.DeputatRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    public List<Deputat> getKpiAndNauGraduates() {
        return deputatRepository.findByEduLikeOrEduLike(
                "%Київський політехнічний інститут%", "%Національний авіаційний університет%");
    }

    @Override
    public List<Deputat> getMainPartySwitchers() {
        Map<String, Integer> switchers = new HashMap<>();
        deputatRepository.findAll().stream()
                .map(Deputat::getName)
                .forEach(name -> switchers.put(name, deputatRepository.findByName(name).stream()
                        .map(Deputat::getParty)
                        .collect(Collectors.toSet())
                        .size()));
        return switchers.keySet().stream()
                .filter(name -> switchers.get(name).equals(Collections.max(switchers.values())))
                .map(name -> deputatRepository.findByName(name))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Deputat save(Deputat deputat) {
        return deputatRepository.save(deputat);
    }
}
