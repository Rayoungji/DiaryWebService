package com.skuniv.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryDeleteDto {
    private String id;
    private String date;
    private String email;
    private String title;
    private String context;
    private String modify_at;
}
