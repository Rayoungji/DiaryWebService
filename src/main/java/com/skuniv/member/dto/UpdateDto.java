package com.skuniv.member.dto;

import com.skuniv.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateDto {
    private String name;
    private String phone;
    private String address;
    private String password;
    private LocalDateTime created_at;

    @Builder
    public UpdateDto(String name, String phone, String address, String password, LocalDateTime created_at) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.created_at = created_at;
    }

    public Member toEntity(UpdateDto updateDto){
        return Member.builder()
                .name(updateDto.getName())
                .phone(updateDto.getPhone())
                .address(updateDto.getAddress())
                .password(updateDto.getPassword())
                .created_at(updateDto.getCreated_at()).build();
    }
}
