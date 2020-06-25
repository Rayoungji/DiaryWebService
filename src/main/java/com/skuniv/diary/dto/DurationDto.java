package com.skuniv.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DurationDto {
    private String startDate;
    private String endDate;
}
