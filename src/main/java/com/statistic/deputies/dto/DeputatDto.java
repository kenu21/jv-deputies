package com.statistic.deputies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DeputatDto {
    @NotNull
    @NotEmpty
    private Integer rada;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String party;

    @NotNull
    @NotEmpty
    private LocalDate startWork;

    @NotNull
    @NotEmpty
    private LocalDate endWork;

    @NotNull
    @NotEmpty
    private String nationality;

    public DeputatDto(Integer rada, String name,
                      String party, LocalDate startWork,
                      LocalDate endWork, String nationality) {
        this.rada = rada;
        this.name = name;
        this.party = party;
        this.startWork = startWork;
        this.endWork = endWork;
        this.nationality = nationality;
    }
}
