package com.statistic.deputies.repository;

import com.statistic.deputies.entity.Deputat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeputatRepository extends JpaRepository<Deputat, Long> {

    @Query(value = "SELECT d FROM Deputat d WHERE d.rada = :rada")
    public List<Deputat> getDeputiesByConvocation(@Param("rada") Integer rada);
}
