package com.statistic.deputies.controller;

import com.statistic.deputies.dto.DeputatDto;
import com.statistic.deputies.dto.DeputatDtoUtil;
import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.service.DeputyService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deputy")
public class DeputyController {

    @Autowired
    private DeputyService deputyService;

    @GetMapping("/byConvocation/{rada}")
    public List<Deputat> deputiesByCollocation(@PathVariable("rada") Integer rada) {
        if (deputyService.getDeputiesByConvocation(rada).isEmpty()) {
            throw new EntityNotFoundException("Invalid convocation number input");
        }
        return deputyService.getDeputiesByConvocation(rada);
    }

    @GetMapping("/notUkrainian")
    public List<Deputat> deputiesNotUkrainian() {
        return deputyService.getDeputiesNotUkrainian();
    }

    @GetMapping("/partiesByConvocation/{rada}")
    public List<String> partiesByConvocation(@PathVariable("rada") Integer rada) {
        return deputyService.getPartiesByConvocation(rada);
    }

    @GetMapping("/leastActiveTerms")
    public List<Deputat> deputiesWithShortestActiveTerms() {
        return deputyService.getDeputiesWithShortestActiveTerms();
    }
    @PostMapping("/add")
    public void add(@RequestBody DeputatDto deputatDto) {
        deputyService.save(DeputatDtoUtil.deputatFromDto(deputatDto));
    }
}
