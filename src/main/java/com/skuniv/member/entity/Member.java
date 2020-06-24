package com.skuniv.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String password;
    private LocalDateTime created_at;

    @Builder
    public Member(String name, String phone, String address, String email, String password, LocalDateTime created_at) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }
}

