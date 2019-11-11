package com.statistic.deputies.dto;

import com.statistic.deputies.entity.Deputat;

public class DeputatDtoUtil {
    public static Deputat deputatFromDto(DeputatDto deputatDto) {
        Deputat deputatFromDto = new Deputat();
        deputatFromDto.setRada(deputatDto.getRada());
        deputatFromDto.setName(deputatDto.getName());
        deputatFromDto.setParty(deputatDto.getParty());
        deputatFromDto.setStartWork(deputatDto.getStartWork());
        deputatFromDto.setEndWork(deputatDto.getEndWork());
        deputatFromDto.setNationality(deputatDto.getNationality());
        return deputatFromDto;
    }
}
