package com.statistic.deputies.repository;

import com.statistic.deputies.entity.Deputat;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeputatRepository extends JpaRepository<Deputat, Long> {

    @Query("SELECT d FROM Deputat d WHERE d.rada = :rada")
    List<Deputat> getDeputiesByConvocation(@Param("rada") Integer rada);

    List<Deputat> findByActivityNotLike(String activity);

    List<Deputat> findByAwardsNot(String awards);

    @Query("SELECT d FROM Deputat d WHERE d.startWork >= :startWork"
            + " AND d.endWork <= :endWork GROUP BY d.party, d.id")
    List<Deputat> getAllDeputiesGroupByParty(@Param("startWork") LocalDate startWork,
                                             @Param("endWork") LocalDate endWork);
}
