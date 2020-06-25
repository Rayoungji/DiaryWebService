package com.skuniv.diary.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Diary {
    private int id;
    private String email;
    private LocalDate date;
    private String title;
    private String context;
    private Date modify_at;

    @Builder
    public Diary(String email, LocalDate date, String title, String context, Date modify_at) {
        this.email = email;
        this.date = date;
        this.title = title;
        this.context = context;
        this.modify_at = modify_at;
    }
}
