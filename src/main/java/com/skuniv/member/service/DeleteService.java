package com.skuniv.member.service;

import com.skuniv.member.dao.MemberDao;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class DeleteService {
    @Autowired
    public MemberDao memberDao;

    public void deleteMember(String email){
        memberDao.deleteMember(email);
    }
}
