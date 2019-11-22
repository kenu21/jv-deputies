package com.statistic.deputies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.PageRequest.of;


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
    private Integer size;
    private Pageable pageable;
    private List<Deputy> expectedData;
    private Page<Deputy> expectedPage;

    @Before
    public void setUp() {
        deputyRepository = mock(DeputyRepository.class);
        deputyService = new DeputyServiceImpl(deputyRepository);
        page = 0;
        size = 2;
        pageable = of(page, size);
        expectedData = new MockDataInitializer().add();
        expectedPage = new PageImpl<>(expectedData, pageable, expectedData.size());
    }

    @Test
    public void getAllDeputies() {
        when(deputyRepository.findAll(pageable)).thenReturn(expectedPage);
        List<Deputy> actualData = deputyService.getAllDeputies(page, size);
        assertFalse("Expected non-empty list", actualData.isEmpty());
        int expectedSize = expectedData.size();
        assertEquals("Expected list with size = " + expectedSize,
                expectedSize, actualData.size());
    }

    @Test
    public void getDeputiesByConvocation() {
        Integer rada = 9;
        List<Deputy> expectedDataWithSpecifiedRada = expectedData.stream()
                .filter(deputy -> deputy.getRada().equals(rada))
                .collect(Collectors.toList());
        when(deputyRepository.findByRada(rada)).thenReturn(expectedDataWithSpecifiedRada);
        for (Deputy deputy : deputyService.getDeputiesByConvocation(rada)) {
            assertEquals("Expected list of deputies of " + rada + "th convocation",
                    rada, deputy.getRada());
        }
    }

    @Test
    public void getNotPoliticians() {
        String activity = "політик";
        when(deputyRepository.findByActivityNotLike("%" + activity + "%", pageable))
                .thenReturn(expectedPage);
        for (Deputy deputy : deputyService.getNotPoliticians(page, size)) {
            assertEquals("Expected list of deputies with activity doesn't include "
                            + activity, activity, deputy.getActivity());
        }
    }

    @Test
    public void getDeputiesWithAwards() {
        when(deputyRepository.findByAwardsNotNullAndAwardsNot("", pageable))
                .thenReturn(expectedPage);
        for (Deputy deputy : deputyService.getDeputiesWithAwards(page, size)) {
            assertNotEquals("Expected list of deputies with awards",
                    null, deputy.getAwards());
            assertNotEquals("Expected list of deputies with awards",
                    "", deputy.getAwards());
        }
    }
}
