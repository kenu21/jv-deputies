package com.statistic.deputies.service;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.repository.DeputatRepository;
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
    public List<Deputat> getDeputiesNotUkrainian() {
        return deputatRepository.getDeputiesNotUkrainian();
    }

    @Override
    public List<String> getPartiesByConvocation(Integer rada) {
        return deputatRepository.getPartiesByConvocation(rada);
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
