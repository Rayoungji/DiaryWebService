package com.skuniv.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class DiaryModifyDto {
    private String date;
    private String title;
    private String context;
    private Date modify_at;

    @Builder
    public DiaryModifyDto(String date, String title, String context, Date modify_at) {
        this.date=date;
        this.title = title;
        this.context = context;
        this.modify_at = modify_at;
    }
}
