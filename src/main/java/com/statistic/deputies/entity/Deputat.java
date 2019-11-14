package com.statistic.deputies.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "deputies")
public class Deputat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rada;
    private String name;
    private String party;
    private LocalDate startWork;
    private LocalDate endWork;
    private String nationality;

    @Column(columnDefinition = "TEXT")
    private String activity;

    @Column(columnDefinition = "TEXT")
    private String awards;

    @Column(name = "edu", columnDefinition = "TEXT")
    private String education;
}
