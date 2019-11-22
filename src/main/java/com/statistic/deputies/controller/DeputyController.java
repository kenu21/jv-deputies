package com.statistic.deputies.controller;

import static com.statistic.deputies.dto.DeputyDtoUtil.deputyFromDto;

import com.statistic.deputies.dto.DeputyDto;
import com.statistic.deputies.entity.Deputy;
import com.statistic.deputies.service.DeputyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deputy")
public class DeputyController {

    @Autowired
    private DeputyService deputyService;

    @GetMapping
    public List<Deputy> allDeputies(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return deputyService.getAllDeputies(page, size);
    }

    @GetMapping("/byConvocation/{rada}")
    public List<Deputy> deputiesByConvocation(@PathVariable("rada") Integer rada) {
        List<Deputy> deputies = deputyService.getDeputiesByConvocation(rada);
        if (deputies.isEmpty()) {
            throw new EntityNotFoundException("Invalid convocation number input");
        }
        return deputies;
    }

    @GetMapping("/notPoliticians")
    public List<Deputy> notPoliticians(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return deputyService.getNotPoliticians(page, size);
    }

    @GetMapping("/awarded")
    public List<Deputy> deputiesWithAwards(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return deputyService.getDeputiesWithAwards(page, size);
    }

    @GetMapping("/getGroupedByParty")
    public List<Deputy> getAllDeputiesGroupByParty(@RequestParam String startWork,
                                                   @RequestParam String endWork) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startWork, dateTimeFormatter);
        LocalDate end = LocalDate.parse(endWork, dateTimeFormatter);
        return deputyService.getAllDeputiesGroupByParty(start, end);
    }

    @GetMapping("/notUkrainian")
    public List<Deputy> deputiesNotUkrainian(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return deputyService.getDeputiesNotUkrainian(page, size);
    }

    @GetMapping("/byNationality/{nationality}")
    public List<Deputy> deputiesByNationality(@PathVariable("nationality")
                                                       String nationality) {
        return deputyService.getDeputiesByNationality(nationality);
    }

    @GetMapping("/partiesByConvocation/{rada}")
    public List<String> partiesByConvocation(@PathVariable("rada") Integer rada) {
        List<String> parties = deputyService.getPartiesByConvocation(rada);
        if (parties.isEmpty()) {
            throw new EntityNotFoundException("Invalid convocation number input");
        }
        return parties;
    }

    @GetMapping("/leastActiveTerms")
    public List<Deputy> deputiesWithShortestActiveTerms(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return deputyService.getDeputiesWithShortestActiveTerms(page, size);
    }

    @GetMapping("/byUniversity")
    public List<Deputy> deputiesByUniversityGraduated(@RequestParam String university) {
        return deputyService.getDeputiesByUniversityGraduated(university);
    }

    @GetMapping("/partySwitchers")
    public List<Deputy> mainPartySwitchers() {
        return deputyService.getMainPartySwitchers();
    }

    @PostMapping("/add")
    public void addDeputy(@Valid @RequestBody DeputyDto deputyDto) {
        deputyService.addDeputy(deputyFromDto(deputyDto));
    }
}
