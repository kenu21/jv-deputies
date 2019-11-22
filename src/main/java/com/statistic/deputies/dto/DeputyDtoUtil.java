package com.statistic.deputies.dto;

import com.statistic.deputies.entity.Deputy;

public class DeputyDtoUtil {
    public static Deputy deputyFromDto(DeputyDto deputyDto) {
        Deputy deputyFromDto = new Deputy();
        deputyFromDto.setRada(deputyDto.getRada());
        deputyFromDto.setName(deputyDto.getName());
        deputyFromDto.setParty(deputyDto.getParty());
        deputyFromDto.setStartWork(deputyDto.getStartWork());
        deputyFromDto.setEndWork(deputyDto.getEndWork());
        deputyFromDto.setNationality(deputyDto.getNationality());
        return deputyFromDto;
    }
}
