package com.statistic.deputies.service;

import static org.springframework.data.domain.PageRequest.of;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.repository.DeputatRepository;

import java.time.LocalDate;
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
    public List<Deputat> getAllDeputies(Integer page, Integer size) {
        return deputatRepository.findAll(of(page, size)).getContent();
    }

    @Override
    public List<Deputat> getDeputiesByConvocation(Integer rada) {
        return deputatRepository.findByRada(rada);
    }

    @Override
    public List<Deputat> getNotPoliticians(Integer page, Integer size) {
        return deputatRepository.findByActivityNotLike(
                "%політик%", of(page, size)).getContent();
    }

    @Override
    public List<Deputat> getDeputiesWithAwards(Integer page, Integer size) {
        return deputatRepository.findByAwardsNot("", of(page, size)).getContent();
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
    public List<Deputat> getDeputiesByNationality(String nationality) {
        return deputatRepository.findByNationalityIgnoreCaseLike(nationality);
    }

    @Override
    public List<String> getPartiesByConvocation(Integer rada) {
        return deputatRepository.findByRada(rada).stream()
                .map(Deputat::getParty).distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Deputat> getDeputiesWithShortestActiveTerms(Integer page, Integer size) {
        return deputatRepository.getDeputiesWithShortestActiveTerms(of(page, size)).getContent();
    }

    @Override
    public List<Deputat> getDeputiesByUniversityGraduated(String university) {
        return deputatRepository.findByEducationContaining(university);
    }

    @Override
    public List<Deputat> getMainPartySwitchers() {
        List<Deputat> deputies =  deputatRepository.findAll();
        Map<String, Integer> switchers = new HashMap<>();
        deputies.stream()
                .map(Deputat::getName)
                .forEach(name -> switchers.put(name, deputatRepository.findByName(name).stream()
                        .map(Deputat::getParty)
                        .collect(Collectors.toSet())
                        .size()));
        return deputies.stream()
                .filter(deputy -> switchers.get(deputy.getName())
                        .equals(Collections.max(switchers.values())))
                .collect(Collectors.toList());
    }

    @Override
    public Deputat save(Deputat deputat) {
        return deputatRepository.save(deputat);
    }
}
