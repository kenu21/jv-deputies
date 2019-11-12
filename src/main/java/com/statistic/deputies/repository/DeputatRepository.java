package com.statistic.deputies.repository;

import com.statistic.deputies.entity.Deputat;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeputatRepository extends JpaRepository<Deputat, Long> {

    List<Deputat> findByRada(Integer rada);

    List<Deputat> findByActivityNotLike(String activity);

    List<Deputat> findByAwardsNot(String awards);

    @Query("SELECT d FROM Deputat d WHERE d.startWork >= :startWork"
            + " AND d.endWork <= :endWork GROUP BY d.party, d.id")
    List<Deputat> getAllDeputiesGroupByParty(@Param("startWork") LocalDate startWork,
                                             @Param("endWork") LocalDate endWork);

    @Query(value = "SELECT d FROM Deputat d WHERE UPPER(d.nationality) "
            + "NOT LIKE 'УКРАЇН%'")
    public List<Deputat> getDeputiesNotUkrainian();

    @Query(value = "SELECT DISTINCT d.party FROM Deputat d WHERE d.rada =:rada")
    List<String> getPartiesByConvocation(@Param("rada") Integer rada);

    @Query(value = "SELECT d FROM Deputat d WHERE d.endWork IS NOT NULL "
            + "ORDER BY d.endWork - d.startWork")
    List<Deputat> getDeputiesWithShortestActiveTerms();
}
