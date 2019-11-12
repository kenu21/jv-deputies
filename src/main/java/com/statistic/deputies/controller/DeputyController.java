package com.statistic.deputies.controller;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.service.DeputyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deputy")
public class DeputyController {

    @Autowired
    private DeputyService deputyService;

    @GetMapping("/byConvocation/{rada}")
    public List<Deputat> deputiesByCollocation(@PathVariable("rada") Integer rada) {
        if (deputyService.getDeputiesByConvocation(rada).isEmpty()) {
            throw new EntityNotFoundException("Invalid collocation number input");
        }
        return deputyService.getDeputiesByConvocation(rada);
    }

    @GetMapping("/notPoliticians")
    public List<Deputat> notPoliticians() {
        return deputyService.getNotPoliticians();
    }

    @GetMapping("/awarded")
    public List<Deputat> deputiesWithAwards() {
        return deputyService.getDeputiesWithAwards();
    }

    @GetMapping("/getGroupedByParty")
    public List<Deputat> getAllDeputiesGroupByParty(@RequestParam String startWork,
                                                    @RequestParam String endWork) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startWork, dateTimeFormatter);
        LocalDate end = LocalDate.parse(endWork, dateTimeFormatter);
        return deputyService.getAllDeputiesGroupByParty(start, end);
    }
}
