package com.skuniv.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class DairyInsertDto {
    private String email;
    private String date;
    private String title;
    private String context;

    @Builder

    public DairyInsertDto(String email, String date, String title, String context) {
        this.email = email;
        this.date = date;
        this.title = title;
        this.context = context;
    }
}
