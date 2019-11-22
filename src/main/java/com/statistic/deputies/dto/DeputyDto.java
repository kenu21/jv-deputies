package com.statistic.deputies.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeputyDto {

    @NotNull
    @Positive
    private Integer rada;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String party;

    private LocalDate startWork;

    private LocalDate endWork;

    @NotNull
    @NotBlank
    private String nationality;

    public DeputyDto(Integer rada, String name, String party,
                     LocalDate startWork, LocalDate endWork, String nationality) {
        if (startWork == null || (endWork != null && endWork.isBefore(startWork))) {
            throw new IllegalArgumentException("Invalid dates of seat input");
        }
        this.rada = rada;
        this.name = name;
        this.party = party;
        this.startWork = startWork;
        this.endWork = endWork;
        this.nationality = nationality;
    }
}
