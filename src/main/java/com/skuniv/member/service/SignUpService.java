package com.skuniv.member.service;

import com.skuniv.member.dao.MemberDao;
import com.skuniv.member.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class SignUpService {
    @Autowired
    private MemberDao memberDao;

    public void signUpMember(Member member){
        memberDao.insertMember(member);
    }
}
