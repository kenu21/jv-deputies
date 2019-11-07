package com.statistic.deputies.controller;

import com.statistic.deputies.entity.Deputat;
import com.statistic.deputies.service.DeputyService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deputy")
public class DeputyController {

    @Autowired
    private DeputyService deputyService;

    @GetMapping("/getDeputiesByCollocation/{rada}")
    public List<Deputat> deputiesByCollocation(@PathVariable("rada") Integer rada) {
        if (deputyService.getDeputiesByCollocation(rada).isEmpty()) {
            throw new EntityNotFoundException("Invalid collocation number input");
        }
        return deputyService.getDeputiesByCollocation(rada);
    }
}
