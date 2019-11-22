package com.statistic.deputies;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.PageRequest.of;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.statistic.deputies.entity.Deputy;
import com.statistic.deputies.repository.DeputyRepository;
import com.statistic.deputies.service.DeputyService;
import com.statistic.deputies.service.DeputyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class DeputyServiceTest {
    private DeputyService deputyService;
    private DeputyRepository deputyRepository;
    private Integer page;
    private Integer pageSize;
    private Pageable pageable;
    private MockDataInitializer initializer;
    private List<Deputy> expectedData;

    @Before
    public void setUp() {
        deputyRepository = mock(DeputyRepository.class);
        deputyService = new DeputyServiceImpl(deputyRepository);
        page = 0;
        pageSize = 2;
        pageable = of(page, pageSize);
        initializer = new MockDataInitializer();
        expectedData = initializer.createMockList();
    }

    @Test
    public void getAllDeputies() {
        when(deputyRepository.findAll(pageable)).thenReturn(getPageFromList(expectedData));
        List<Deputy> actualData = deputyService.getAllDeputies(page, pageSize);
        assertFalse("Expected non-empty list", actualData.isEmpty());
        int expectedSize = expectedData.size();
        assertEquals("Expected list with size = " + expectedSize,
                expectedSize, actualData.size());
    }

    @Test
    public void getDeputiesByConvocation() {
        Integer rada = anyInt();
        when(deputyRepository.findByRada(rada)).thenReturn(expectedData.stream()
                .filter(deputy -> deputy.getRada().equals(rada))
                .collect(Collectors.toList()));
        for (Deputy deputy : deputyService.getDeputiesByConvocation(rada)) {
            assertEquals("Expected list of deputies of " + rada + "th convocation",
                    rada, deputy.getRada());
        }
    }

    @Test
    public void getNotPoliticians() {
        String activity = "політик";
        List<Deputy> expectedDataWithSpecifiedActivity = expectedData.stream()
                .filter(deputy -> deputy.getActivity() == null
                        || !deputy.getActivity().contains(activity))
                .collect(Collectors.toList());
        when(deputyRepository.findByActivityNotLike("%" + activity + "%", pageable))
                .thenReturn(getPageFromList(expectedDataWithSpecifiedActivity));
        List<Deputy> actualData = deputyService.getNotPoliticians(page, pageSize);
        for (Deputy deputy : actualData) {
            String deputyActivity = deputy.getActivity();
            assertFalse("Expected list of deputies with activity doesn't include "
                            + activity,
                    deputyActivity != null && deputyActivity.contains(activity));
        }
    }

    @Test
    public void getDeputiesWithAwards() {
        List<Deputy> expectedDataWithAwards = expectedData.stream()
                .filter(deputy -> deputy.getAwards() != null
                        && !deputy.getAwards().equals(""))
                .collect(Collectors.toList());
        when(deputyRepository.findByAwardsNotNullAndAwardsNot("", pageable))
                .thenReturn(getPageFromList(expectedDataWithAwards));
        for (Deputy deputy : deputyService.getDeputiesWithAwards(page, pageSize)) {
            String deputyAwards = deputy.getAwards();
            assertFalse("Expected list of deputies with awards",
                    deputyAwards == null || deputyAwards.equals(""));
        }
    }

    @Test
    public void getDeputiesNotUkrainian() {
        String nationality = "УКРАЇН";
        List<Deputy> expectedNotUkrainians = new ArrayList<>();
        for (Deputy deputy : expectedData) {
            String deputyNationality = deputy.getNationality();
            if (deputyNationality == null ||
                    !deputyNationality.toUpperCase().contains(nationality)) {
                expectedNotUkrainians.add(deputy);
            }
        }
        when(deputyRepository.findByNationalityIgnoreCaseNotLike(
                nationality + "%", pageable))
                .thenReturn(getPageFromList(expectedNotUkrainians));
        List<Deputy> actualData = deputyService.getDeputiesNotUkrainian(page, pageSize);
        for (Deputy deputy : actualData) {
            String deputyNationality = deputy.getNationality();
            assertFalse("Expected list of deputies of not ukrainian heritage",
                    deputyNationality != null
                            && deputyNationality.toUpperCase().contains(nationality));
        }
    }

    @Test
    public void getDeputiesByNationality() {
        String nationality = anyString();
        String nationalityInUpperCase = nationality.toUpperCase();
        List<Deputy> expectedDeputiesWithSpecifiedNationality = new ArrayList<>();
        for (Deputy deputy : expectedData) {
            String deputyNationality = deputy.getNationality();
            if (deputyNationality != null &&
                    deputyNationality.toUpperCase().contains(nationalityInUpperCase)) {
                    expectedDeputiesWithSpecifiedNationality.add(deputy);
            }
        }
        when(deputyRepository.findByNationalityIgnoreCaseContaining(nationality))
                .thenReturn(expectedDeputiesWithSpecifiedNationality);
        List<Deputy> actualData = deputyService.getDeputiesByNationality(nationality);
        for (Deputy deputy : actualData) {
            String deputyNationality = deputy.getNationality();
            assertTrue("Expected list of deputies with nationality " + nationality,
                    deputyNationality != null &&
                            deputyNationality.toUpperCase().contains(nationalityInUpperCase));
        }
    }

    @Test
    public void findDeputiesWithShortestActiveTerms() {
        List<Deputy> expectedDataSortByTerm = expectedData.stream()
                .filter(deputy -> deputy.getEndWork() != null)
                .sorted((deputy1, deputy2) -> {
                   if (DAYS.between(deputy1.getEndWork(), deputy1.getStartWork())
                           < DAYS.between(deputy2.getEndWork(), deputy2.getStartWork())) {
                       return -1;
                    } else if (DAYS.between(deputy1.getEndWork(), deputy1.getStartWork())
                           > DAYS.between(deputy2.getEndWork(), deputy2.getStartWork())) {
                       return 1;
                   }
                   return 0;
                })
                .collect(Collectors.toList());
        when(deputyRepository.findDeputiesWithShortestActiveTerms(pageable))
                .thenReturn(getPageFromList(expectedDataSortByTerm));
        List<Deputy> actualData = deputyService.getDeputiesWithShortestActiveTerms(page, pageSize);
        assertTrue("Expected list of deputies with specified end of term",
                actualData.stream()
                        .noneMatch(deputy -> deputy.getEndWork() == null));
        Deputy actualDeputyWithShortestTerm = actualData.get(0);
        Deputy actualDeputyWithLongestTerm = actualData.get(actualData.size() - 1);
        assertTrue("Expected list of deputies sorted by seat term",
                DAYS.between(actualDeputyWithShortestTerm.getEndWork(),
                        actualDeputyWithShortestTerm.getStartWork())
                        <= DAYS.between(actualDeputyWithLongestTerm.getEndWork(),
                        actualDeputyWithLongestTerm.getStartWork()));
    }

    @Test
    public void getDeputiesByUniversityGraduated() {
        for (String university : new String[]{"КНУ", "кпі"}) {
            when(deputyRepository.findByEducationContaining(university))
                    .thenReturn(expectedData.stream()
                            .filter(deputy -> deputy.getEducation() != null
                                    && deputy.getEducation().contains(university))
                            .collect(Collectors.toList()));
            for (Deputy deputy : deputyService.getDeputiesByUniversityGraduated(university)) {
                assertEquals("Expected list of " + university + " graduates",
                        university, deputy.getEducation());
            }
        }
    }

    @Test
    public void getMainPartySwitchers() {
        String deputyName = "Тарас Шевченко";
        when(deputyRepository.findAll()).thenReturn(expectedData);
        when(deputyRepository.findByName(deputyName))
                .thenReturn(getExpectedDeputiesWithName(deputyName));
        assertFalse("Expected non-empty list",
                deputyService.getMainPartySwitchers().isEmpty());
    }

    @Test
    public void addDeputy() {
        Deputy newDeputy = initializer.createMockDeputy();
        when(deputyRepository.save(newDeputy)).thenReturn(newDeputy);
        Deputy actualDeputy = deputyService.addDeputy(newDeputy);
        assertNotNull("Expected Deputy instance", actualDeputy);
        String expectedName = newDeputy.getName();
        assertEquals("Expected Deputy instance to be " + expectedName,
                expectedName, actualDeputy.getName());
    }

    private Page<Deputy> getPageFromList(List<Deputy> expectedData) {
        return new PageImpl<>(expectedData, pageable, expectedData.size());
    }

    private List<Deputy> getExpectedDeputiesWithName(String name) {
        return expectedData.stream()
                .filter(deputy -> deputy.getName().equals(name))
                .collect(Collectors.toList());
    }
}
