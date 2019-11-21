package com.statistic.deputies.repository;

import com.statistic.deputies.entity.Deputat;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeputatRepository extends JpaRepository<Deputat, Long> {

    List<Deputat> findByRada(Integer rada);

    Page<Deputat> findByActivityNotLike(String activity, Pageable pageable);

    Page<Deputat> findByAwardsNot(String awards, Pageable pageable);

    @Query("SELECT d FROM Deputat d WHERE d.startWork >= :startWork"
            + " AND d.endWork <= :endWork GROUP BY d.party, d.id")
    List<Deputat> getAllDeputiesGroupByParty(@Param("startWork") LocalDate startWork,
                                             @Param("endWork") LocalDate endWork);

    List<Deputat> findByNationalityIgnoreCaseNotLike(String nationality);

    @Query(value = "SELECT d FROM Deputat d WHERE d.endWork IS NOT NULL "
            + "ORDER BY d.endWork - d.startWork")
    Page<Deputat> getDeputiesWithShortestActiveTerms(Pageable pageable);

    List<Deputat> findByEducationContaining(String university);

    List<Deputat> findByName(String name);

    List<Deputat> findByNationalityIgnoreCaseLike(String nationality);
}
