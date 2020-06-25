package com.skuniv.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DurationReturnDto {
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public DurationReturnDto(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
