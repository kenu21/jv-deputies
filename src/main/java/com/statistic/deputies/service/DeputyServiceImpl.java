package com.statistic.deputies.service;

import static org.springframework.data.domain.PageRequest.of;

import com.statistic.deputies.entity.Deputy;
import com.statistic.deputies.repository.DeputyRepository;

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
    private final DeputyRepository deputyRepository;

    public DeputyServiceImpl(DeputyRepository deputyRepository) {
        this.deputyRepository = deputyRepository;
    }

    @Override
    public List<Deputy> getAllDeputies(Integer page, Integer size) {
        return deputyRepository.findAll(of(page, size)).getContent();
    }

    @Override
    public List<Deputy> getDeputiesByConvocation(Integer rada) {
        return deputyRepository.findByRada(rada);
    }

    @Override
    public List<Deputy> getNotPoliticians(Integer page, Integer size) {
        return deputyRepository.findByActivityNotLike(
                "%політик%", of(page, size)).getContent();
    }

    @Override
    public List<Deputy> getDeputiesWithAwards(Integer page, Integer size) {
        return deputyRepository.findByAwardsNotNullAndAwardsNot("", of(page, size))
                .getContent();
    }

    @Override
    public List<Deputy> getAllDeputiesGroupByParty(LocalDate start, LocalDate end) {
        return deputyRepository.findAllDeputiesGroupByParty(start, end);
    }

    @Override
    public List<Deputy> getDeputiesNotUkrainian(Integer page, Integer size) {
        return deputyRepository.findByNationalityIgnoreCaseNotLike("УКРАЇН%", of(page, size))
                .getContent();
    }

    @Override
    public List<Deputy> getDeputiesByNationality(String nationality) {
        return deputyRepository.findByNationalityIgnoreCaseContaining(nationality);
    }

    @Override
    public List<String> getPartiesByConvocation(Integer rada) {
        return deputyRepository.findByRada(rada).stream()
                .map(Deputy::getParty).distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Deputy> getDeputiesWithShortestActiveTerms(Integer page, Integer size) {
        return deputyRepository.findDeputiesWithShortestActiveTerms(of(page, size)).getContent();
    }

    @Override
    public List<Deputy> getDeputiesByUniversityGraduated(String university) {
        return deputyRepository.findByEducationContaining(university);
    }

    @Override
    public List<Deputy> getMainPartySwitchers() {
        List<Deputy> deputies =  deputyRepository.findAll();
        Map<String, Integer> switchers = new HashMap<>();
        deputies.stream()
                .map(Deputy::getName)
                .forEach(name -> switchers.put(name, deputyRepository.findByName(name).stream()
                        .map(Deputy::getParty)
                        .collect(Collectors.toSet())
                        .size()));
        return deputies.stream()
                .filter(deputy -> switchers.get(deputy.getName())
                        .equals(Collections.max(switchers.values())))
                .collect(Collectors.toList());
    }

    @Override
    public Deputy save(Deputy deputy) {
        return deputyRepository.save(deputy);
    }
}
