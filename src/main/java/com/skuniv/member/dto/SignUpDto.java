package com.skuniv.member.dto;

import com.skuniv.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SignUpDto {
    private String name;
    private String phone;
    private String address;
    private String email;
    private String password;
    private LocalDateTime created_at;

    @Builder
    public SignUpDto(String name, String phone, String address, String email, String password, LocalDateTime created_at) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

    public Member toEntity(SignUpDto signUpDto){
        return Member.builder()
                .name(signUpDto.getName())
                .phone(signUpDto.getPhone())
                .address(signUpDto.getAddress())
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .created_at(signUpDto.getCreated_at()).build();
    }
}
