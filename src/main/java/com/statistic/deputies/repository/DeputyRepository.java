package com.statistic.deputies.repository;

import com.statistic.deputies.entity.Deputy;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeputyRepository extends JpaRepository<Deputy, Long> {

    List<Deputy> findByRada(Integer rada);

    Page<Deputy> findByActivityNotLike(String activity, Pageable pageable);

    Page<Deputy> findByAwardsNotNullAndAwardsNot(String awards, Pageable pageable);

    @Query("SELECT d FROM Deputy d WHERE d.startWork >= :startWork"
            + " AND d.endWork <= :endWork GROUP BY d.party, d.id")
    List<Deputy> findAllDeputiesGroupByParty(@Param("startWork") LocalDate startWork,
                                             @Param("endWork") LocalDate endWork);

    Page<Deputy> findByNationalityIgnoreCaseNotLike(String nationality, Pageable pageable);

    List<Deputy> findByNationalityIgnoreCaseContaining(String nationality);

    @Query(value = "SELECT d FROM Deputy d WHERE d.endWork IS NOT NULL "
            + "ORDER BY d.endWork - d.startWork")
    Page<Deputy> findDeputiesWithShortestActiveTerms(Pageable pageable);

    List<Deputy> findByEducationContaining(String university);

    List<Deputy> findByName(String name);
}
